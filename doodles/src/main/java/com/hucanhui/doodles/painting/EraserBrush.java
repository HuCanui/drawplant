package com.hucanhui.doodles.painting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;

public class EraserBrush extends Brush
{
  private int mParticleCount;
  private Path mPath = new Path();
  private float mX;
  private float mX1 = 0.0F;
  private float mX2 = 0.0F;
  private float mX3 = 0.0F;
  private float mY;
  private float mY1 = 0.0F;
  private float mY2 = 0.0F;
  private float mY3 = 0.0F;

  protected EraserBrush(Context paramContext)
  {
    this(paramContext, 0);
  }

  public EraserBrush(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.mPaint.setAntiAlias(true);
    this.mPaint.setDither(true);
    this.mPaint.setStyle(Style.STROKE);
    this.mPaint.setStrokeCap(Cap.ROUND);
  }

  private void calLastBounds(Route paramRoute, Rect paramRect)
  {
    paramRect.union((int)(Math.min(this.mX1, Math.min(this.mX2, this.mX3)) - this.mWidth), (int)(Math.min(this.mY1, Math.min(this.mY2, this.mY3)) - this.mWidth), (int)(Math.max(this.mX1, Math.max(this.mX2, this.mX3)) + this.mWidth), (int)(Math.max(this.mY1, Math.max(this.mY2, this.mY3)) + this.mWidth));
  }

  private void updateLastParticle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (this.mParticleCount == 0)
    {
      float f1 = this.mX;
      this.mX2 = f1;
      this.mX1 = f1;
      float f2 = this.mY;
      this.mY2 = f2;
      this.mY1 = f2;
      this.mX3 = paramFloat3;
    }
    for (this.mY3 = paramFloat4; ; this.mY3 = paramFloat4)
    {
      this.mParticleCount = (2 + this.mParticleCount);
      this.mX1 = this.mX3;
      this.mY1 = this.mY3;
      this.mX2 = paramFloat1;
      this.mY2 = paramFloat2;
      this.mX3 = paramFloat3;
    }
  }

  public void beginRoute(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    this.mPath.reset();
    this.mParticleCount = 0;
    this.mPath.moveTo(paramParticle.x, paramParticle.y);
    this.mX = paramParticle.x;
    this.mY = paramParticle.y;
  }

  protected void drawParticle(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    calLastBounds(this.mRoute, paramRect);
    this.mLayerCanvas.save(Canvas.CLIP_SAVE_FLAG);
    this.mLayerCanvas.clipRect(paramRect);
    this.mLayerCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
    this.mLayerCanvas.drawPath(this.mPath, this.mPaint);
    this.mLayerCanvas.restore();
  }

  public void drawRoute(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    if ((paramCanvas != null) && (paramParticle != null) && (paramRect != null) && (this.mLayer != null) && (this.mLayerCanvas != null))
    {
      float f1 = (paramParticle.x + this.mX) / 2.0F;
      float f2 = (paramParticle.y + this.mY) / 2.0F;
      updateLastParticle(this.mX, this.mY, f1, f2);
      this.mPath.quadTo(this.mX, this.mY, f1, f2);
      this.mX = paramParticle.x;
      this.mY = paramParticle.y;
      this.mBounds.setEmpty();
      drawParticle(paramCanvas, paramParticle, this.mBounds);
      if (!this.mBounds.isEmpty())
        paramRect.union(this.mBounds);
    }
  }

  public void endRoute(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    if ((paramCanvas != null) && (paramParticle != null) && (paramRect != null) && (this.mLayer != null) && (this.mLayerCanvas != null))
    {
      float f1 = (paramParticle.x + this.mX) / 2.0F;
      float f2 = (paramParticle.y + this.mY) / 2.0F;
      updateLastParticle(this.mX, this.mY, f1, f2);
      this.mPath.lineTo(f1, f2);
      this.mBounds.setEmpty();
      drawParticle(paramCanvas, paramParticle, this.mBounds);
      if (!this.mBounds.isEmpty())
        paramRect.union(this.mBounds);
    }
  }

  public void setPaper(Bitmap paramBitmap)
  {
    super.setPaper(paramBitmap);
    if (paramBitmap == null)
    {
      this.mPaint.setShader(null);
      return;
    }
    BitmapShader localBitmapShader = new BitmapShader(this.mPaper, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    this.mPaint.setShader(localBitmapShader);
  }

  public void setWidth(int paramInt)
  {
    super.setWidth(paramInt);
    this.mPaint.setStrokeWidth(this.mWidth);
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.EraserBrush
 * JD-Core Version:    0.6.0
 */