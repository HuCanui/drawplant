package com.hucanhui.doodles.painting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import java.util.Random;

public class TextureBrush extends Brush
{
  public static final int DIRECTION_TYPE_NORMAL = 0;
  public static final int DIRECTION_TYPE_RANDOM = 1;
  public static final int DIRECTION_TYPE_TANGENT = 2;
  public static final int DIRECTION_TYPE_TANGENT_RANDOM = 3;
  public static final int DITHER_TYPE_NORMAL = 0;
  public static final int DITHER_TYPE_RANDOM = 1;
  protected int mBrushMaxStep = 0;
  protected int mCurParticleIndex = 0;
  protected int mDirectionType = 0;
  protected int mDirectionValue = 0;
  protected float mDitherFactor = 0.0F;
  protected int mDitherRadius = 0;
  protected int mDitherType = 0;
  private float mDrawRadius;
  protected Bitmap mMask;
  protected int mMaskHeight;
  protected int mMaskWidth;
  protected Matrix mMatrix = new Matrix();
  protected Random mRandom = new Random();
  protected float mStepFactor = 0.0F;

  public TextureBrush(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }

  protected void drawParticle(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    float f1 = paramParticle.x;
    float f2 = paramParticle.y;
    this.mPaint.setAlpha(paramParticle.alpha);
    int i = PainterUtils.getRandNum(this.mCurParticleIndex);
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
      int m = i % this.mDirectionValue;
      this.mMatrix.setRotate(m, this.mWidth, this.mWidth);
      this.mMatrix.postScale((1.0F + 2.0F * f3) / this.mMaskWidth, (1.0F + 2.0F * f3) / this.mMaskWidth);
    }
    while (true)
    {
      this.mMatrix.postTranslate(f4, f5);
      paramRect.union((int)(f1 - this.mDrawRadius), (int)(f2 - this.mDrawRadius), (int)(f1 + this.mDrawRadius), (int)(f2 + this.mDrawRadius));
      this.mLayerCanvas.save(Canvas.CLIP_SAVE_FLAG);
      this.mLayerCanvas.clipRect(paramRect);
      this.mLayerCanvas.drawBitmap(this.mMask, this.mMatrix, this.mPaint);
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
        int j = (int)(paramParticle.angle + i % this.mDirectionValue);
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
        localRoute = Route.calculateLinePoints(localParticle4, Particle.getMidPoint(localParticle4, localParticle5), this.mStep);
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
          if (this.mEffectType != 1){
            localParticle3.width = 0.0F;
            localParticle3.alpha = this.mAlpha;
          }
        }
      }
    }
    while (true)
    {
      Route localRoute = Route.calculateSmoothLinePoints(localParticle1, localParticle2, localParticle3, this.mStep);
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

  // ERROR //
  protected void loadBrush(int paramInt)
  {
  }

  public void setColor(int paramInt)
  {
    super.setColor(paramInt);
    this.mPaint.setColorFilter(new PorterDuffColorFilter(this.mColor, PorterDuff.Mode.SRC_ATOP));
  }

  public void setDirectionType(int paramInt1, int paramInt2)
  {
    this.mDirectionType = paramInt1;
    this.mDirectionValue = Math.max(1, paramInt2);
  }

  public void setDitherType(int paramInt1, int paramInt2)
  {
    this.mDitherType = paramInt1;
    this.mDitherFactor = Math.max(0, paramInt2);
  }

  public void setWidth(int paramInt)
  {
    super.setWidth(paramInt);
    this.mDitherRadius = Math.max(2, Math.round(this.mWidth * this.mDitherFactor));
    this.mStep = Math.max(3, Math.round(this.mWidth * this.mStepFactor));
    this.mDrawRadius = (2.0F * this.mWidth + Math.round(this.mWidth * this.mDitherFactor));
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.TextureBrush
 * JD-Core Version:    0.6.0
 */