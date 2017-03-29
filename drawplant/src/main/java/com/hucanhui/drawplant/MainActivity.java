package com.hucanhui.drawplant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hucanhui.drawplant.slate.Slate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Slate slate;
    private LinearLayout root;
    public static int mLayoutHeight;
    public static int mLayoutWidth;
    public static final String IMAGE_SAVE_DIRNAME = "Drawings";
    public static final String IMAGE_TEMP_DIRNAME = IMAGE_SAVE_DIRNAME + "/.temporary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        obtainViewSize();

    }

    private void initView() {
        Button btn_marker = (Button) findViewById(R.id.btn_marker);
        Button btn_air = (Button) findViewById(R.id.btn_air);
        Button btn_watercolor = (Button) findViewById(R.id.btn_watercolor);
        Button btn_crayon = (Button) findViewById(R.id.btn_crayon);
        Button btn_hair = (Button) findViewById(R.id.btn_hair);
        Button btn_pen = (Button) findViewById(R.id.btn_pen);
        Button btn_image = (Button) findViewById(R.id.btn_image);
        root = (LinearLayout) findViewById(R.id.root);
        btn_marker.setOnClickListener(this);
        btn_air.setOnClickListener(this);
        btn_watercolor.setOnClickListener(this);
        btn_crayon.setOnClickListener(this);
        btn_hair.setOnClickListener(this);
        btn_pen.setOnClickListener(this);
        btn_image.setOnClickListener(this);
        slate = (Slate) findViewById(R.id.slate);
        slate.setPenType(Slate.SHAPE_CIRCLE);
        slate.setPenColor(Color.BLACK);
        slate.setPenSize(2,24);
        mMediaScannerConnection =
                new MediaScannerConnection(this, mMediaScannerClient);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_marker) {
            slate.setPenType(Slate.TYPE_Maker);

        } else if (id == R.id.btn_air) {
            slate.setPenType(Slate.TYPE_AIRBRUSH);

        } else if (id == R.id.btn_watercolor) {
            slate.setPenType(Slate.TYPE_WATER_COLOR);

        } else if (id == R.id.btn_crayon) {
            slate.setPenType(Slate.TYPE_CRAYAN);

        } else if (id == R.id.btn_hair) {
            slate.setPenType(Slate.TYPE_PIANCIEL);

        } else if (id == R.id.btn_pen) {
            slate.setPenType(Slate.TYPE_FOUNTAIN_PEN);

        } else if (id == R.id.btn_image) {
            slate.setPenType(Slate.TYPE_IMAGE_BRUSH);

        }
    }


    private boolean hasMeasured = false;
    // 获取LinearLayout的宽和高
    private void obtainViewSize() {
        ViewTreeObserver vto = root.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (hasMeasured == false) {
                    mLayoutWidth = root.getMeasuredWidth();
                    mLayoutHeight = root.getMeasuredHeight();
                    hasMeasured = true;
                }
                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.svg, menu);
        getMenuInflater().inflate(R.menu.undo, menu);
//        getMenuInflater().inflate(R.menu.redo, menu);
        return super.onCreateOptionsMenu(menu);
    }


    String svgFile ="";
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clear){
            slate.clear();
        }else if (item.getItemId() == R.id.get_svg){
            slate.mTiledCanvas.getSvgData();
        }else if (item.getItemId() == R.id.undo){
            slate.undo();
        } else if (item.getItemId() == R.id.redo){
            slate.redo();
        }
        return super.onOptionsItemSelected(item);
    }


    public void saveDrawing(String filename) {
        saveDrawing(filename, false);
    }

    public void saveDrawing(String filename, boolean temporary) {
        saveDrawing(filename, temporary, /*animate=*/ false, /*share=*/ false, /*clear=*/ false);
    }

    public void saveDrawing(String filename, boolean temporary, boolean animate, boolean share, boolean clear) {
        final Bitmap localBits = slate.copyBitmap(/*withBackground=*/!temporary);
        if (localBits == null) {
            return;
        }

        final String _filename = filename;
        final boolean _temporary = temporary;
        final boolean _share = share;
        final boolean _clear = clear;

        new AsyncTask<Void,Void,String>() {
            @Override
            protected String doInBackground(Void... params) {
                String fn = null;
                try {
                    File d = getPicturesDirectory();
                    d = new File(d, _temporary ? IMAGE_TEMP_DIRNAME : IMAGE_SAVE_DIRNAME);
                    if (!d.exists()) {
                        if (d.mkdirs()) {
                            if (_temporary) {
                                final File noMediaFile = new File(d, MediaStore.MEDIA_IGNORE_FILENAME);
                                if (!noMediaFile.exists()) {
                                    new FileOutputStream(noMediaFile).write('\n');
                                }
                            }
                        } else {
                            throw new IOException("cannot create dirs: " + d);
                        }
                    }
                    File file = new File(d, _filename);
                    OutputStream os = new FileOutputStream(file);
                    localBits.compress(Bitmap.CompressFormat.PNG, 100, os);
                    localBits.recycle();
                    os.close();

                    fn = file.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return fn;
            }

            @Override
            protected void onPostExecute(String fn) {
                if (fn != null) {
                    synchronized(mDrawingsToScan) {
                        mDrawingsToScan.add(fn);
                        if (_share) {
                            mPendingShareFile = fn;
                        }
                        if (!mMediaScannerConnection.isConnected()) {
                            mMediaScannerConnection.connect(); // will scan the files and share them
                        }
                    }
                }

                if (_clear) slate.clear();
            }
        }.execute();

    }

    private LinkedList<String> mDrawingsToScan = new LinkedList<String>();
    protected MediaScannerConnection mMediaScannerConnection;
    private String mPendingShareFile;
    private MediaScannerConnection.MediaScannerConnectionClient mMediaScannerClient =
            new MediaScannerConnection.MediaScannerConnectionClient() {
                @Override
                public void onMediaScannerConnected() {
                    scanNext();
                }

                private void scanNext() {
                    synchronized (mDrawingsToScan) {
                        if (mDrawingsToScan.isEmpty()) {
                            mMediaScannerConnection.disconnect();
                            return;
                        }
                        String fn = mDrawingsToScan.removeFirst();
                        mMediaScannerConnection.scanFile(fn, "image/png");
                    }
                }

                @Override
                public void onScanCompleted(String path, Uri uri) {
                    synchronized (mDrawingsToScan) {
                        if (path.equals(mPendingShareFile)) {
                            Intent sendIntent = new Intent(Intent.ACTION_SEND);
                            sendIntent.setType("image/png");
                            sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            startActivity(Intent.createChooser(sendIntent, "Send drawing to:"));
                            mPendingShareFile = null;
                        }
                        scanNext();
                    }
                }
            };

    public File getPicturesDirectory() {
        final File d;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            d = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        } else {
            d = new File("/sdcard/Pictures");
        }
        return d;
    }

    public void clickSave() {
        if (slate.isEmpty()) return;
        final String filename = System.currentTimeMillis() + ".png";
        saveDrawing(filename);
        Toast.makeText(this, "Drawing saved: " + filename, Toast.LENGTH_SHORT).show();
    }
}
