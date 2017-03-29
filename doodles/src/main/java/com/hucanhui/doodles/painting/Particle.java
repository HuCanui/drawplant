package com.hucanhui.doodles.painting;

public class Particle
{
  public int alpha;
  public float angle;
  public float width;
  public float x;
  public float y;

  public Particle()
  {
  }

  public Particle(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.angle = paramFloat3;
  }

  public static Particle getMidPoint(Particle paramParticle1, Particle paramParticle2)
  {
    Particle localParticle = new Particle();
    localParticle.x = ((paramParticle1.x + paramParticle2.x) / 2.0F);
    localParticle.y = ((paramParticle1.y + paramParticle2.y) / 2.0F);
    localParticle.width = ((paramParticle1.width + paramParticle2.width) / 2.0F);
    localParticle.alpha = ((paramParticle1.alpha + paramParticle2.alpha) / 2);
    localParticle.angle = ((paramParticle1.angle + paramParticle2.angle) / 2.0F);
    return localParticle;
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.Particle
 * JD-Core Version:    0.6.0
 */