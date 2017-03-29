package com.hucanhui.finaldesign.editor;

public class TotalLinkElem {
	public enum LinkType{TEXT,EXPRESSION,BITMAP};
	public TotalLinkElem front;
	public TotalLinkElem next;
	public LinkType linkType;
	public int totalHeight;
	public ElemLink elemLink;
	public TotalLinkElem(TotalLinkElem front, TotalLinkElem next,
			LinkType linkType, int totalHeight, ElemLink elemLink) {
		this.front = front;
		this.next = next;
		this.linkType = linkType;
		this.totalHeight = totalHeight;
		this.elemLink = elemLink;
	}
}
