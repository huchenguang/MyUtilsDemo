package com.hcg.myutilsdemo.CustomKeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hcg.myutilsdemo.R;
import com.hcg.myutilslibrary.utils.KeyboardUtil;

import javax.crypto.Mac;

public class CustomKeyboardActivity extends AppCompatActivity {

    private EditText etAmount;
    private View llInput;
    private KeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_keyboard);
        llInput = findViewById(R.id.ll_input);
        etAmount = findViewById(R.id.et_amount);
        keyboardUtil = new KeyboardUtil(this);
        keyboardUtil.mKeyboardView.setOkKeyBackgroundDrawable(R.drawable.my_bg_keyboardview_yes);
        findViewById(R.id.tv_amount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardUtil.attachTo(etAmount);
                etAmount.setFocusable(true);
                etAmount.setFocusableInTouchMode(true);
                etAmount.requestFocus();
                llInput.setVisibility(View.VISIBLE);
            }
        });
        keyboardUtil.setOnOkClick(new KeyboardUtil.OnOkClick() {
            @Override
            public void onOkClick() {

            }
        });
        keyboardUtil.setOnCancelClick(new KeyboardUtil.onCancelClick() {
            @Override
            public void onCancelClick() {
                llInput.setVisibility(View.GONE);
            }
        });
    }
}
