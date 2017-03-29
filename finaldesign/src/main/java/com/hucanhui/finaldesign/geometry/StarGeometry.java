package com.hucanhui.finaldesign.geometry;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class StarGeometry extends BasicGeometry {
	private float[] vertexs;
	private float[] vertexs_dst;
	private Path path = new Path();

	public StarGeometry(Paint paint) {
		super(paint);
		float tan18 = (float)Math.tan(Math.PI/10);
		float sin18 = (float)Math.sin(Math.PI/10);
		float cos18 = (float)Math.cos(Math.PI/10);
		float sin54 = (float)Math.sin(Math.PI * 0.3);
		float cos54 = (float)Math.cos(Math.PI * 0.3);
		float t = (1 + tan18 * tan18)/(3 - tan18 * tan18);
		float tsin18 = t * sin18;
		float tcos18 = t * cos18;
		float tsin54 = t * sin54;
		float tcos54 = t * cos54;
		vertexs = new float[20];
		vertexs_dst = new float[20];
		vertexs[0] = width/2;
		vertexs[1] = 0;
		vertexs[2] = width/2*(1+tcos54);
		vertexs[3] = height/2*(1-tsin54);
		vertexs[4] = width/2*(1+cos18);
		vertexs[5] = height/2*(1-sin18);
		vertexs[6] = width/2*(1+tcos18);
		vertexs[7] = height/2*(1+tsin18);
		vertexs[8] = width/2*(1+cos54);
		vertexs[9] = height/2*(1+sin54);
		vertexs[10] = width/2;
		vertexs[11] = height/2*(1+t);
		vertexs[12] = width/2*(1-cos54);
		vertexs[13] = vertexs[9];
		vertexs[14] = width/2*(1-tcos18);
		vertexs[15] = vertexs[7];
		vertexs[16] = width/2*(1-cos18);
		vertexs[17] = vertexs[5];
		vertexs[18] = width/2*(1-tcos54);
		vertexs[19] = vertexs[3];
												
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
		path.lineTo(vertexs_dst[10], vertexs_dst[11]);
		path.lineTo(vertexs_dst[12], vertexs_dst[13]);
		path.lineTo(vertexs_dst[14], vertexs_dst[15]);
		path.lineTo(vertexs_dst[16], vertexs_dst[17]);
		path.lineTo(vertexs_dst[18], vertexs_dst[19]);
		path.close();
		canvas.drawPath(path, paint);
	}

}
