package com.hucanhui.drawplant.svg;

/**
 * Created by hucanhui on 2017/3/27.
 */
public class SvgFile {

    private final StringBuilder svgCircleBuilder = new StringBuilder();
    public String build() {
        return (new StringBuilder())
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n")
                .append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.2\" width=\"100%\" height=\"100%\">")
                .append(svgCircleBuilder)
                .append("</svg>")
                .toString();
    }


    public void appent(String circle){
        svgCircleBuilder.append(circle);
    }
}
