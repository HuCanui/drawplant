package com.hucanhui.doodles.painting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import com.hucanhui.doodles.painting.Utils;
import java.lang.reflect.Array;
import java.util.Iterator;

public class Brush
{
  public static final int BRUSH_CHALK = 6;
  public static final int BRUSH_COUNT = 8;
  public static final int BRUSH_ERASER = 0;
  public static final int BRUSH_LAST = 7;
  public static final int BRUSH_MARKER = 3;
  public static final int BRUSH_PEN = 1;
  public static final int BRUSH_PENCIL = 2;
  public static final int BRUSH_SOFT_CHARCOAL = 5;
  public static final int BRUSH_SOFT_ERASRE = 7;
  public static final int BRUSH_WATERCOLOR = 4;
  public static final int EFFECT_TYPE_FADE = 2;
  public static final int EFFECT_TYPE_LIGHT_FADE = 4;
  public static final int EFFECT_TYPE_LIGHT_PRESSURE_FADE = 5;
  public static final int EFFECT_TYPE_NORMAL = 0;
  public static final int EFFECT_TYPE_PRESSURE = 1;
  public static final int EFFECT_TYPE_PRESSURE_FADE = 3;
  public static final int TARGET_COUNT = 3;
  public static final int TARGET_PAINT = 0;
  public static final int TARGET_PLAYBACK = 1;
  public static final int TARGET_PREVIEW = 2;
  public static final int TEXTURE_MAX_ALPHA = 1;
  public static final int TEXTURE_MIN_ALPHA = 0;
  protected static final float WIDTH_PRESSURE_FACTOR = 0.3F;
  protected static final float WIDTH_SCALE_FACTOR = 0.4F;
  private static Brush[][] sBrushes = (Brush[][])Array.newInstance(Brush.class, new int[] { 3, 8 });
  protected int mAlpha;
  protected Rect mBounds;
  protected int mColor;
  protected Context mContext;
  protected int mEffectType;
  protected Bitmap mLayer;
  protected Canvas mLayerCanvas;
  protected int mLayerHeight;
  protected int mLayerWidth;
  protected Paint mPaint;
  protected Bitmap mPaper;
  protected Route mRoute;
  protected int mStep;
  protected final int mType;
  protected int mWidth;

  protected Brush(Context paramContext, int paramInt)
  {
    this.mContext = paramContext;
    this.mType = paramInt;
    this.mPaint = new Paint();
    this.mPaint.setAntiAlias(true);
    this.mRoute = new Route();
    this.mBounds = new Rect();
  }

  public static Brush copyBrush(Context paramContext, Brush paramBrush, int paramInt)
  {
    if (paramBrush == null)
      return null;
    Brush localBrush = getBrush(paramContext, paramInt, paramBrush.getType());
    localBrush.setColor(paramBrush.getColor());
    localBrush.setWidth(paramBrush.getWidth());
    localBrush.setAlpha(paramBrush.getAlpha());
    localBrush.setPaper(paramBrush.getPaper());
    return localBrush;
  }

