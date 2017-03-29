package com.hucanhui.finaldesign.editor;
//javaapk.com�ṩ����
import android.graphics.Paint;

public class Char {
	public int width;
	public char c;
	public Char(char c, Paint paint) {
		this.c = c;
		float[] widths = new float[1];
		String srt = String.valueOf(c);
		paint.getTextWidths(srt, widths);
		this.width = (int) (Math.ceil(widths[0]));
	}	
}
