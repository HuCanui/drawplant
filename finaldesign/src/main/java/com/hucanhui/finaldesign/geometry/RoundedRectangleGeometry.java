package com.hucanhui.finaldesign.geometry;
//javaapk.com�ṩ����
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class RoundedRectangleGeometry extends BasicGeometry {
	private float[] vertexs;
	private float[] vertexs_dst ;
	private RectF rectF;
	private float centerPointX;
	private float centerPointY;
	private float width_dst;
	private float height_dst;
	private float rotation;

	public RoundedRectangleGeometry(Paint paint) {
		super(paint);
		vertexs = new float[6];
		vertexs_dst = new float[6];
		rectF = new RectF();
		vertexs[0] = 0;
		vertexs[1] = 0;
		vertexs[2] = width;
		vertexs[3] = 0;
		vertexs[4] = width;
		vertexs[5] = height;
	}

	@Override
	public void drawGraphic(Canvas canvas) {
		geometryMatrix.mapPoints(vertexs_dst,vertexs);
		centerPointX = (vertexs_dst[0] + vertexs_dst[4])/2;
		centerPointY = (vertexs_dst[1] + vertexs_dst[5])/2;
		rotation = rotation(vertexs_dst[0], vertexs_dst[1], vertexs_dst[2], vertexs_dst[3]);		
		width_dst = (float)Math.sqrt((vertexs_dst[3]-vertexs_dst[1])*(vertexs_dst[3] - vertexs_dst[1]) +
				(vertexs_dst[2]-vertexs_dst[0])*(vertexs_dst[2] - vertexs_dst[0]));
		height_dst = (float)Math.sqrt((vertexs_dst[5]-vertexs_dst[3])*(vertexs_dst[5] - vertexs_dst[3]) +
				(vertexs_dst[4]-vertexs_dst[2])*(vertexs_dst[4] - vertexs_dst[2]));
		rectF.set(centerPointX - width_dst/2, centerPointY - height_dst/2,
				centerPointX + width_dst/2, centerPointY + height_dst/2);
		canvas.save();
		canvas.rotate(rotation, centerPointX, centerPointY);
		canvas.drawRoundRect(rectF, 20, 20, paint);
		canvas.restore();
	}

}
