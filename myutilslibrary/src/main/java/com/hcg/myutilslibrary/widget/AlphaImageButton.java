package com.hcg.myutilslibrary.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

public class AlphaImageButton extends AppCompatImageButton {
    public float pressAlpha = 0.5f;
    public float normAlpha = 1;
    public float disableAlpha = 0.5f;

    public AlphaImageButton(Context context) {
        this(context, null);
    }

    public AlphaImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphaImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundResource(android.R.color.transparent);
        setClickable(true);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed && isEnabled()) {
            setAlpha(pressAlpha);
        } else {
            setAlpha(normAlpha);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            setAlpha(disableAlpha);
        } else {
            setAlpha(normAlpha);
        }
    }
}
