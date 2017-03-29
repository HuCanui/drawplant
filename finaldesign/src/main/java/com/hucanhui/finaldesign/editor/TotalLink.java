package com.hucanhui.finaldesign.editor;
//javaapk.com�ṩ����
import java.util.ArrayList;

import com.hucanhui.finaldesign.editor.TotalLinkElem.LinkType;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.FontMetrics;

public class TotalLink {
	private TotalLinkElem root;
	private int textHeight;
	private int lineWidth;
	private TotalLinkElem currentLinkElem;
	private Point currentPointer;
	private Paint textPaint;
	private Point cursor;
	
	public TotalLink(int lineWidth, Paint textPaint){
		root = new TotalLinkElem(null,null,
				TotalLinkElem.LinkType.TEXT,
				textHeight,new TextLink());
		currentLinkElem = root;
		currentPointer = new Point(0,0);
		this.lineWidth = lineWidth;
		this.textPaint = textPaint;
		FontMetrics fm = textPaint.getFontMetrics();		
		textHeight = (int)Math.ceil(fm.descent - fm.top) + 2;
		cursor = new Point(0,0);
	}
	
	private ArrayList<ArrayList<Char> > arrangeLine(ArrayList<Char> source) {
		ArrayList<ArrayList<Char> > target = new ArrayList<ArrayList<Char> >();
		int width = 0;
		ArrayList<Char> arrayList = new ArrayList<Char>();
		for(int i = 0; i < source.size(); i++) {
			Char  c = source.get(i);
			int charWidth = c.width;
			if(width + charWidth < lineWidth) {
				arrayList.add(c);
				width += charWidth;
			}
			else {
				target.add(arrayList);
				arrayList = new ArrayList<Char>();
				arrayList.add(c);
				width = charWidth;
			}
		}
		target.add(arrayList);
		return target;
	}
	
	//��ǰ��������ɾ����
	public void deleteAtCurrentPointer() {
		if(currentLinkElem.linkType == LinkType.TEXT) {
			ArrayList<ArrayList<Char> > lines = ((TextLink)currentLinkElem.elemLink).text;
			if(currentPointer.equals(0, 0)) {
				if(currentLinkElem != root) {
					if(currentLinkElem.front.linkType == LinkType.TEXT) {
						ArrayList<ArrayList<Char> > frontLines = ((TextLink)currentLinkElem.front.
								                                            elemLink).text;
						ArrayList<Char> frontLastLine = frontLines.get(frontLines.size() - 1);
						//����cursorPointer
						currentPointer.x = frontLines.size() - 1;
						currentPointer.y = frontLastLine.size();
						ArrayList<Char> source = new ArrayList<Char>();
						for(int i = 0; i < frontLastLine.size(); i++) {
							source.add(frontLastLine.get(i));
						}
						for(int i = 0; i < lines.size(); i++) {
							ArrayList<Char> line = lines.get(i);
							for(int j = 0; j < line.size(); j++) {
								source.add(line.get(j));
							}
						}
						ArrayList<ArrayList<Char> > target = this.arrangeLine(source);
						frontLines.set(frontLines.size() - 1,target.get(0));
						for(int i = 1; i < target.size(); i++) {
							frontLines.add(target.get(i));
						}
						
						currentLinkElem = currentLinkElem.front;
						currentLinkElem.next = currentLinkElem.next.next;
						if(currentLinkElem.next != null) 
							currentLinkElem.next.front = currentLinkElem;					
						currentLinkElem.totalHeight = textHeight * frontLines.size();										
					}
				}		
			}
			else {
				if(currentPointer.y == 0) {
					currentPointer.x--;
					currentPointer.y = lines.get(currentPointer.x).size();
				}
				currentPointer.y--;
				//ɾ�����ַ�
				ArrayList<Char> currentLine = lines.get(currentPointer.x);
				currentLine.remove(currentPointer.y);
				//���±���
				ArrayList<Char> source = new ArrayList<Char>();
				for(int i = currentPointer.x; i < lines.size(); i++) {
					ArrayList<Char> line = lines.get(i);
					for(int j = 0; j < line.size(); j++) {
						source.add(line.get(j));
					}
				}
				if(source.size() == 0 && currentPointer.x != 0) {
					lines.remove(currentPointer.x);
					currentPointer.x--;
					currentPointer.y = lines.get(currentPointer.x).size();
				}
				else {
					ArrayList<ArrayList<Char> > target = this.arrangeLine(source);
					int j = currentPointer.x;
					for(int i = 0; i < target.size(); i++) {
						lines.set(j, target.get(i));
						j++;
					}
					while(j < lines.size()){
						lines.remove(j);
					}
				}
				currentLinkElem.totalHeight = lines.size() * textHeight;
			}
			setCursorFromCurrentPointer();
		}
	}
	
