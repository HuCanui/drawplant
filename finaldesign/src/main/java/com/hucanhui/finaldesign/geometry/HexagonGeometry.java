package com.hucanhui.finaldesign.geometry;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class HexagonGeometry extends BasicGeometry {
	private float[] vertexs;
	private float[] vertexs_dst;
	private Path path = new Path();

	public HexagonGeometry(Paint paint) {
		super(paint);
		float t = (float)(Math.sqrt(3)/2);
		vertexs = new float[12];
		vertexs_dst = new float[12];
		vertexs[0] = width/2;
		vertexs[1] = height;
		vertexs[2] = width*(1 + t)/2;
		vertexs[3] = height*3/4;	
		vertexs[4] = vertexs[2];
		vertexs[5] = height/4;
		vertexs[6] = vertexs[0];
		vertexs[7] = 0;
		vertexs[8] = width*(1-t)/2;
		vertexs[9] = vertexs[5];
		vertexs[10] = vertexs[8];
		vertexs[11] = vertexs[3];	
		
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
		path.close();
		canvas.drawPath(path, paint);
	}

}
