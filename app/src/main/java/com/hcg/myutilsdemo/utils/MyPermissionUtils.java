package com.hcg.myutilsdemo.utils;

import android.Manifest;
import android.app.Activity;

import com.hcg.myutilslibrary.utils.PermissionConstants;
import com.hcg.myutilslibrary.utils.PermissionUtils;

import java.util.List;

public class MyPermissionUtils {
    public static void requestAllAppPermission(Activity context, OnGrantedListener listener) {
        PermissionUtils.init(context);
        PermissionUtils.permissionIt(Manifest.permission.RECORD_AUDIO, Manifest.permission
                .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .rationale(shouldRequest -> {
                    shouldRequest.again(true);
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        if (listener != null) {
                            listener.onGranted(permissionsGranted);
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String>
                            permissionsDenied) {
                        if (listener != null) {
                            listener.onDenied(permissionsDeniedForever, permissionsDenied);
                        }
                    }
                })
                .request();
    }

    public static void requestRecordPermission(Activity context, OnGrantedListener listener) {
        PermissionUtils.init(context);
        PermissionUtils.permissionIt(Manifest.permission.RECORD_AUDIO)
                .rationale(shouldRequest -> {
                    PermissionUtils.showDialog(shouldRequest, "请求录音，如果拒绝可能导致无法正常使用app",
                            context);
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        if (listener != null) {
                            listener.onGranted(permissionsGranted);
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String>
                            permissionsDenied) {
                        if (listener != null) {
                            listener.onDenied(permissionsDeniedForever, permissionsDenied);
                        }
                        if (!permissionsDenied.isEmpty()) {
                            PermissionUtils.showGoToSettingDialog("请求录音权限失败，立即前往设置界面授权?", context);
                        }
                    }
                })
                .request();
    }

    public static void requestSdPermission(Activity context, OnGrantedListener listener) {
        PermissionUtils.init(context);
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .rationale(shouldRequest -> {
                    PermissionUtils.showDialog(shouldRequest, "请求读写外部内存卡权限，如果拒绝可能导致无法正常使用app",
                            context);
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        if (listener != null) {
                            listener.onGranted(permissionsGranted);
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String>
                            permissionsDenied) {
                        if (listener != null) {
                            listener.onDenied(permissionsDeniedForever, permissionsDenied);
                        }
                        if (!permissionsDenied.isEmpty()) {
                            PermissionUtils.showGoToSettingDialog("请求读写外部内存卡失败，立即前往设置界面授权?",
                                    context);
                        }
                    }
                })
                .request();
    }


    public static abstract class OnGrantedListener {
        public abstract void onGranted(List<String> permissionsGranted);

        public void onDenied(List<String> permissionsDeniedForever, List<String>
                permissionsDenied) {
        }
    }
}
