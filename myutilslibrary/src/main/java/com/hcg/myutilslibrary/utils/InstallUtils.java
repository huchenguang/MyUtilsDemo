package com.hcg.myutilslibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

public class InstallUtils {
    public static void installApk(Activity context, File file, String providerClassName) {
       /* startAnotherActivity(MainActivity.class);
        finish();*/
        File apkFile = file;
        //安装apk文件
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context
                    , providerClassName
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android" +
                    ".package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android" +
                    ".package-archive");
        }
        context.startActivity(intent);
    }
}
