package com.fxc.adapt;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;
import android.view.WindowManager;

/**
 * @项目名： AppAdapterView
 * @包名： com.fxc.adapt
 * @创建者: feng
 * @时间: 14:55
 * @描述： app适配器
 */
public class AppAdapt {
    private static int     sWidth;
    private static int     sHeight;
    private static Context sContext;

    private AppAdapt() {}

    /**
     * app标注尺寸标准,默认是720*1280
     */
    public static float sNormWidth  = 720f;
    public static float sNormHeight = 1280f;
    public static float sCellWidth;
    public static float sCellHeight;

    /**
     * 初始化
     *
     * @param context 上下文
     */
    public static void initAdapt(Context context) {
        sContext = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        sWidth = wm.getDefaultDisplay().getWidth();
        sHeight = wm.getDefaultDisplay().getHeight();
        sCellWidth = sWidth / sNormWidth;
        sCellHeight = sHeight / sNormHeight;
    }

    public static Context getContext(){
        return sContext;
    }


    /**
     * 初始化
     *
     * @param context    上下文
     * @param normWidth  标注宽度标准
     * @param normHeight 标注高度标准
     */
    public static void initAdapt(Context context, int normWidth, int normHeight) {
        initAdapt(context);
        sNormWidth = normWidth;
        sNormHeight = normHeight;
    }


    /**
     * 获取标准宽度
     */
    public static float getNormWidth() {
        return sNormHeight;
    }

    /**
     * 获取标准高度
     */
    public static float getNormHeight() {
        return sNormHeight;
    }

    /**
     * 获取app宽度
     */
    public static int getWidth() {
        return sWidth;
    }

    /**
     * 获取app高度
     */
    public static int getHeight() {
        return sHeight;
    }

    /**
     * 设置标注标准
     *
     * @param normWidth  宽度标准
     * @param normHeight 高度标准
     */
    public static void setNorm(float normWidth, float normHeight) {
        sNormWidth = normWidth;
        sNormHeight = normHeight;
    }

    /**
     * 转换宽度的值
     *
     * @param size 定义值
     *
     * @return 适配本机器的值
     */
    public static float transWidthSize(float size) {
        int temp = (int) (sCellWidth * size * 100);
        return temp / 100f;
    }


    /**
     * 转换宽度的值
     *
     * @param size 定义值
     *
     * @return 适配本机器的值
     */
    public static float transHeightSize(float size) {
        int temp = (int) (sCellHeight * size * 100);
        return temp / 100f;
    }


    /**
     * 转换float 成int
     */
    public static int realFloat(float size) {
        if (size != -1) {
            return (int) size;
        } else {
            return 0;
        }
    }

    /**
     * 获取属性值
     */
    public static float getAttrSize(TypedArray type, @StyleableRes int res, boolean isW) {
        int size = type.getInteger(res, -1);
        if (size == -1) {
            return -1;
        }
        if (isW) {
            return transWidthSize(size);
        } else {
            return transHeightSize(size);
        }
    }


    /**
     * 获取属性值
     */
    public static float getAttrSize(TypedArray type, @StyleableRes int res, boolean isW, int defaultValue) {
        int size = type.getInteger(res, defaultValue);
        if (size == -1) {
            return -1;
        }
        if (isW) {
            return transWidthSize(size);
        } else {
            return transHeightSize(size);
        }
    }
}
