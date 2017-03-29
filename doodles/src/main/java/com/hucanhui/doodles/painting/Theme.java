package com.hucanhui.doodles.painting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

public class Theme
{
  public static final int[] ERASER_LIST;
  public static final int THEME_ALL = 16;
  public static final int THEME_DEFAULT = -1;
  public static final Theme[] THEME_LIST;
  public static final int THEME_PAPER_BACK_1 = 0;
  public static final int THEME_PAPER_BACK_10 = 9;
  public static final int THEME_PAPER_BACK_11 = 10;
  public static final int THEME_PAPER_BACK_12 = 11;
  public static final int THEME_PAPER_BACK_13 = 12;
  public static final int THEME_PAPER_BACK_14 = 13;
  public static final int THEME_PAPER_BACK_15 = 14;
  public static final int THEME_PAPER_BACK_16 = 15;
  public static final int THEME_PAPER_BACK_2 = 1;
  public static final int THEME_PAPER_BACK_3 = 2;
  public static final int THEME_PAPER_BACK_4 = 3;
  public static final int THEME_PAPER_BACK_5 = 4;
  public static final int THEME_PAPER_BACK_6 = 5;
  public static final int THEME_PAPER_BACK_7 = 6;
  public static final int THEME_PAPER_BACK_8 = 7;
  public static final int THEME_PAPER_BACK_9 = 8;
  private static SparseArray<Bitmap> sPaperBitmap = new SparseArray();
  public int mId;
  public int mPaper;

  static
  {
    Theme[] arrayOfTheme = new Theme[17];
    arrayOfTheme[0] = new Theme(0, 2130838071);
    arrayOfTheme[1] = new Theme(1, 2130838079);
    arrayOfTheme[2] = new Theme(2, 2130838077);
    arrayOfTheme[3] = new Theme(3, 2130838068);
    arrayOfTheme[4] = new Theme(4, 2130838067);
    arrayOfTheme[5] = new Theme(5, 2130838073);
    arrayOfTheme[6] = new Theme(6, 2130838072);
    arrayOfTheme[7] = new Theme(7, 2130838064);
    arrayOfTheme[8] = new Theme(8, 2130838070);
    arrayOfTheme[9] = new Theme(9, 2130838075);
    arrayOfTheme[10] = new Theme(10, 2130838065);
    arrayOfTheme[11] = new Theme(11, 2130838078);
    arrayOfTheme[12] = new Theme(12, 2130838076);
    arrayOfTheme[13] = new Theme(13, 2130838069);
    arrayOfTheme[14] = new Theme(14, 2130838066);
    arrayOfTheme[15] = new Theme(15, 2130838074);
    arrayOfTheme[16] = new Theme(16, 2130838071);
    THEME_LIST = arrayOfTheme;
    ERASER_LIST = new int[] { 0, 7 };
  }

  public Theme(int paramInt1, int paramInt2)
  {
    this.mId = paramInt1;
    this.mPaper = paramInt2;
  }

  public Bitmap getPaper(Context paramContext)
  {
    synchronized (sPaperBitmap)
    {
      Bitmap localBitmap = (Bitmap)sPaperBitmap.get(this.mPaper);
      if (localBitmap == null)
      {
        localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), this.mPaper);
        sPaperBitmap.put(this.mPaper, localBitmap);
      }
      return localBitmap;
    }
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.painting.Theme
 * JD-Core Version:    0.6.0
 */