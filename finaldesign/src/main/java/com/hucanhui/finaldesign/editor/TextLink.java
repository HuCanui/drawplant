package com.hucanhui.finaldesign.editor;

import java.util.ArrayList;

public class TextLink implements ElemLink {
	public ArrayList<ArrayList<Char> > text;
	
	public TextLink(){
		text = new ArrayList<ArrayList<Char> >();
		text.add(new ArrayList<Char>());
	}

	public TextLink(ArrayList<ArrayList<Char>> text) {
		this.text = text;
	}
}
