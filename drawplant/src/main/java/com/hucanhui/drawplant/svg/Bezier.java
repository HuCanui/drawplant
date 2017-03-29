package com.hucanhui.drawplant.svg;

/**
 * Created by hucanhui on 2017/3/25.
 */
public class Bezier {
    public TimedPoint startPoint;
    public TimedPoint control1;
    public TimedPoint control2;
    public TimedPoint endPoint;

    public Bezier set(TimedPoint startPoint, TimedPoint control1,
                      TimedPoint control2, TimedPoint endPoint) {
        this.startPoint = startPoint;
        this.control1 = control1;
        this.control2 = control2;
        this.endPoint = endPoint;
        return this;
    }

    public double point(float t, float start, float c1, float c2, float end) {
        return start * (1.0 - t) * (1.0 - t) * (1.0 - t)
                + 3.0 * c1 * (1.0 - t) * (1.0 - t) * t
                + 3.0 * c2 * (1.0 - t) * t * t
                + end * t * t * t;
    }

}
