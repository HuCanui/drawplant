package com.hucanhui.doodles.painting;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class PenBrush extends Brush
{
  private static final float PEN_BRUSH_BLUR_RADIUS = 1.0F;

  protected PenBrush(Context paramContext)
  {
    super(paramContext, 1);
    this.mEffectType = 1;
    BlurMaskFilter localBlurMaskFilter = new BlurMaskFilter(1.0F, Blur.SOLID);
    this.mPaint.setMaskFilter(localBlurMaskFilter);
  }

  protected void drawParticle(Canvas paramCanvas, Particle paramParticle, Rect paramRect)
  {
    int i = (int)(1.0F + paramParticle.width);
    paramRect.union((int)(paramParticle.x - i), (int)(paramParticle.y - i), (int)(paramParticle.x + i), (int)(paramParticle.y + i));
    this.mLayerCanvas.drawCircle(paramParticle.x, paramParticle.y, paramParticle.width, this.mPaint);
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.PenBrush
 * JD-Core Version:    0.6.0
 */