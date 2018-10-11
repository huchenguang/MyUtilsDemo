package com.hcg.myutilsdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hcg.myutilsdemo.CustomKeyboard.CustomKeyboardActivity;
import com.hcg.myutilsdemo.dialog.DialogMainActivity;
import com.hcg.myutilsdemo.utils.MyPermissionUtils;
import com.hcg.myutilslibrary.dialog.RxDialogSure;
import com.hcg.myutilslibrary.utils.InstallUtils;
import com.hcg.myutilslibrary.widget.TipLoadDialog;

import java.io.File;
import java.util.List;

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

    public void onExpandTextViewClick(View view) {
        new TipLoadDialog(this).setMsgAndType("加载成功", TipLoadDialog.ICON_TYPE_SUCCESS).show();
    }

    public void onOkClick(View view) {
        final RxDialogSure rxDialogSure = new RxDialogSure(this);
        rxDialogSure.setContent("你好");
        rxDialogSure.setSureListener(v -> rxDialogSure.dismiss());
        rxDialogSure.show();
    }

    public void onPermissionRequest(View v) {
        MyPermissionUtils.requestRecordPermission(this, new MyPermissionUtils.OnGrantedListener() {
            @Override
            public void onGranted(List<String> permissionsGranted) {

            }
        });
    }

    public void onInstallApk(View v) {
        InstallUtils.installApk(this, new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/" + "android.apk"), "com.hcg.myutilsdemo.provider" +
                ".MyFileProvider");
    }

}
