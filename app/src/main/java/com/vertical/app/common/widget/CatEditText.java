package com.vertical.app.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vertical.app.R;
import com.vertical.app.common.util.Utils;

/**
 * Created by ls on 10/30/17.
 */

public class CatEditText extends RelativeLayout {
    private Context mContext;

    private int defaultTextSize;
    private int defaultMargin;
    private int defaultTextColor = 0xFF333333;//默认字体颜色
    private int defaultDividerLineColor = 0xffdddddd;//默认分割线颜色

    private static final int NONE = 0;
    private static final int TOP = 1;
    private static final int BOTTOM = 2;
    private static final int BOTH = 3;
    private int defaultDividerLineType = BOTTOM;


    private String mLeftTextString;
    private String mRightTextString;
    private String mCenterTextString;
    private String mEditTextHint;


    private Drawable mLeftIconRes;
    private Drawable mRightIconRes;
    private int mLeftIconHeight;
    private int mLeftIconWidth;
    private int mRightIconHeight;
    private int mRightIconWidth;
    private int mLeftIconMarginLeft;
    private int mRightIconMarginRight;
    private int mLeftTextMarginLeft;
    private int mLeftTextMarginRight;
    private int mRightTextMarginLeft;
    private int mRightTextMarginRight;
    private int mCenterTextMarginLeft;
    private int mCenterTextMarginRight;
    private int mBottomDividerLineMarginLeft;
    private int mTopDividerLineMarginLeft;


    private int mLeftTextSize;
    private int mRightTextSize;
    private int mCenterTextSize;

    private int mLeftTextColor;
    private int mRightTextColor;
    private int mCenterTextColor;

    private int mDividerLineColor;
    private int mDividerLineHeight;
    private int mDividerLineType;

    private ImageView mLeftIconIV, mRightIconIV;
    private TextView mLeftTextTV, mRightTextTV;
    private View mTopDividerLineView, mBottomDividerLineView;
    private EditText mCenterEditText;


    public CatEditText(Context context) {
        this(context, null);
    }

