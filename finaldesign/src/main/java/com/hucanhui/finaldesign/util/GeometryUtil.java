package com.hucanhui.finaldesign.util;

import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.geometry.BasicGeometry;
import com.hucanhui.finaldesign.geometry.DiamondGeometry;
import com.hucanhui.finaldesign.geometry.HexagonGeometry;
import com.hucanhui.finaldesign.geometry.LineGeometry;
import com.hucanhui.finaldesign.geometry.OvalGeometry;
import com.hucanhui.finaldesign.geometry.PentagonGeometry;
import com.hucanhui.finaldesign.geometry.RectangleGeometry;
import com.hucanhui.finaldesign.geometry.RightTriangleGeometry;
import com.hucanhui.finaldesign.geometry.RoundedRectangleGeometry;
import com.hucanhui.finaldesign.geometry.StarGeometry;
import com.hucanhui.finaldesign.geometry.TriangleGeometry;
import com.hucanhui.finaldesign.view.DrawView;

public class GeometryUtil {
	private enum GraphicType{LINE, OVAL, RECTANGLE, ROUNDEDRECTANGLE, TRIANGLE, RIGHTTRIANGLE,
		DIAMOND, PENTAGON, HEXAGON, STAR};
	private DrawActivity drawActivity;
	private DrawView drawView;
	
	private ImageView selectedGraphic;
	private GraphicType graphicType;
	private ImageView selectedColor;
	private SeekBar sizeSeekBar;
	private CheckBox strokeCheckBox;
	private ColorPickerDialog colorPickerDialog;
	
	public GeometryUtil(DrawActivity drawActivity,DrawView drawView){
		this.drawActivity = drawActivity;
		this.drawView = drawView;	
		selectedGraphic = (ImageView)drawActivity.findViewById(R.id.graphic_line);
		graphicType = GraphicType.LINE;
		selectedGraphic.setBackgroundColor(DrawAttribute.backgroundOnClickColor);
		selectedColor = (ImageView)drawActivity.findViewById(R.id.graphiccolorview);
		sizeSeekBar = (SeekBar)drawActivity.findViewById(R.id.graphicsizeskb);
		strokeCheckBox = (CheckBox)drawActivity.findViewById(R.id.strokckb);
	    colorPickerDialog = new ColorPickerDialog(drawActivity,selectedColor);
	}
	
	public void graphicPicSetOnClickListener() {
		ImageView[] graphics = new ImageView[10];
		graphics[0] = (ImageView)drawActivity.findViewById(R.id.graphic_line);
		graphics[1] = (ImageView)drawActivity.findViewById(R.id.graphic_oval);
		graphics[2] = (ImageView)drawActivity.findViewById(R.id.graphic_rectangle);
		graphics[3] = (ImageView)drawActivity.findViewById(R.id.graphic_roundedrectangle);
		graphics[4] = (ImageView)drawActivity.findViewById(R.id.graphic_triangle);
		graphics[5] = (ImageView)drawActivity.findViewById(R.id.graphic_righttriangle);
		graphics[6] = (ImageView)drawActivity.findViewById(R.id.graphic_diamond);
		graphics[7] = (ImageView)drawActivity.findViewById(R.id.graphic_pentagon);
		graphics[8] = (ImageView)drawActivity.findViewById(R.id.graphic_hexagon);
		graphics[9] = (ImageView)drawActivity.findViewById(R.id.graphic_star);
		GraphicOnClickListener graphicOnTouchListener = new GraphicOnClickListener(graphics);
		for(int i = 0;i < graphics.length;i++) {
			graphics[i].setOnClickListener(graphicOnTouchListener);
		}	
		Button chooseColorButton = (Button)drawActivity.findViewById(R.id.graphiccolorbtn);
		chooseColorButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {			
        		colorPickerDialog.getDialog().show();
			}
			
		});	
		Button startDrawingbutton = (Button)drawActivity.findViewById(R.id.graphicdrawbtn);
		startDrawingbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {			
				drawView.setBasicGeometry(GetGraphicClass(graphicType));
			}
			
		});
	}
	
	class GraphicOnClickListener implements OnClickListener {
		private ImageView[] graphics;
		
		public GraphicOnClickListener(ImageView[] graphics) {
			this.graphics = graphics;
		}

		@Override
		public void onClick(View v) {
			selectedGraphic.setBackgroundColor(0x00000000);
			switch(v.getId()) {
			case R.id.graphic_line:
				selectedGraphic = graphics[0];
				graphicType = GraphicType.LINE;break;
			case R.id.graphic_oval:
				selectedGraphic = graphics[1];
				graphicType = GraphicType.OVAL;break;
			case R.id.graphic_rectangle:
				selectedGraphic = graphics[2];
				graphicType = GraphicType.RECTANGLE;break;
			case R.id.graphic_roundedrectangle:
				selectedGraphic = graphics[3];
				graphicType = GraphicType.ROUNDEDRECTANGLE;break;
			case R.id.graphic_triangle:
				selectedGraphic = graphics[4];
				graphicType = GraphicType.TRIANGLE;break;
			case R.id.graphic_righttriangle:
				selectedGraphic = graphics[5];
				graphicType = GraphicType.RIGHTTRIANGLE;break;
			case R.id.graphic_diamond:
				selectedGraphic = graphics[6];
				graphicType = GraphicType.DIAMOND;break;
			case R.id.graphic_pentagon:
				selectedGraphic = graphics[7];
				graphicType = GraphicType.PENTAGON;break;
			case R.id.graphic_hexagon:
				selectedGraphic = graphics[8];
				graphicType = GraphicType.HEXAGON;break;
			case R.id.graphic_star:
				selectedGraphic = graphics[9];
				graphicType = GraphicType.STAR;
			}
			selectedGraphic.setBackgroundColor(DrawAttribute.backgroundOnClickColor);
		}		
	}
	
	private BasicGeometry GetGraphicClass(GraphicType graphicType) {
		Paint paint = new Paint();
		paint.setColor(colorPickerDialog.getColor());
		paint.setStrokeWidth(sizeSeekBar.getProgress());
		if(strokeCheckBox.isChecked())paint.setStyle(Style.STROKE);
		if(graphicType == GraphicType.LINE)return new LineGeometry(paint);
		if(graphicType == GraphicType.OVAL)return new OvalGeometry(paint);
		if(graphicType == GraphicType.RECTANGLE)return new RectangleGeometry(paint);
		if(graphicType == GraphicType.ROUNDEDRECTANGLE)return new RoundedRectangleGeometry(paint);
		if(graphicType == GraphicType.TRIANGLE)return new TriangleGeometry(paint);
		if(graphicType == GraphicType.RIGHTTRIANGLE)return new RightTriangleGeometry(paint);
		if(graphicType == GraphicType.DIAMOND)return new DiamondGeometry(paint);
		if(graphicType == GraphicType.PENTAGON)return new PentagonGeometry(paint);
		if(graphicType == GraphicType.HEXAGON)return new HexagonGeometry(paint);
		if(graphicType == GraphicType.STAR)return new StarGeometry(paint);
		return null;
	 }
}
