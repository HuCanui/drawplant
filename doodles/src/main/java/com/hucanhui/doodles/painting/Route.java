package com.hucanhui.doodles.painting;

import java.util.ArrayList;

public class Route extends ArrayList<Particle>
{
  public static final int ROUTE_BEGIN_INDEX = 3;
  private static final long serialVersionUID = 2593521039063107713L;
  public long mDrawTime = 0L;

  public Route()
  {
  }

  public Route(int paramInt)
  {
    super(paramInt);
  }

  public static Route calculateLinePoints(Particle paramParticle1, Particle paramParticle2, float paramFloat)
  {
    Route localRoute = new Route();
    if ((paramParticle1 != null) && (paramParticle2 != null))
    {
      int i = (int)(1.0F + PainterUtils.getDistance(paramParticle1.x, paramParticle1.y, paramParticle2.x, paramParticle2.y) / paramFloat);
      float f1 = 0.0F;
      float f2 = 1.0F / i;
      for (int j = 0; j < i; j++)
      {
        Particle localParticle = new Particle();
        float f3 = 1.0F - f1;
        localParticle.x = (f3 * paramParticle1.x + f1 * paramParticle2.x);
        localParticle.y = (f3 * paramParticle1.y + f1 * paramParticle2.y);
        localParticle.width = (f3 * paramParticle1.width + f1 * paramParticle2.width);
        localParticle.alpha = (int)(f3 * paramParticle1.alpha + f1 * paramParticle2.alpha);
        localRoute.add(localParticle);
        f1 += f2;
      }
    }
    return localRoute;
  }

  public static Route calculateSmoothLinePoints(Particle paramParticle1, Particle paramParticle2, Particle paramParticle3, float paramFloat)
  {
    Route localRoute = new Route();
    if ((paramParticle1 != null) && (paramParticle2 != null) && (paramParticle3 != null))
    {
      float f1 = (paramParticle2.x + paramParticle1.x) / 2.0F;
      float f2 = (paramParticle2.y + paramParticle1.y) / 2.0F;
      float f3 = (paramParticle2.x + paramParticle3.x) / 2.0F;
      float f4 = (paramParticle2.y + paramParticle3.y) / 2.0F;
      float f5 = (paramParticle2.width + paramParticle1.width) / 2.0F;
      float f6 = (paramParticle2.width + paramParticle3.width) / 2.0F;
      float f7 = (paramParticle2.alpha + paramParticle1.alpha) / 2;
      float f8 = (paramParticle2.alpha + paramParticle3.alpha) / 2;
      float f9 = PainterUtils.getDistance(f1, f2, f3, f4);
      int i = (int)(f9 / Math.max(1.0F, Math.min(f9, paramFloat)));
      float f10 = 0.0F;
      float f11 = 1.0F / i;
      for (int j = 0; j < i; j++)
      {
        Particle localParticle = new Particle();
        float f12 = 1.0F - f10;
        localParticle.x = (f12 * (f1 * f12) + f10 * (f12 * (2.0F * paramParticle2.x)) + f10 * (f3 * f10));
        localParticle.y = (f12 * (f2 * f12) + f10 * (f12 * (2.0F * paramParticle2.y)) + f10 * (f4 * f10));
        localParticle.width = (f12 * (f5 * f12) + f10 * (f12 * (2.0F * paramParticle2.width)) + f10 * (f6 * f10));
        localParticle.alpha = (int)(f12 * (f7 * f12) + f10 * (f12 * (2 * paramParticle2.alpha)) + f10 * (f8 * f10));
        localRoute.add(localParticle);
        f10 += f11;
      }
    }
    return localRoute;
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.Route
 * JD-Core Version:    0.6.0
 */