	//��ǰ�������ǻس���
	public void splitAtCurrentPointer() {
		if(currentLinkElem.linkType == LinkType.TEXT) {
			ArrayList<ArrayList<Char> > newLines = new ArrayList<ArrayList<Char> >();
			ArrayList<ArrayList<Char> > lines = ((TextLink)currentLinkElem.elemLink).text;
			ArrayList<Char> currentLine = lines.get(currentPointer.x);
			ArrayList<Char> lastLine = lines.get(lines.size() - 1);
			//����λ������
			if(currentPointer.equals(0, 0)) {
				newLines.add(new ArrayList<Char>());
				TextLink newTextLink = new TextLink(newLines);
				TotalLinkElem newTotalLinkElem = new TotalLinkElem
				                (null,null,LinkType.TEXT,textHeight,newTextLink);
				if(currentLinkElem == root) {
					root = newTotalLinkElem;
					newTotalLinkElem.next = currentLinkElem;
					currentLinkElem.front = newTotalLinkElem;
				}
				else {
					currentLinkElem.front.next = newTotalLinkElem;
					newTotalLinkElem.front = currentLinkElem.front;
					newTotalLinkElem.next = currentLinkElem;
					currentLinkElem.front = newTotalLinkElem;
				}
			}
			else {
				//����λ�ڶ���ĩβ
				if(currentPointer.x == lines.size() -1 &&
						currentPointer.y == lastLine.size()) {
					newLines.add(new ArrayList<Char>());					
				}
				//�ָ�������׻�����β
				else if(currentPointer.y == 0 || 
						currentLine.size() == currentPointer.y) {
					currentPointer.x += currentPointer.y / currentLine.size();
					currentPointer.y %= currentLine.size();
					for(int i = lines.size()-1; i >= currentPointer.x; i--) {
						newLines.add(0, lines.remove(i));
					}
					//����currentTextLink�ĸ߶�
					currentLinkElem.totalHeight = lines.size() * textHeight;
				}
				//�ָ�����м䣬ǰ��һ����Ԫ��
				else {
					ArrayList<Char> source = new ArrayList<Char>();
					for(int i = currentLine.size() -1; i >= currentPointer.y; i--){
						source.add(0, currentLine.remove(i));
					}
					for(int i = currentPointer.x + 1; i < lines.size();) {
						ArrayList<Char> line = lines.remove(i);
						for(int j = 0; j < line.size(); j++) {
							source.add(line.get(j));
						}
					}
					newLines = this.arrangeLine(source);
					currentLinkElem.totalHeight = lines.size() * textHeight;
				}
				TextLink newTextLink = new TextLink(newLines);
				TotalLinkElem newTotalLinkElem = new TotalLinkElem
                (null,null,LinkType.TEXT,textHeight* newLines.size(),newTextLink);
				if(currentLinkElem.next == null) {
					currentLinkElem.next = newTotalLinkElem;
					newTotalLinkElem.front = currentLinkElem;
				}
				else {
					newTotalLinkElem.front = currentLinkElem;
					newTotalLinkElem.next = currentLinkElem.next;
					currentLinkElem.next.front = newTotalLinkElem;
					currentLinkElem.next = newTotalLinkElem;
				}
				currentLinkElem = newTotalLinkElem;
				currentPointer.x = 0; currentPointer.y = 0;
			}
			setCursorFromCurrentPointer();
		}
	}
	
	public void addCharsAtCurrent(Char[] c) {
		if(currentLinkElem.linkType == LinkType.TEXT) {
			TextLink textLink= (TextLink)currentLinkElem.elemLink;
			//���뵽��ǰ��link��
			for(int h = c.length - 1; h >= 0; h--) {
				textLink.text.get(currentPointer.x).add(currentPointer.y, c[h]);
			}
			//����ǰ���Ժ����ȫ��ȡ������
			ArrayList<Char> source = new ArrayList<Char>();
			for(int i = currentPointer.x; i < textLink.text.size(); i++) {
				ArrayList<Char> line = textLink.text.get(i);
				for(int j = 0; j < line.size(); j++) {
					source.add(line.get(j));
				}
			}
			ArrayList<ArrayList<Char> > target = arrangeLine(source);
			//���ȥ
			int j = 0;
			for(int i = currentPointer.x; i < textLink.text.size(); i++) {
				textLink.text.set(i, target.get(j));
				j++;
			}
			for(;j < target.size(); j++) {
				textLink.text.add(target.get(j));
			}
			//����currentPointer
			for(int i = 0 ; i < c.length; i++){
				currentPointer.y++;
				int size = textLink.text.get(currentPointer.x).size();
				if(currentPointer.y > size) {
					currentPointer.x++;
					currentPointer.y -= size;
					
				}
			}
			//����Link�ĸ߶�
			currentLinkElem.totalHeight = textLink.text.size() * textHeight;
			//����cursor
			setCursorFromCurrentPointer();
		}
		else if(currentLinkElem.linkType == LinkType.BITMAP) {			
		}
	}
	
