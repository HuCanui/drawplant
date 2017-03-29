package com.hucanhui.drawplant.svg;

/**
 * Created by hucanhui on 2017/3/27.
 */
public class SvgCircleBuilder {

    private float cx;
    private float cy;
    private float radius;
    private String color;
    private float opacity;

    public SvgCircleBuilder(float cx, float cy, float radius, float opacity, String color){
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
        this.opacity = opacity;
    }

    @Override
    public String toString() {
        return (new StringBuilder())
                .append("<circle ")
                .append("style=\"opacity:")
                .append(opacity)
                .append("\" ")
                .append("cx=\"")
                .append(cx)
                .append("\" ")
                .append("cy=\"")
                .append(cy)
                .append("\" ")
                .append("r=\"")
                .append(radius)
                .append("\" ")
                .append("fill=\"#")
                .append(color)
                .append("\"/>")
                .toString();
    }
}
