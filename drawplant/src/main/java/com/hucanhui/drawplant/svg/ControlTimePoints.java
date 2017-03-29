package com.hucanhui.drawplant.svg;

/**
 * Created by hucanhui on 2017/3/25.
 */
public class ControlTimePoints {
    public TimedPoint c1;
    public TimedPoint c2;

    public ControlTimePoints set(TimedPoint c1, TimedPoint c2) {
        this.c1 = c1;
        this.c2 = c2;
        return this;
    }
}