  private static Brush createBrushInternal(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 0:
      return new EraserBrush(paramContext);
    case 7:
      return new SoftEraserBrush(paramContext);
    case 1:
      return new PenBrush(paramContext);
    case 2:
      TextureBrush localTextureBrush3 = new TextureBrush(paramContext, 2);
      localTextureBrush3.loadBrush(2131099652);
      return localTextureBrush3;
    case 3:
      return new MarkerBrush(paramContext);
    case 4:
      return new WaterColorBrush(paramContext);
    case 5:
      TextureBrush localTextureBrush2 = new TextureBrush(paramContext, 5);
      localTextureBrush2.loadBrush(2131099653);
      return localTextureBrush2;
    case 6:
    }
    TextureBrush localTextureBrush1 = new TextureBrush(paramContext, 6);
    localTextureBrush1.loadBrush(2131099649);
    return localTextureBrush1;
  }

  public static Brush getBrush(Context paramContext, int paramInt1, int paramInt2)
  {
    Brush localBrush = sBrushes[paramInt1][paramInt2];
    if (localBrush == null)
    {
      localBrush = createBrushInternal(paramContext, paramInt2);
      sBrushes[paramInt1][paramInt2] = localBrush;
    }
    return localBrush;
  }

  public void beginRoute(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    if ((paramCanvas != null) && (paramParticle != null) && (paramRect != null))
    {
      this.mRoute.add(paramParticle);
      if (this.mEffectType == 1)
      {
        paramParticle.width = (0.3F * this.mWidth);
        paramParticle.alpha = this.mAlpha;
      }
    }
    else
    {
      return;
    }
    if (this.mEffectType == 2)
    {
      paramParticle.width = this.mWidth;
      paramParticle.alpha = 0;
      return;
    }
    if (this.mEffectType == 3)
    {
      paramParticle.width = (0.3F * this.mWidth);
      paramParticle.alpha = 0;
      return;
    }
    if (this.mEffectType == 4)
    {
      paramParticle.width = this.mWidth;
      paramParticle.alpha = (2 * this.mAlpha / 3);
      return;
    }
    if (this.mEffectType == 5)
    {
      paramParticle.width = (0.3F * this.mWidth);
      paramParticle.alpha = (2 * this.mAlpha / 3);
      return;
    }
    paramParticle.width = this.mWidth;
    paramParticle.alpha = this.mAlpha;
  }

  public void clearBrushLayer(Rect paramRect)
  {
    this.mLayerCanvas.save(Canvas.CLIP_SAVE_FLAG);
    if (paramRect != null)
    {
      paramRect.set(-5 + paramRect.left, -5 + paramRect.top, 5 + paramRect.right, 5 + paramRect.bottom);
      this.mLayerCanvas.clipRect(paramRect);
    }
    this.mLayerCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
    this.mLayerCanvas.restore();
  }

  protected void doDraw(Canvas paramCanvas, Route paramRoute, Rect paramRect)
  {
    this.mBounds.setEmpty();
    Iterator localIterator = paramRoute.iterator();
    while (localIterator.hasNext())
      drawParticle(paramCanvas, (Particle)localIterator.next(), this.mBounds);
    if (!this.mBounds.isEmpty())
      paramRect.union(this.mBounds);
  }

  public void drawFillTexture(Path paramPath, Paint paramPaint, int paramInt)
  {
    if (this.mLayerCanvas != null)
    {
      this.mLayerCanvas.drawPath(paramPath, paramPaint);
      this.mLayerCanvas.drawColor(paramInt, PorterDuff.Mode.SRC_IN);
    }
  }

  public void drawInsertPic(Bitmap paramBitmap, Matrix paramMatrix, Paint paramPaint)
  {
    if (this.mLayerCanvas != null)
      this.mLayerCanvas.drawBitmap(paramBitmap, paramMatrix, paramPaint);
  }

  protected void drawParticle(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
  }

  public void drawRoute(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    int i;
    if ((paramCanvas != null) && (paramParticle != null) && (paramRect != null))
    {
      this.mRoute.add(paramParticle);
      i = this.mRoute.size();
      if (i >= 2);
    }
    else
    {
      return;
    }
    Route localRoute;
    if (i == 2)
    {
      Particle localParticle4 = (Particle)this.mRoute.get(0);
      Particle localParticle5 = (Particle)this.mRoute.get(1);
      if (this.mEffectType == 1)
      {
        localParticle5.width = Utils.clamp(localParticle4.width + 0.4F * this.mWidth, 0.0F, this.mWidth);
        localParticle5.alpha = this.mAlpha;
      }
      while (true)
      {
        localRoute = Route.calculateLinePoints(localParticle4, Particle.getMidPoint(localParticle4, localParticle5), Math.max(1, this.mWidth / 4));
        doDraw(paramCanvas, localRoute, paramRect);
        if (this.mEffectType == 2)
        {
          localParticle5.width = this.mWidth;
          localParticle5.alpha = 0;
          continue;
        }
        if (this.mEffectType == 3)
        {
          localParticle5.width = Utils.clamp(localParticle4.width + 0.4F * this.mWidth, 0.0F, this.mWidth);
          localParticle5.alpha = 0;
          continue;
        }
        if (this.mEffectType == 4)
        {
          localParticle5.width = this.mWidth;
          localParticle5.alpha = (2 * this.mAlpha / 3);
          continue;
        }
        if (this.mEffectType == 5)
        {
          localParticle5.width = Utils.clamp(localParticle4.width + 0.4F * this.mWidth, 0.0F, this.mWidth);
          localParticle5.alpha = (2 * this.mAlpha / 3);
          continue;
        }
        localParticle5.width = this.mWidth;
        localParticle5.alpha = this.mAlpha;
      }
    }
    Particle localParticle1 = (Particle)this.mRoute.get(i - 3);
    Particle localParticle2 = (Particle)this.mRoute.get(i - 2);
    Particle localParticle3 = (Particle)this.mRoute.get(i - 1);
    if (this.mEffectType == 1)
      localParticle3.width = Utils.clamp(localParticle2.width + 0.4F * this.mWidth, 0.0F, this.mWidth);
    while (true)
    {
      localParticle3.alpha = this.mAlpha;
      localRoute = Route.calculateSmoothLinePoints(localParticle1, localParticle2, localParticle3, Math.max(1, this.mWidth / 4));
      if (this.mEffectType == 2)
      {
        localParticle3.width = this.mWidth;
        continue;
      }
      if (this.mEffectType == 3)
      {
        localParticle3.width = Utils.clamp(localParticle2.width + 0.4F * this.mWidth, 0.0F, this.mWidth);
        continue;
      }
      if (this.mEffectType == 5)
      {
        localParticle3.width = Utils.clamp(localParticle2.width + 0.4F * this.mWidth, 0.0F, this.mWidth);
        continue;
      }
      localParticle3.width = this.mWidth;
    }
  }

  public void endRoute(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    Particle localParticle1 = null;
    Particle localParticle2 = null;
    Particle localParticle3 = null;
    if ((paramCanvas != null) && (paramParticle != null) && (paramRect != null))
    {
      this.mRoute.add(paramParticle);
      if ((paramCanvas != null) && (this.mRoute != null))
      {
        int i = this.mRoute.size();
        if (i >= 3)
        {
          localParticle1 = (Particle)this.mRoute.get(i - 3);
          localParticle2 = (Particle)this.mRoute.get(i - 2);
          localParticle3 = (Particle)this.mRoute.get(i - 1);
          if (this.mEffectType != 1)
          localParticle3.width = 0.0F;
          localParticle3.alpha = this.mAlpha;
        }
      }
    }
    while (true)
    {
      doDraw(paramCanvas, Route.calculateSmoothLinePoints(localParticle1, localParticle2, localParticle3, Math.max(1, this.mWidth / 5)), paramRect);
      this.mRoute.clear();
      if (this.mEffectType == 2)
      {
        localParticle3.width = this.mWidth;
        localParticle1.alpha /= 2;
        localParticle3.alpha = (-localParticle2.alpha);
        continue;
      }
      if (this.mEffectType == 3)
      {
        localParticle3.width = 0.0F;
        localParticle1.alpha /= 2;
        localParticle3.alpha = (-localParticle2.alpha);
        continue;
      }
      if (this.mEffectType == 4)
      {
        localParticle3.width = this.mWidth;
        localParticle1.alpha /= 2;
        localParticle3.alpha = 0;
        continue;
      }
      if (this.mEffectType == 5)
      {
        localParticle3.width = 0.0F;
        localParticle1.alpha /= 2;
        localParticle3.alpha = 0;
        continue;
      }
      localParticle3.width = this.mWidth;
      localParticle3.alpha = this.mAlpha;
    }
  }

  public int getAlpha()
  {
    return this.mAlpha;
  }

  public int getColor()
  {
    return this.mColor;
  }

  public Bitmap getPaper()
  {
    return this.mPaper;
  }

  public int getType()
  {
    return this.mType;
  }

  public int getWidth()
  {
    return this.mWidth;
  }

  public void setAlpha(int paramInt)
  {
    this.mAlpha = paramInt;
  }

  public void setBrushLayer(Bitmap paramBitmap, Canvas paramCanvas)
  {
    this.mLayer = paramBitmap;
    this.mLayerCanvas = paramCanvas;
    if (this.mLayer != null)
    {
      this.mLayerWidth = this.mLayer.getWidth();
      this.mLayerHeight = this.mLayer.getHeight();
    }
  }

  public void setColor(int paramInt)
  {
    this.mColor = paramInt;
    this.mPaint.setColor(this.mColor);
  }

  public void setEffectType(int paramInt)
  {
    this.mEffectType = paramInt;
  }

  public void setPaper(Bitmap paramBitmap)
  {
    this.mPaper = paramBitmap;
  }

  public void setWidth(int paramInt)
  {
    this.mWidth = paramInt;
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.Brush
 * JD-Core Version:    0.6.0
 */