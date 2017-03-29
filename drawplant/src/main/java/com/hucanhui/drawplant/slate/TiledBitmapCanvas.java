package com.hucanhui.drawplant.slate;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;


import com.google.gson.Gson;
import com.hucanhui.drawplant.svg.Shape;
import com.hucanhui.drawplant.svg.SvgBuilder;
import com.hucanhui.drawplant.svg.SvgCircleBuilder;
import com.hucanhui.drawplant.svg.SvgFile;

import java.util.ArrayList;
import java.util.List;

public class TiledBitmapCanvas implements CanvasLite {
    public static final String TAG = "Markers/TiledBitmapCanvas";

    public static final boolean DEBUG_TILES_ON_COMMIT = false;
    private static final boolean DEBUG_VERBOSE = false;

    public static final int DEFAULT_TILE_SIZE = 256;
    private static final float INVALIDATE_PADDING = 4.0f;
    public static final int DEFAULT_NUM_VERSIONS = 10;

    private boolean mDebug = false;
    private int mTileSizeX = DEFAULT_TILE_SIZE;
    private int mMaxVersions = DEFAULT_NUM_VERSIONS;
    private SvgFile svgFile = new SvgFile();
    private int mTileSizeY = DEFAULT_TILE_SIZE;
    private List<List<Shape>> shapeList = new ArrayList<List<Shape>>();
    private List<Shape> tempShapeList = new ArrayList<>();


    private class Tile {
        private class Version {
            int version;
            Canvas canvas;
            Bitmap bitmap;
            public Version(int version) {
                this.version = version;
                this.bitmap = Bitmap.createBitmap(mTileSizeX, mTileSizeY, mConfig);
                if (this.bitmap != null) {
                    this.canvas = new Canvas(this.bitmap);
                    this.canvas.translate(-x*mTileSizeX, -y*mTileSizeY);
                }
            }
        }
        int x, y;
        int top, bottom;
        boolean dirty;
        ArrayList<Version> versions;
        private String debugVersions() {
            StringBuffer sb = new StringBuffer();
            sb.append("bot=");
            sb.append(bottom);
            sb.append(" top=");
            sb.append(top);
            sb.append(" [");
            for (int i=0; i<versions.size(); i++) {
                if (i > 0) sb.append(" ");
                sb.append(versions.get(i).version);
            }
            sb.append("]");
            return sb.toString();
        }
        public Tile(int x, int y, int version) {
            this.x = x;
            this.y = y;
            versions = new ArrayList<Version>(mMaxVersions);
            bottom = version;
            createVersion(version); // sets top
            if (top < 0) {
                throw new OutOfMemoryError("Could not create bitmap for tile " + x + "," + y);
            }
        };
        private Version createVersion(int version) {
            Version v;
            final int N = versions.size();
            if (N == mMaxVersions) {
                // recycle the last version object & bitmap
                v = versions.get(N-1);
                versions.remove(N-1);
                v.version = version;
                bottom = versions.get(N-2).version;
                v.bitmap.eraseColor(0);
            } else {
                v = new Version(version);
                if (v.bitmap == null) {
                    // XXX handle memory error
                    return null;
                }
            }
            if (versions.size() > 0) {
                // XXX: this will be slow; maybe we can do the alloc & copy at commit time
                v.canvas.drawBitmap(versions.get(0).bitmap, x*mTileSizeX, y*mTileSizeY, null);
            }
            versions.add(0, v);
            top = version;
            if (mDebug && DEBUG_VERBOSE) {
                Log.v(TAG, String.format("createVersion %d: [%2d,%2d] %s", version, x, y, debugVersions()));
            }
            return v;
        }
        private int findVersion(int version) {
            // short path
            if (version >= top) return 0;
            if (version < bottom) return -1;
            if (mDebug) {
                if (versions.size() > 0 && top != versions.get(0).version) {
                    Log.e(TAG, String.format("internal inconsistency: tile (%d,%d) top=%d but version[0]=%d",
                            x, y, top, versions.get(0).version));
                }
            }
            for (int i=1; i<versions.size(); i++) {
                final Version v = versions.get(i);
                if (v.version <= version) {
                    return i;
                }
            }
            // this is just for the error
            throw new RuntimeException(
                    String.format("internal inconsistency: couldn't findVersion %d for tile (%d,%d) %s",
                            version, x, y,
                            debugVersions()));
        }
        private Version getVersion(int version) {
            // short path
            if (version == top) return versions.get(0);

            // another common situation
            if (version > top) return createVersion(version);

            // ok, you're gonna make us go look
            int i = findVersion(version);
            if (i >= 0) {
                return versions.get(i);
            }

            // we don't have it
            Log.e(TAG, "Tile.getVersion: don't have v" + version + " at " + x + "," + y);
            return null;
        }
        public void clear() {
            for (int i=0; i<versions.size(); i++) {
                versions.get(i).bitmap.recycle();
            }
            versions.clear();
        }
        public Bitmap getBitmap() {
            return versions.get(0).bitmap;
        }
        public Bitmap getBitmap(int version) {
            return getVersion(version).bitmap;
        }
        public Canvas getCanvas() {
            return versions.get(0).canvas;
        }
        public Canvas getCanvas(int version) {
            return getVersion(version).canvas;
        }
        public void revert(int toVersion) {
            final int i = findVersion(toVersion);
            if (i < 0) {
                // went backward past the end of our undo stack
                Log.e(TAG, "cannot revert to version " + toVersion + " because it is before bottom: " + bottom);
                return;
            }
            if (i > 0) {
                versions.subList(0, i).clear();
                final int oldTop = top;
                if (mDebug) {
                    Log.v(TAG, String.format("   tile [%2d,%2d]: revert(%d) old top %d, %s",
                            x, y, toVersion, oldTop,
                            debugVersions()));
                }
                top = versions.get(0).version;

                //移除上一步数据
                if (shapeList.size()>0){
                    shapeList.remove(shapeList.size()-1);
                    tempShapeList = new ArrayList<>();
                }
            }
        }
    }
    public Tile[] mTiles;