	private void setCursorFromCurrentPointer() {
		TotalLinkElem totalLinkElem = root;
		int paintX = 0;
		int paintY = 0;
		while(totalLinkElem != null && totalLinkElem != currentLinkElem) {
			paintY += totalLinkElem.totalHeight;
			totalLinkElem = totalLinkElem.next;
		}
		if(currentLinkElem.linkType == LinkType.TEXT) {
			ArrayList<ArrayList<Char> > lines = ((TextLink)currentLinkElem.elemLink).text;
			for(int i = 0; i < currentPointer.x; i++) {
				paintY += textHeight;
			}
			cursor.y = paintY;
			ArrayList<Char> line = lines.get(currentPointer.x);
			for(int i = 0; i < currentPointer.y; i++) {
				paintX += line.get(i).width;
			}
			cursor.x = paintX;
		}
	}
	
	public void setCurrentPointerFromPoint(int x, int y) {
		boolean isHandle = false;
		TotalLinkElem preTotalLinkElem = null;
		TotalLinkElem totalLinkElem = root;
		int paintX = 0;
		int paintY = 0;
		while(totalLinkElem != null && !isHandle) {
			if(y >= paintY && y < totalLinkElem.totalHeight + paintY ) {
				currentLinkElem = totalLinkElem;
				if(currentLinkElem.linkType == LinkType.TEXT) {
					ArrayList<ArrayList<Char> > lines = ((TextLink)currentLinkElem.elemLink).text;
					for(int i = 0; i < lines.size(); i++) {
						if(y >= paintY && y < textHeight + paintY) {
							currentPointer.x = i;
							break;
						}
						else {
							paintY += textHeight;
						}
					}
					ArrayList<Char> line = lines.get(currentPointer.x);
					for(int i = 0; i < line.size();i++) {
						int width = line.get(i).width;
						if(x>= paintX && x < paintX + width/2) {
							currentPointer.y = i;
							isHandle = true;
							break;
						}
						else if(x>= paintX + width/2 && x < paintX + width) {
							currentPointer.y = i + 1;
							isHandle = true;
							break;
						}
						else {
							paintX += width;
						}
					}
					if(!isHandle) {
						currentPointer.y = line.size();
						isHandle = true;
					}
				}
			}
			else {
				paintY += totalLinkElem.totalHeight;
			}
			preTotalLinkElem = totalLinkElem;
			totalLinkElem = totalLinkElem.next;
		}
		if(!isHandle) {
			currentLinkElem = preTotalLinkElem;
			if(currentLinkElem.linkType == LinkType.TEXT) {
				ArrayList<ArrayList<Char> > lines = ((TextLink)currentLinkElem.elemLink).text;
				ArrayList<Char> line = lines.get(lines.size()-1);
				currentPointer.set(lines.size()-1, line.size());
			}
		}
		//����Cursor
		setCursorFromCurrentPointer();
	}
	
	public void drawText(Canvas canvas) {
		TotalLinkElem totalLinkElem = root;
		int paintX = 0;
		int paintY = textHeight;
		while(totalLinkElem != null) {
			if(totalLinkElem.linkType == LinkType.TEXT) {
				ArrayList<ArrayList<Char> >lines = ((TextLink)totalLinkElem.elemLink).text;
				for(int i = 0; i < lines.size(); i++) {
					ArrayList<Char> line = lines.get(i);
					String s = "";
					for(int j = 0; j < line.size(); j++) {
						s += String.valueOf(line.get(j).c);
					}
					canvas.drawText(s, paintX, paintY, textPaint);
					paintY += textHeight;
				}
			}
			totalLinkElem  = totalLinkElem.next;
		}
		canvas.drawLine(cursor.x, cursor.y, cursor.x, cursor.y + textHeight, textPaint);
	}
}
