package com.fxc.adapt;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewGroup;

import static com.fxc.adapt.AppAdapt.getAttrSize;

/**
 * @项目名： AppAdapterView
 * @包名： com.fxc.adapt
 * @创建者: feng
 * @时间: 16:45
 * @描述： View
 */
public class RelativeLayout extends android.widget.RelativeLayout {
    private float mMarginLeft;
    private float mMarginRight;
    private float mMarginTop;
    private float mMarginBottom;
    private float mHeight = -1;
    private float mWidth = -1;
    private int mMargin = -1;

    public RelativeLayout(Context context) {
        this(context,null);
    }

    public RelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    public RelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        if (mWidth != -1) {
            params.width = (int) mWidth;
        }
        if (mHeight != -1) {
            params.height = (int) mHeight;
        }
        if (mMargin != -1) {
            params.setMargins(mMargin, mMargin, mMargin, mMargin);
        }
        if (mMarginLeft != -1) {
            params.leftMargin = (int) mMarginLeft;
        }
        if (mMarginRight != -1) {
            params.rightMargin = (int) mMarginRight;
        }
        if (mMarginTop != -1) {
            params.topMargin = (int) mMarginTop;
        }
        if (mMarginBottom != -1) {
            params.bottomMargin = (int) mMarginBottom;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 初始化参数
     */
    private void initAttr(AttributeSet attrs) {
        TypedArray type = getContext().obtainStyledAttributes(attrs, R.styleable.AdaptStyle);
        //高度
        mHeight =  getAttrSize(type, R.styleable.AdaptStyle_layout_height, false);
        //宽度
        mWidth =  getAttrSize(type, R.styleable.AdaptStyle_layout_width, true);
        //padding
        float paddingLeft   = AppAdapt.getAttrSize(type, R.styleable.AdaptStyle_paddingLeft, true, 0);
        float paddingRight  = AppAdapt.getAttrSize(type, R.styleable.AdaptStyle_paddingRight, true, 0);
        float paddingTop    = AppAdapt.getAttrSize(type, R.styleable.AdaptStyle_paddingTop, false, 0);
        float paddingBottom = AppAdapt.getAttrSize(type, R.styleable.AdaptStyle_paddingBottom, false, 0);
        if (paddingLeft > 0 || paddingRight > 0 || paddingTop > 0 ||
            paddingBottom > 0) {
            setPadding(AppAdapt.realFloat(paddingLeft), AppAdapt.realFloat(paddingTop), AppAdapt.realFloat(paddingRight), AppAdapt.realFloat(paddingBottom));
        }
        //padding
        float padding = getAttrSize(type, R.styleable.AdaptStyle_padding, true, 0);
        if (padding > 0) {
            int pad = (int) padding;
            setPadding(pad, pad, pad, pad);
        }

        //margin
        mMarginLeft = AppAdapt.getAttrSize(type, R.styleable.AdaptStyle_layout_marginLeft, true);
        mMarginRight = AppAdapt.getAttrSize(type, R.styleable.AdaptStyle_layout_marginRight, true);
        mMarginTop = AppAdapt.getAttrSize(type, R.styleable.AdaptStyle_layout_marginTop, false);
        mMarginBottom = AppAdapt.getAttrSize(type, R.styleable.AdaptStyle_layout_marginBottom, false);

        float size = AppAdapt.getAttrSize(type, R.styleable.AdaptStyle_layout_margin, true);

        if (size != -1) {
            int sizes = (int) size;
            mMargin = sizes;
        }
        type.recycle();
    }
}