    private int mWidth, mHeight, mTilesX, mTilesY;

    private Config mConfig;

    private int mNewVersion = 0,
            mBottomVersion = 0;
    private boolean mVersionInUse = false;

    public TiledBitmapCanvas(Bitmap bitmap, int tileSizeX, int tileSizeY, int maxVersions) {
        mWidth = bitmap.getWidth();
        mHeight = bitmap.getHeight();
        mConfig = bitmap.getConfig();
        mTileSizeX = tileSizeX;
        mTileSizeY = tileSizeY;
        mMaxVersions = maxVersions;
        load(bitmap);
    }

    public TiledBitmapCanvas(Bitmap bitmap) {
        this(bitmap, DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE, DEFAULT_NUM_VERSIONS);
    }

    public TiledBitmapCanvas(int w, int h, Config config, int tileSizeX, int tileSizeY, int maxVersions) {
        mWidth = w;
        mHeight = h;
        mConfig = config;
        mTileSizeX = tileSizeX;
        mTileSizeY = tileSizeY;
        mMaxVersions = maxVersions;
        load(null);
    }

    public TiledBitmapCanvas(int w, int h, Config config) {
        this(w, h, config, DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE, DEFAULT_NUM_VERSIONS);
    }

    public void setDebug(boolean d) {
        mDebug = d;
    }

    public void recycleBitmaps() {
        for (int i=0; i<mTiles.length; i++) {
            mTiles[i].clear();
            mTiles[i] = null;
        }
        mTiles = null;
    }

    // By using this to get a Canvas to draw into, you ensure that mVersionInUse is current
    private Canvas getDrawingCanvas(Tile t) {
        mVersionInUse = true;
        return t.getCanvas(mNewVersion);
    }

