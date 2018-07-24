package com.hcg.myutilsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hcg.myutilsdemo.CustomKeyboard.CustomKeyboardActivity;
import com.hcg.myutilsdemo.dialog.DialogMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnCustomKeyboardClick(View view) {
        startActivity(new Intent(this, CustomKeyboardActivity.class));
    }

    public void onDialogClick(View view) {
        startActivity(new Intent(this, DialogMainActivity.class));
    }
}
