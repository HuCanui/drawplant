package com.hucanhui.finaldesign.geometry;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class PentagonGeometry extends BasicGeometry {
	private float[] vertexs;
	private float[] vertexs_dst;
	private Path path = new Path();

	public PentagonGeometry(Paint paint) {
		super(paint);
		float sin18 = (float)Math.sin(Math.PI/10);
		float cos18 = (float)Math.cos(Math.PI/10);
		float sin54 = (float)Math.sin(Math.PI * 0.3);
		float cos54 = (float)Math.cos(Math.PI * 0.3);
		vertexs = new float[10];
		vertexs_dst = new float[10];
		vertexs[0] = width/2;
		vertexs[1] = 0;
		vertexs[2] = width*(1+cos18)/2;
		vertexs[3] = height*(1-sin18)/2;
		vertexs[4] = width*(1+cos54)/2;
		vertexs[5] = height*(1+sin54)/2;
		vertexs[6] = width*(1-cos54)/2;
		vertexs[7] = height*(1+sin54)/2;
		vertexs[8] = width*(1-cos18)/2;
		vertexs[9] = height*(1-sin18)/2;
	}

	@Override
	public void drawGraphic(Canvas canvas) {
		geometryMatrix.mapPoints(vertexs_dst, vertexs);
		path.reset();
		path.moveTo(vertexs_dst[0], vertexs_dst[1]);
		path.lineTo(vertexs_dst[2], vertexs_dst[3]);
		path.lineTo(vertexs_dst[4], vertexs_dst[5]);
		path.lineTo(vertexs_dst[6], vertexs_dst[7]);
		path.lineTo(vertexs_dst[8], vertexs_dst[9]);
		path.close();
		canvas.drawPath(path, paint);
	}

}
