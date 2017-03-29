package com.hucanhui.doodles.painting;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.AbsListView;
import java.io.Closeable;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

public class Utils
{
  private static final int DATE_STRING_FLAGS = 65556;
  private static final String[] FILE_PATH_PROJ;
  public static boolean SAVE_LOG_TO_FILE = false;
  private static final int SCREEN_WIDTH_OF_M040 = 800;
  public static boolean SHOW_LOG = false;
  private static final String TAG = "Utils";
  private static String sAppVersionCode;
  private static Collator sBetterCompareCollator;
  private static String sDurationFormatLong;
  private static String sDurationFormatShort;
  private static StringBuilder sFormatBuilder;
  private static Formatter sFormatter;
  private static final Method sGetStorageDirMethod;
  private static int sInternational;
  private static String sIsChinaMobile;
  private static boolean sIsChineseLang;
  private static String sIsFlymeRom;
  private static String sIsHifi;
  private static String sIsMeizuM71;
  private static String sIsMeizuM79;
  private static String sIsShopDemo;
  private static String sLogFileLocation;
  private static String sNetworkOperator;
  private static String sPhoneIMEI;
  private static String sPhoneSn;
  private static final DecimalFormat sPriceFormatter;
  private static final DecimalFormat sPriceWithCentFormatter;
  private static float sRatio;
  private static Object[] sTimeArgs;

  static
  {
    SAVE_LOG_TO_FILE = false;
    sGetStorageDirMethod = getGetStorageDirMethod();
    FILE_PATH_PROJ = new String[] { "_data" };
    sRatio = 1.0F;
    sLogFileLocation = null;
    sPriceFormatter = new DecimalFormat("#0.0");
    sPriceWithCentFormatter = new DecimalFormat("#0.00");
    sBetterCompareCollator = null;
    sIsChineseLang = true;
    sPhoneSn = null;
    sInternational = -1;
    sIsShopDemo = null;
    sIsFlymeRom = null;
    sIsChinaMobile = null;
    sIsMeizuM71 = null;
    sIsMeizuM79 = null;
    sIsHifi = null;
  }



  public static float clamp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 > paramFloat3)
      return paramFloat3;
    if (paramFloat1 < paramFloat2)
      return paramFloat2;
    return paramFloat1;
  }

  public static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 > paramInt3)
      return paramInt3;
    if (paramInt1 < paramInt2)
      return paramInt2;
    return paramInt1;
  }





  private static Method getGetStorageDirMethod()
  {
    try
    {
      Method localMethod = Environment.class.getMethod("getExternalStorageDirectoryMzInternal", new Class[0]);
      return localMethod;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

}

/* Location:           C:\Users\11022\Desktop\classes_dex2jar.jar
 * Qualified Name:     com.meizu.media.common.utils.Utils
 * JD-Core Version:    0.6.0
 */