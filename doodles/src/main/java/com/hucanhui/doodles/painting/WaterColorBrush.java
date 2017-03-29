package com.hucanhui.doodles.painting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import java.util.Iterator;

public class WaterColorBrush extends TextureBrush
{
  private Paint mDepthPaint;
  private int mDirectRadius;
  private float mDrawRadius;

  public WaterColorBrush(Context paramContext)
  {
    super(paramContext, 4);
    loadBrush(2131099654);
    this.mDepthPaint = new Paint();
    this.mDepthPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
  }

  protected void doDraw(Canvas paramCanvas, Route paramRoute, Rect paramRect)
  {
    this.mBounds.setEmpty();
    Iterator localIterator = paramRoute.iterator();
    while (localIterator.hasNext())
      drawParticle(paramCanvas, (Particle)localIterator.next(), this.mBounds);
    if (!this.mBounds.isEmpty())
    {
      this.mLayerCanvas.save(Canvas.CLIP_SAVE_FLAG);
      this.mLayerCanvas.clipRect(this.mBounds);
      this.mLayerCanvas.drawColor(this.mColor, PorterDuff.Mode.SRC_ATOP);
      this.mLayerCanvas.restore();
      paramRect.union(this.mBounds);
    }
  }

  protected void drawParticle(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    float f1 = paramParticle.x;
    float f2 = paramParticle.y;
    int i = PainterUtils.getRandNum(this.mCurParticleIndex);
    this.mPaint.setAlpha(paramParticle.alpha);
    if (1 == this.mDitherType)
    {
      int n = i % this.mDitherRadius;
      f1 += n;
      f2 += n;
    }
    float f3 = paramParticle.width;
    float f4 = f1 - f3;
    float f5 = f2 - f3;
    if (1 == this.mDirectionType)
    {
      int m = i % this.mDirectRadius;
      this.mMatrix.setRotate(m, this.mWidth, this.mWidth);
      this.mMatrix.postScale((1.0F + 2.0F * f3) / this.mMaskWidth, (1.0F + 2.0F * f3) / this.mMaskWidth);
    }
    while (true)
    {
      this.mMatrix.postTranslate(f4, f5);
      paramRect.union((int)(f1 - this.mDrawRadius), (int)(f2 - this.mDrawRadius), (int)(f1 + this.mDrawRadius), (int)(f2 + this.mDrawRadius));
      this.mLayerCanvas.save(Canvas.CLIP_SAVE_FLAG);
      this.mLayerCanvas.clipRect(paramRect);
      this.mLayerCanvas.drawBitmap(this.mMask, this.mMatrix, this.mDepthPaint);
      this.mLayerCanvas.restore();
      this.mCurParticleIndex = (1 + this.mCurParticleIndex);
      if (2 == this.mDirectionType)
      {
        int k = (int)paramParticle.angle;
        this.mMatrix.setRotate(k, this.mWidth, this.mWidth);
        this.mMatrix.postScale((1.0F + 2.0F * f3) / this.mMaskWidth, (1.0F + 2.0F * f3) / this.mMaskWidth);
        continue;
      }
      if (3 == this.mDirectionType)
      {
        int j = (int)(paramParticle.angle + i % this.mDirectRadius);
        this.mMatrix.setRotate(j, this.mWidth, this.mWidth);
        this.mMatrix.postScale((1.0F + 2.0F * f3) / this.mMaskWidth, (1.0F + 2.0F * f3) / this.mMaskWidth);
        continue;
      }
      this.mMatrix.setScale((1.0F + 2.0F * f3) / this.mMaskWidth, (1.0F + 2.0F * f3) / this.mMaskWidth);
    }
  }

  public void drawRoute(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    int i;
    if ((paramCanvas != null) && (paramParticle != null) && (paramRect != null) && (this.mLayer != null) && (this.mLayerCanvas != null))
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
        localRoute = Route.calculateLinePoints(localParticle4, Particle.getMidPoint(localParticle4, localParticle5), this.mStep);
        this.mDirectRadius = Math.max(1, this.mDirectionValue * this.mWidth / this.mMaskWidth);
        this.mCurParticleIndex = 0;
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
      localRoute = Route.calculateSmoothLinePoints(localParticle1, localParticle2, localParticle3, this.mStep);
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
    if ((paramCanvas != null) && (paramParticle != null) && (paramRect != null) && (this.mLayer != null) && (this.mLayerCanvas != null))
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
          if (this.mEffectType != 1){
            localParticle3.width = (-localParticle2.width);
            localParticle3.alpha = this.mAlpha;
          }
        }
      }
    }
    while (true)
    {
      Route localRoute = Route.calculateSmoothLinePoints(localParticle1, localParticle2, localParticle3, this.mStep);
      this.mDirectRadius = Math.max(1, this.mDirectionValue * this.mWidth / this.mMaskWidth);
      this.mCurParticleIndex = 0;
      doDraw(paramCanvas, localRoute, paramRect);
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
        localParticle3.width = (-localParticle2.width);
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
        localParticle3.width = (-localParticle2.width);
        localParticle1.alpha /= 2;
        localParticle3.alpha = 0;
        continue;
      }
      localParticle3.width = this.mWidth;
      localParticle3.alpha = this.mAlpha;
    }
  }

  public void setWidth(int paramInt)
  {
    super.setWidth(paramInt);
    this.mDrawRadius = (2.0F * this.mWidth + Math.round(this.mWidth * this.mDitherFactor));
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.WaterColorBrush
 * JD-Core Version:    0.6.0
 */