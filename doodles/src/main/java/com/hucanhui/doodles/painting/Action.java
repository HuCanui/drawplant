package com.hucanhui.doodles.painting;

public class Action
{
  public static final int ACTION_TYPE_CHANGE_ALPHA = 3;
  public static final int ACTION_TYPE_CHANGE_BRUSH = 0;
  public static final int ACTION_TYPE_CHANGE_COLOR = 2;
  public static final int ACTION_TYPE_CHANGE_WIDTH = 1;
  public static final int ACTION_TYPE_DRAW = 5;
  public int mType;
  public int mValue;

  public Action()
  {
  }

  public Action(int paramInt1, int paramInt2)
  {
    this.mType = paramInt1;
    this.mValue = paramInt2;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(this.mType);
    arrayOfObject[1] = Integer.valueOf(this.mValue);
    return String.format("[%d, %d]", arrayOfObject);
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.Action
 * JD-Core Version:    0.6.0
 */