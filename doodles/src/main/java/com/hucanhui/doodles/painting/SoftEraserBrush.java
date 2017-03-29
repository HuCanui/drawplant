package com.hucanhui.doodles.painting;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Paint;

public class SoftEraserBrush extends EraserBrush
{
  protected SoftEraserBrush(Context paramContext)
  {
    super(paramContext, 7);
    BlurMaskFilter localBlurMaskFilter = new BlurMaskFilter(15.0F, Blur.NORMAL);
    this.mPaint.setMaskFilter(localBlurMaskFilter);
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.SoftEraserBrush
 * JD-Core Version:    0.6.0
 */