package com.huanchengfly.tieba.widgets.theme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.huanchengfly.theme.interfaces.Tintable;
import com.huanchengfly.theme.utils.ColorStateListUtils;
import com.huanchengfly.theme.utils.ThemeUtils;
import com.huanchengfly.tieba.post.R;

@SuppressLint("CustomViewStyleable")
public class TintTextView extends AppCompatTextView implements Tintable {
    private int mBackgroundTintResId;
    private int mTintResId;
    private int mTintListResId;

    public TintTextView(Context context) {
        this(context, null);
    }

    public TintTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TintTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }
        if (attrs == null) {
            mBackgroundTintResId = 0;
            mTintResId = 0;
            mTintListResId = 0;
            applyTintColor();
            return;
        }
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TintView, defStyleAttr, 0);
        mBackgroundTintResId = array.getResourceId(R.styleable.TintView_backgroundTint, 0);
        mTintResId = array.getResourceId(R.styleable.TintView_tint, 0);
        mTintListResId = array.getResourceId(R.styleable.TintView_tintList, 0);
        array.recycle();
        applyTintColor();
    }

    public void setBackgroundTintResId(int mBackgroundTintResId) {
        this.mBackgroundTintResId = mBackgroundTintResId;
        applyTintColor();
    }

    public void setTintResId(int mTintResId) {
        this.mTintResId = mTintResId;
        applyTintColor();
    }

    private void applyTintColor() {
        if (mBackgroundTintResId != 0) {
            if (getBackground() == null) {
                setBackgroundColor(ThemeUtils.getColorById(getContext(), mBackgroundTintResId));
            } else {
                setBackgroundTintList(ColorStateListUtils.createColorStateList(getContext(), mBackgroundTintResId));
            }
        }
        if (mTintResId != 0 && mTintListResId == 0) {
            setTextColor(ColorStateList.valueOf(ThemeUtils.getColorById(getContext(), mTintResId)));
        } else if (mTintListResId != 0) {
            setTextColor(ColorStateListUtils.createColorStateList(getContext(), mTintListResId));
        }
    }

    @Override
    public void tint() {
        applyTintColor();
    }
}
