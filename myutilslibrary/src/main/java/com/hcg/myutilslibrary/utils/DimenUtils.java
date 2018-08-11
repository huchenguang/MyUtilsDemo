package com.hcg.myutilslibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author yuyh.
 * @date 16/4/10.
 */
public class DimenUtils {

    public enum EScreenDensity {
        XXHDPI,    //超高分辨率    1080×1920
        XHDPI,    //超高分辨率    720×1280
        HDPI,    //高分辨率         480×800
        MDPI,    //中分辨率         320×480
    }

    public static EScreenDensity getDisply(Context context) {
        EScreenDensity eScreenDensity;
        //初始化屏幕密度
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int densityDpi = dm.densityDpi;

        if (densityDpi <= 160) {
            eScreenDensity = EScreenDensity.MDPI;
        } else if (densityDpi <= 240) {
            eScreenDensity = EScreenDensity.HDPI;
        } else if (densityDpi < 400) {
            eScreenDensity = EScreenDensity.XHDPI;
        } else {
            eScreenDensity = EScreenDensity.XXHDPI;
        }
        return eScreenDensity;
    }

    /**
     * 将dp转换成px
     *
     * @param dp
     * @return
     */
    public static float dpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    /**
     * 将px转换成dp
     *
     * @param px
     * @return
     */
    public static float pxToDp(Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int pxToDpInt(Context context, float px) {
        return (int) (pxToDp(context, px) + 0.5f);
    }

    /**
     * 将px值转换为sp值
     *
     * @param pxValue
     * @return
     */
    public static float pxToSp(Context context, float pxValue) {
        return pxValue / context.getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * 将sp值转换为px值
     *
     * @param spValue
     * @return
     */
    public static float spToPx(Context context, float spValue) {
        return spValue * context.getResources().getDisplayMetrics().scaledDensity;
    }
}