    private void load(Bitmap bitmap) {
        mTilesX = mWidth / mTileSizeX + ((mWidth % mTileSizeY) == 0 ? 0 : 1);
        mTilesY = mHeight / mTileSizeX + ((mHeight % mTileSizeY) == 0 ? 0 : 1);
        mTiles = new Tile[mTilesX * mTilesY];
        mTiles = new Tile[mTilesX * mTilesY];

        final Paint paint = new Paint();
        for (int j=0; j<mTilesY; j++) {
            for (int i=0; i<mTilesX; i++) {
                final int p = j * mTilesX + i;
                final Tile t = new Tile(i, j, mNewVersion); // XXX: version
                mTiles[p] = t;
                if (bitmap != null) {
                    getDrawingCanvas(t).drawBitmap(bitmap, 0, 0, paint);
                }
            }
        }
    }

    public static final int max(int a, int b) {
        return (b > a) ? b : a;
    }

    public static final int min(int a, int b) {
        return (b < a) ? b : a;
    }

    public void drawRect(float l, float t, float r, float b, Paint paint) {
        final int tilel = max(0,(int)Math.floor((l-INVALIDATE_PADDING) / mTileSizeX));
        final int tilet = max(0,(int)Math.floor((t-INVALIDATE_PADDING) / mTileSizeY));
        final int tiler = min(mTilesX-1, (int)Math.floor((r+INVALIDATE_PADDING) / mTileSizeX));
        final int tileb = min(mTilesY-1, (int)Math.floor((b+INVALIDATE_PADDING) / mTileSizeY));
        for (int tiley = tilet; tiley <= tileb; tiley++) {
            for (int tilex = tilel; tilex <= tiler; tilex++) {
                final Tile tile = mTiles[tiley*mTilesX + tilex];
                getDrawingCanvas(tile).drawRect(l, t, r, b, paint);
                tile.dirty = true;
            }
        }
    }


    public void drawCircle(float x, float y, float r, Paint paint) {
        final float invalR = r + INVALIDATE_PADDING;
        final int tilel = max(0, (int)Math.floor((x-invalR) / mTileSizeX));
        final int tilet = max(0, (int)Math.floor((y-invalR) / mTileSizeY));
        final int tiler = min(mTilesX-1, (int)Math.floor((x+invalR) / mTileSizeX));
        final int tileb = min(mTilesY-1, (int)Math.floor((y+invalR) / mTileSizeY));
        for (int tiley = tilet; tiley <= tileb; tiley++) {
            for (int tilex = tilel; tilex <= tiler; tilex++) {
                final Tile tile = mTiles[tiley*mTilesX + tilex];
                getDrawingCanvas(tile).drawCircle(x, y, r, paint);
                Shape shape = new Shape();
                shape.setCx(x);
                shape.setCy(y);
                shape.setR(r);
                shape.setFill("#"+Integer.toHexString(paint.getColor()).substring(2, 7));
                shape.setOpacity((float)paint.getAlpha()/100);
                tempShapeList.add(shape);
//                svgFile.appent(new SvgCircleBuilder(x, y, r, ((float)paint.getAlpha()/100),Integer.toHexString(paint.getColor()).substring(2, 7)).toString());
                tile.dirty = true;
            }
        }
    }

    public void drawColor(int color, PorterDuff.Mode mode) {
        for (int i=0; i<mTiles.length; i++) {
            final Tile tile = mTiles[i];
            getDrawingCanvas(tile).drawColor(color, mode);
            tile.dirty = true;
        }
    }

