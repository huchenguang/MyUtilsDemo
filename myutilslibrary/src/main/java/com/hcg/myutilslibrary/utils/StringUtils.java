package com.hcg.myutilslibrary.utils;

import java.text.NumberFormat;

public class StringUtils {
    /**
     * 最多两位小数，如果最后一位小数是0，则舍去.
     */
    public static String toStringNoZero2(float f) {
        return toStringNoZero(toFloat(f, 2));
    }

    /**
     *如果最后一位小数是0，则舍去.
     */
    public static String toStringNoZero(float f) {
        NumberFormat instance = NumberFormat.getInstance();
        instance.format(f);
        return f + "";
    }

    /**
     * 默认保留两位小数
     */
    public static String toString(float f) {
        return toString(f, 2);
    }

    /**
     * 自定义保留小数
     */
    public static String toString(float f, int n) {
        return String.format("%." + n + "f", f);
    }

    /**
     * 四舍五入
     */
    public static float toFloat(float f, int n) {

        return Float.parseFloat(toString(f, n));

    }

    /**
     * 不要四舍五入,直接舍去
     */
    public static float toFloat2(float f, int n) {
        if (n <= 0) {
            return f;
        }
        int t = 1;
        int tempInt = 0;
        for (int i = 0; i < n; i++) {
            t = t * 10;
        }
        tempInt = (int) (f * t);
        return tempInt / (float) t;
    }


}
