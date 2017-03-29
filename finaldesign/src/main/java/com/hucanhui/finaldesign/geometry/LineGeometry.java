package com.hucanhui.finaldesign.geometry;

import android.graphics.Canvas;
import android.graphics.Paint;

public class LineGeometry extends BasicGeometry {
	private float[] vertexs;
	private float[] vertexs_dst;
	
	public LineGeometry(Paint paint) {
		super(paint);
		vertexs = new float[4];
		vertexs_dst = new float[4];
		vertexs[0] = 0;
		vertexs[1] = 0;
		vertexs[2] = width;
		vertexs[3] = height;
	}

	@Override
	public void drawGraphic(Canvas canvas) {
		geometryMatrix.mapPoints(vertexs_dst,vertexs);
		canvas.drawLine(vertexs_dst[0], vertexs_dst[1], vertexs_dst[2], vertexs_dst[3], paint);
	}

}
