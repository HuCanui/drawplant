package com.hucanhui.doodles.painting;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.provider.Settings.System;
import android.util.FloatMath;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;

public class PainterUtils
{
  public static final long LOW_STORAGE_THRESHOLD = 52428800L;
  public static final long PREPARING = -2L;
  public static final long UNAVAILABLE = -1L;
  public static final long UNKNOWN_SIZE = -3L;
  private static final int[] gAlphaCurveTable;
  private static final int[] gRandomTable;
  private static String sExportFilePath;
  private static final SimpleDateFormat sFormater;
  private static int sHasNfc = 0;
  private static final String sIndexFormat = "%s-%03d";
  private static String sSaveFilePath = null;
  private static String sTempFilePath = null;

  static
  {
    sExportFilePath = null;
    sFormater = new SimpleDateFormat("yyyyMMdd-HHmmss");
    gRandomTable = new int[] { -87, 58, -98, 56, 49, -86, -88, 76, 49, 27, 33, -20, -32, 93, -73, -34, -93, 12, -83, 90, 51, 45, -43, 71, -47, -83, -22, 47, 84, 77, 18, 48, 55, 91, -84, 90, -40, -47, 41, -18, 25, -59, -2, 37, -44, -57, -26, 3, -55, 21, -68, 73, 95, -86, 11, -33, 0, -69, 2, 32, 36, -35, 92, 71, -91, 84, 24, -24, 76, -63, -95, 54, 70, 34, -35, 10, -53, 20, 15, -90, 42, 74, 1, -38, 44, 44, 52, 41, -20, 49, -11, -71, 50, -91, 46, -40, -52, 26, 27, -28 };
    gAlphaCurveTable = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 13, 13, 13, 14, 14, 14, 14, 15, 15, 16, 16, 16, 17, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 24, 25, 25, 26, 26, 27, 28, 29, 29, 30, 31, 32, 32, 33, 34, 35, 36, 37, 38, 38, 39, 40, 41, 42, 44, 45, 46, 47, 48, 49, 50, 52, 53, 54, 55, 57, 58, 60, 61, 62, 64, 65, 67, 68, 70, 72, 73, 75, 77, 79, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100, 102, 105, 107, 109, 111, 114, 116, 118, 121, 123, 125, 128, 130, 133, 135, 138, 140, 143, 145, 148, 150, 153, 156, 158, 161, 164, 166, 169, 172, 174, 177, 180, 183, 185, 188, 191, 194, 197, 200, 202, 205, 208, 211, 214, 217, 220, 222, 225, 228, 231, 234, 237, 240, 243, 246, 249, 252, 255 };
    sHasNfc = -1;
  }









  public static float getDistance(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    float f1 = paramFloat1 - paramFloat3;
    float f2 = paramFloat2 - paramFloat4;
    return (float) Math.sqrt(f1 * f1 + f2 * f2);
  }




  public static int getRandNum(int paramInt)
  {
    return gRandomTable[(paramInt % gRandomTable.length)];
  }
}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.painter.utils.PainterUtils
 * JD-Core Version:    0.6.0
 */