    @Override
    public void drawBitmap(Bitmap bitmap, Rect src, RectF dst, Paint paint) {
        final int tilel = max(0,(int)Math.floor((dst.left-INVALIDATE_PADDING) / mTileSizeX));
        final int tilet = max(0,(int)Math.floor((dst.top-INVALIDATE_PADDING) / mTileSizeY));
        final int tiler = min(mTilesX-1, (int)Math.floor((dst.right+INVALIDATE_PADDING) / mTileSizeX));
        final int tileb = min(mTilesY-1, (int)Math.floor((dst.bottom+INVALIDATE_PADDING) / mTileSizeY));
        for (int tiley = tilet; tiley <= tileb; tiley++) {
            for (int tilex = tilel; tilex <= tiler; tilex++) {
                final Tile tile = mTiles[tiley*mTilesX + tilex];
                getDrawingCanvas(tile).drawBitmap(bitmap, src, dst, paint);
                tile.dirty = true;
            }
        }
    }

    @Override
    public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        RectF dst = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        matrix.mapRect(dst);
        final int tilel = max(0,(int)Math.floor((dst.left-INVALIDATE_PADDING) / mTileSizeX));
        final int tilet = max(0,(int)Math.floor((dst.top-INVALIDATE_PADDING) / mTileSizeY));
        final int tiler = min(mTilesX-1, (int)Math.floor((dst.right+INVALIDATE_PADDING) / mTileSizeX));
        final int tileb = min(mTilesY-1, (int)Math.floor((dst.bottom+INVALIDATE_PADDING) / mTileSizeY));
        for (int tiley = tilet; tiley <= tileb; tiley++) {
            for (int tilex = tilel; tilex <= tiler; tilex++) {
                final Tile tile = mTiles[tiley*mTilesX + tilex];
                getDrawingCanvas(tile).drawBitmap(bitmap, matrix, paint);
                tile.dirty = true;
            }
        }
    }

    public void drawBitmap(Bitmap bitmap, float left, float top, float right, float bottom, Paint paint) {
        final int tilel = max(0,(int)Math.floor((left-INVALIDATE_PADDING) / mTileSizeX));
        final int tilet = max(0,(int)Math.floor((top-INVALIDATE_PADDING) / mTileSizeY));
        final int tiler = min(mTilesX-1, (int)Math.floor((right+INVALIDATE_PADDING) / mTileSizeX));
        final int tileb = min(mTilesY-1, (int)Math.floor((bottom+INVALIDATE_PADDING) / mTileSizeY));
        for (int tiley = tilet; tiley <= tileb; tiley++) {
            for (int tilex = tilel; tilex <= tiler; tilex++) {
                final Tile tile = mTiles[tiley*mTilesX + tilex];
                getDrawingCanvas(tile).save();
                getDrawingCanvas(tile).rotate((float)(Math.random()*1000),right ,bottom);
                getDrawingCanvas(tile).drawBitmap(bitmap, left, top, paint);
                getDrawingCanvas(tile).restore();
                tile.dirty = true;
            }
        }
    }

    private static Paint dbgPaint = new Paint(0);
    private static Paint dbgStroke = new Paint(0);
    private static Paint dbgTextPaint = new Paint(0);
    static {
        dbgStroke.setColor(0x80000000);
        dbgStroke.setStrokeWidth(3.0f);
        dbgStroke.setStyle(Paint.Style.STROKE);
        dbgTextPaint.setColor(0x80000000);
        dbgTextPaint.setTextSize(20.0f);
    }
    private int mDrawCount = 0;

    final static int DEBUG_COLORS[] = {
            0x40FF0000, 0x40FFFF00, 0x4000FF00, 0x400000FF, 0x40FF00FF,
            0x40AA0000, 0x40AAAA00, 0x4000AA00, 0x400000AA, 0x40AA00AA,
            0x40770000, 0x40777700, 0x40007700, 0x40000077, 0x40770077,
    };

    @Override
    public void drawTo(Canvas drawCanvas, float left, float top, Paint paint, boolean onlyDirty) {
        final Rect src = new Rect(0, 0, mTileSizeX, mTileSizeY);
        final Rect dst = new Rect(0, 0, mTileSizeX, mTileSizeY);
        drawCanvas.save();
        drawCanvas.translate(-left, -top);
        drawCanvas.clipRect(0, 0, mWidth, mHeight);
        for (int j=0; j<mTilesY; j++) {
            for (int i=0; i<mTilesX; i++) {
                dst.offsetTo(i*mTileSizeX, j*mTileSizeY);
                final int p = j * mTilesX + i;
                final Tile tile = mTiles[p];
                if (!onlyDirty || tile.dirty) {
                    drawCanvas.drawBitmap(tile.getBitmap(), src, dst, paint);
                    tile.dirty = false;
                    if (mDebug) {
                        mDrawCount++;
                        dbgPaint.setColor(DEBUG_COLORS[tile.top % DEBUG_COLORS.length]);
                        //drawCanvas.drawRect(dst, (mDrawCount % 2 == 0) ? dbgPaint1 : dbgPaint2);
                        drawCanvas.drawRect(dst, dbgPaint);
                        //drawCanvas.drawRect(dst, dbgStroke);
                        drawCanvas.drawText(
                                String.format("%d,%d v%d", tile.x, tile.y, tile.top),
                                dst.left + 4, dst.bottom - 4, dbgTextPaint);
                    }
                }
            }
        }
        drawCanvas.restore();
    }

    public int getWidth() {
        return mWidth;
    }
    public int getHeight() {
        return mHeight;
    }

    public Bitmap toBitmap() {
        return toBitmap(0);
    }

    public Bitmap toBitmap(int bgcolor) {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, mConfig);
        Canvas canvas = new Canvas(bitmap);

        if (bgcolor != Color.TRANSPARENT) {
            canvas.drawColor(bgcolor);
        }

        drawTo(canvas, 0, 0, null, false);
        return bitmap;
    }

    public void commit() {
        if (!mVersionInUse) return;

        tempShapeList = new ArrayList<>();

        mNewVersion++; // one more than top
        if (mNewVersion - mBottomVersion > mMaxVersions) {
            mBottomVersion++;
        }
        mVersionInUse = false;
        if (DEBUG_TILES_ON_COMMIT) {
            Log.v(TAG, "commit: next=" + mNewVersion + " top=" + (mNewVersion-1) + " bot=" + mBottomVersion);
            for (int i=0; i<mTiles.length; i++) {
                final Tile tile = mTiles[i];
                Log.v(TAG, String.format("   %2d [%2d,%2d]: %s",
                        i,
                        tile.x, tile.y,
                        tile.debugVersions()
                ));
            }
        }
    }


    public void undo(){
        step(-1);
    }

    public void step(int delta) {
        final int oldTop = mVersionInUse ? mNewVersion : mNewVersion-1;
        int newTop = oldTop + delta;       // step
        if (newTop < mBottomVersion) {
            if (newTop == mBottomVersion) return; // we're already at the end
            newTop = mBottomVersion;
        }
        if (mDebug) {
            Log.v(TAG, String.format("step(%d): oldTop=%d newTop=%d bot=%d", delta,
                    oldTop, newTop, mBottomVersion));
        }
        for (int i=0; i<mTiles.length; i++) {
            final Tile tile = mTiles[i];
            if (tile.bottom == newTop) {
                // XXX: bottomed out; do something
            }
            tile.revert(newTop);
            tile.dirty = true; // XXX: only do this if the version changed, i.e. tile.top > mVersion
        }
        mNewVersion = newTop + 1;
        mVersionInUse = false;
    }

    public Canvas getCanvas(){
        return mTiles[0].getCanvas();
    }

    public String getSvgFile(){
        return svgFile.build();
    }


    public void addTempShapes(){
        if (tempShapeList != null){
            shapeList.add(tempShapeList);
        }
        tempShapeList = new ArrayList<>();
    }

    String jsonStr;
    public void getSvgData(){
        jsonStr = "";
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < shapeList.size(); i++) {
            shapes.addAll(shapeList.get(i));
        }
        Gson gson = new Gson();
        jsonStr = gson.toJson(shapes);
        int a = 1;
    }
}