    public CatEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        defaultTextSize = Utils.dp2px(mContext, 17);
        defaultMargin = Utils.dp2px(mContext, 10);
        initAttr(attrs);
        initView();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.CatTextStyle);

        mLeftTextString = a.getString(R.styleable.CatTextStyle_leftText);
        mRightTextString = a.getString(R.styleable.CatTextStyle_rightText);
        mCenterTextString = a.getString(R.styleable.CatTextStyle_centerText);
        mEditTextHint = a.getString(R.styleable.CatTextStyle_editTextHint);

        mLeftIconRes = a.getDrawable(R.styleable.CatTextStyle_leftIcon);
        mRightIconRes = a.getDrawable(R.styleable.CatTextStyle_rightIcon);

        mLeftTextSize = a.getDimensionPixelSize(R.styleable.CatTextStyle_leftTextSize, defaultTextSize);
        mRightTextSize = a.getDimensionPixelSize(R.styleable.CatTextStyle_rightTextSize, defaultTextSize);
        mCenterTextSize = a.getDimensionPixelSize(R.styleable.CatTextStyle_centerTextSize, defaultTextSize);

        mLeftTextColor = a.getColor(R.styleable.CatTextStyle_leftTextColor, defaultTextColor);
        mRightTextColor = a.getColor(R.styleable.CatTextStyle_rightTextColor, defaultTextColor);
        mCenterTextColor = a.getColor(R.styleable.CatTextStyle_centerTextColor, defaultTextColor);

        mDividerLineColor = a.getColor(R.styleable.CatTextStyle_dividerLineColor, defaultDividerLineColor);
        mDividerLineHeight = a.getDimensionPixelSize(R.styleable.CatTextStyle_dividerLineHeight, Utils.dp2px(mContext, 0.5f));
        mDividerLineType = a.getInt(R.styleable.CatTextStyle_dividerLineType, defaultDividerLineType);

        mLeftIconHeight = a.getDimensionPixelSize(R.styleable.CatTextStyle_leftIconHeight, 0);
        mLeftIconWidth = a.getDimensionPixelSize(R.styleable.CatTextStyle_leftIconWidth, 0);
        mRightIconHeight = a.getDimensionPixelSize(R.styleable.CatTextStyle_rightIconHeight, 0);
        mRightIconWidth = a.getDimensionPixelSize(R.styleable.CatTextStyle_rightIconWidth, 0);

        mLeftIconMarginLeft = a.getDimensionPixelSize(R.styleable.CatTextStyle_leftIconMarginLeft, defaultMargin);
        mRightIconMarginRight = a.getDimensionPixelSize(R.styleable.CatTextStyle_rightIconMarginRight, defaultMargin);
        mLeftTextMarginLeft = a.getDimensionPixelSize(R.styleable.CatTextStyle_leftTextMarginLeft, defaultMargin);
        mLeftTextMarginRight = a.getDimensionPixelSize(R.styleable.CatTextStyle_leftTextMarginRight, defaultMargin);
        mRightTextMarginLeft = a.getDimensionPixelSize(R.styleable.CatTextStyle_rightTextMarginLeft, defaultMargin);
        mRightTextMarginRight = a.getDimensionPixelSize(R.styleable.CatTextStyle_rightTextMarginRight, defaultMargin);
        mCenterTextMarginLeft = a.getDimensionPixelSize(R.styleable.CatTextStyle_centerTextMarginLeft, 0);
        mCenterTextMarginRight = a.getDimensionPixelSize(R.styleable.CatTextStyle_centerTextMarginRight, 0);
        mBottomDividerLineMarginLeft = a.getDimensionPixelSize(R.styleable.CatTextStyle_bottomDividerLineMarginLeft, 0);
        mTopDividerLineMarginLeft = a.getDimensionPixelSize(R.styleable.CatTextStyle_topDividerLineMarginLeft, 0);
        a.recycle();
    }

    private void initView() {
        initCatEditText();
        initLeftIcon();
        initLeftTextView();
        initRightIcon();
        initRightTextView();
        initCenterEditText();
        initDividerLineView();
    }

    private void initCatEditText() {
        this.setBackgroundColor(mContext.getResources().getColor(R.color.white));
    }

    private void initLeftIcon() {
        if (mLeftIconIV == null) {
            mLeftIconIV = new ImageView(mContext);
        }
        LayoutParams mLeftIconParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLeftIconParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        mLeftIconParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        if (mLeftIconHeight != 0 && mLeftIconWidth != 0) {
            mLeftIconParams.height = mLeftIconHeight;
            mLeftIconParams.width = mLeftIconWidth;
        }
        mLeftIconIV.setId(R.id.leftIconId);
        mLeftIconIV.setLayoutParams(mLeftIconParams);
        if (mLeftIconRes != null) {
            mLeftIconParams.setMargins(mLeftIconMarginLeft, 0, 0, 0);
            mLeftIconIV.setImageDrawable(mLeftIconRes);
        }
        addView(mLeftIconIV);
    }

    private void initRightIcon() {
        if (mRightIconIV == null) {
            mRightIconIV = new ImageView(mContext);
        }
        LayoutParams mRightIconParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mRightIconParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        mRightIconParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        if (mRightIconHeight != 0 && mRightIconWidth != 0) {
            mRightIconParams.height = mRightIconHeight;
            mRightIconParams.width = mRightIconWidth;
        }
        mRightIconIV.setId(R.id.rightIconId);
        mRightIconIV.setLayoutParams(mRightIconParams);
        if (mRightIconRes != null) {
            mRightIconParams.setMargins(0, 0, mRightIconMarginRight, 0);
            mRightIconIV.setImageDrawable(mRightIconRes);
        }
        addView(mRightIconIV);
    }

    private void initLeftTextView() {
        if (mLeftTextTV == null) {
            mLeftTextTV = new TextView(mContext);
        }
        LayoutParams mLeftTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLeftTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.leftIconId);
        mLeftTextParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        mLeftTextParams.setMargins(mLeftTextMarginLeft, 0, mLeftTextMarginRight, 0);
        mLeftTextTV.setId(R.id.leftTextId);
        mLeftTextTV.setLayoutParams(mLeftTextParams);
        mLeftTextTV.setTextColor(mLeftTextColor);
        mLeftTextTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
        mLeftTextTV.setText(mLeftTextString);
        addView(mLeftTextTV);


    }

    private void initRightTextView() {
        if (mRightTextTV == null) {
            mRightTextTV = new TextView(mContext);
        }
        LayoutParams mRightTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mRightTextParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        mRightTextParams.addRule(RelativeLayout.LEFT_OF, R.id.rightIconId);
        mRightTextParams.setMargins(mRightTextMarginLeft, 0, mRightTextMarginRight, 0);
        mRightTextTV.setId(R.id.rightTextId);
        mRightTextTV.setLayoutParams(mRightTextParams);
        mRightTextTV.setTextColor(mRightTextColor);
        mRightTextTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightTextSize);
        mRightTextTV.setText(mRightTextString);
        addView(mRightTextTV);
    }

    private void initCenterEditText() {
        if (mCenterEditText == null) {
            mCenterEditText = new EditText(mContext);
            mCenterEditText.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        LayoutParams mCenterTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mCenterTextParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        mCenterTextParams.addRule(RelativeLayout.LEFT_OF, R.id.rightTextId);
        mCenterTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.leftTextId);
        mCenterTextParams.setMargins(mCenterTextMarginLeft, 0, mCenterTextMarginRight, 0);

        mCenterEditText.setLayoutParams(mCenterTextParams);
        mCenterEditText.setTextColor(mCenterTextColor);
        mCenterEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mCenterTextSize);
        mCenterEditText.setText(mCenterTextString);
        mCenterEditText.setHint(mEditTextHint);
        addView(mCenterEditText);
    }

    private void initDividerLineView() {
        switch (mDividerLineType) {
            case NONE:
                break;
            case TOP:
                initTopDividerLineView();
                break;
            case BOTTOM:
                initBottomDividerLineView();
                break;
            case BOTH:
                initTopDividerLineView();
                initBottomDividerLineView();
                break;
        }
    }

    private void initTopDividerLineView() {
        if (mTopDividerLineView == null) {
            mTopDividerLineView = new View(mContext);
            LayoutParams mTopDividerLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, mDividerLineHeight);
            mTopDividerLineParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, TRUE);
            mTopDividerLineParams.setMargins(mTopDividerLineMarginLeft, 0, 0, 0);
            mTopDividerLineView.setLayoutParams(mTopDividerLineParams);
            mTopDividerLineView.setBackgroundColor(mDividerLineColor);
        }
        addView(mTopDividerLineView);
    }

    private void initBottomDividerLineView() {
        if (mBottomDividerLineView == null) {
            mBottomDividerLineView = new View(mContext);
            LayoutParams mBottomDividerLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, mDividerLineHeight);
            mBottomDividerLineParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE);
            mBottomDividerLineParams.setMargins(mBottomDividerLineMarginLeft, 0, 0, 0);
            mBottomDividerLineView.setLayoutParams(mBottomDividerLineParams);
            mBottomDividerLineView.setBackgroundColor(mDividerLineColor);
        }
        addView(mBottomDividerLineView);
    }

    public void setLeftTextString(CharSequence lefString) {
        if (mLeftTextTV != null) {
            mLeftTextTV.setText(lefString);
        }
    }

    public void setCenterTextString(CharSequence centerString) {
        if (mCenterEditText != null) {
            mCenterEditText.setText(centerString);
        }
    }

    public void setRightTextString(CharSequence rightString) {
        if (mRightTextTV != null) {
            mRightTextTV.setText(rightString);
        }
    }

    public void setRightIconVisibility(int visibility) {
        if (mRightIconIV != null) {
            mRightIconIV.setVisibility(visibility);
        }
    }

    public void setBottomDividerLineVisibility(int visibility) {
        if (mBottomDividerLineView == null) {
            initBottomDividerLineView();
        }
        mBottomDividerLineView.setVisibility(visibility);
    }

    public void setTopDividerLineVisibility(int visibility) {
        if (mTopDividerLineView == null) {
            initTopDividerLineView();
        }
        mTopDividerLineView.setVisibility(visibility);
    }

    public void setLeftIconRes(int res) {
        mLeftIconRes = getResources().getDrawable(res);
        if(mLeftIconIV != null) {
            ((ViewGroup)mLeftIconIV.getParent()).removeView(mLeftIconIV);
            initLeftIcon();
        }
    }

    public String getLeftTextString() {
        return mLeftTextTV != null ? mLeftTextTV.getText().toString().trim() : "";
    }

    public String getCenterTextString() {
        return mCenterEditText != null ? mCenterEditText.getText().toString().trim() : "";
    }

    public String getRightTextString() {
        return mRightTextTV != null ? mRightTextTV.getText().toString().trim() : "";
    }

}
