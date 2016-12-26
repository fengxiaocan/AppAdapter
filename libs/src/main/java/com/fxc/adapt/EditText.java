package com.fxc.adapt;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;

import static com.fxc.adapt.AppAdapt.getAttrSize;

/**
 * @项目名： AppAdapterView
 * @包名： com.fxc.adapt
 * @创建者: feng
 * @时间: 15:48
 * @描述： textview
 */
public class EditText extends android.widget.EditText {
    private float mMarginLeft;
    private float mMarginRight;
    private float mMarginTop;
    private float mMarginBottom;
    private float mDrawablePadding;
    private int mMargin = -1;

    public EditText(Context context) {
        this(context, null);
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    public EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();
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
        float height = getAttrSize(type, R.styleable.AdaptStyle_layout_height, false);
        if (height != -1) {
            setHeight((int) height);
        }
        //宽度
        float width = getAttrSize(type, R.styleable.AdaptStyle_layout_width, true);
        if (width != -1) {
            setWidth((int) width);
        }
        //最大高度
        float maxHeight = getAttrSize(type, R.styleable.AdaptStyle_maxHeight, false);
        if (maxHeight != -1) {
            setMaxHeight((int) maxHeight);
        }
        //最大宽度
        float maxWidth = getAttrSize(type, R.styleable.AdaptStyle_maxWidth, true);
        if (maxWidth != -1) {
            setMaxWidth((int) maxWidth);
        }

        //最小高度
        float minHeight = getAttrSize(type, R.styleable.AdaptStyle_minHeight, false);
        if (maxHeight != -1) {
            setMinHeight((int) minHeight);
        }
        //最小宽度
        float minWidth = getAttrSize(type, R.styleable.AdaptStyle_minWidth, true);
        if (maxWidth != -1) {
            setMinWidth((int) minWidth);
        }
        //textSize
        float textSize = getAttrSize(type, R.styleable.AdaptStyle_textSize, true);
        if (textSize != -1) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, AppAdapt.transWidthSize(textSize));
        }
        float textSizeH = getAttrSize(type, R.styleable.AdaptStyle_textSizeH, false);
        if (textSizeH != -1) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, AppAdapt.transHeightSize(textSizeH));
        }
        //默认字体大小是15
        if (textSize == -1 && textSizeH == -1) {
            setTextSize(AppAdapt.transWidthSize(15));
        }
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
        //DrawablePadding
        mDrawablePadding = getAttrSize(type, R.styleable.AdaptStyle_drawablePadding, true);
        if (mDrawablePadding != -1) {
            int padd = (int) mDrawablePadding;
            setCompoundDrawablePadding(padd);
        }
        type.recycle();
    }
}
