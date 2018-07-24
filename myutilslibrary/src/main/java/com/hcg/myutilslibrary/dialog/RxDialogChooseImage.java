package com.hcg.myutilslibrary.dialog;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hcg.myutilslibrary.R;

/**
 * @author vondear https://github.com/vondear/RxTool
 * @date 2017/3/20
 * 封装了从相册/相机 获取 图片 的Dialog.
 */
public class RxDialogChooseImage extends RxDialog {

    private LayoutType mLayoutType = LayoutType.TITLE;
    public TextView mTvCamera;
    public TextView mTvFile;
    public TextView mTvCancel;

    public RxDialogChooseImage(Activity context) {
        super(context);
        initView(context);
    }

    public RxDialogChooseImage(Fragment fragment) {
        super(fragment.getContext());
        initView(fragment);
    }

    public RxDialogChooseImage(Fragment fragment, LayoutType layoutType) {
        super(fragment.getContext());
        mLayoutType = layoutType;
        initView(fragment);
    }


    public RxDialogChooseImage(Activity context, LayoutType layoutType) {
        super(context);
        mLayoutType = layoutType;
        initView(context);
    }

    private OnMyClickListener l1;
    private OnMyClickListener l2;

    public interface OnMyClickListener {
        void onClick(RxDialogChooseImage dialog);
    }

    public RxDialogChooseImage setOnCameraViewClickListener(OnMyClickListener l) {
        l1 = l;
        return this;
    }

    public RxDialogChooseImage setOnFileViewClickListener(OnMyClickListener l) {
        l2 = l;
        return this;
    }


    public TextView getFromCameraView() {
        return mTvCamera;
    }

    public TextView getFromFileView() {
        return mTvFile;
    }

    public TextView getCancelView() {
        return mTvCancel;
    }

    public LayoutType getLayoutType() {
        return mLayoutType;
    }

    private void initView(final Activity activity) {
        View dialogView = null;
        switch (mLayoutType) {
            case TITLE:
                dialogView = LayoutInflater.from(getContext()).inflate(R.layout
                        .dialog_picker_pictrue, null);
                break;
            case NO_TITLE:
                dialogView = LayoutInflater.from(getContext()).inflate(R.layout
                        .dialog_camero_show, null);
                break;
            default:
                break;
        }


        mTvCamera = dialogView.findViewById(R.id.tv_camera);
        mTvFile = dialogView.findViewById(R.id.tv_file);
        mTvCancel = dialogView.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancel();
            }
        });

        mTvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (l1 != null) {
                    l1.onClick(RxDialogChooseImage.this);
                    dismiss();
                }
            }
        });
        mTvFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (l2 != null) {
                    l2.onClick(RxDialogChooseImage.this);
                    dismiss();
                }
            }
        });

        setContentView(dialogView);
        mLayoutParams.gravity = Gravity.BOTTOM;
    }

    private void initView(final Fragment fragment) {
        View dialogView = null;
        switch (mLayoutType) {
            case TITLE:
                dialogView = LayoutInflater.from(getContext()).inflate(R.layout
                        .dialog_picker_pictrue, null);
                break;
            case NO_TITLE:
                dialogView = LayoutInflater.from(getContext()).inflate(R.layout
                        .dialog_camero_show, null);
                break;
            default:
                break;
        }

        mTvCamera = dialogView.findViewById(R.id.tv_camera);
        mTvFile = dialogView.findViewById(R.id.tv_file);
        mTvCancel = dialogView.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancel();
            }
        });

        mTvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (l1 != null) {
                    l1.onClick(RxDialogChooseImage.this);
                    dismiss();
                }
            }
        });
        mTvFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (l2 != null) {
                    l2.onClick(RxDialogChooseImage.this);
                    dismiss();
                }
            }
        });

        setContentView(dialogView);
        mLayoutParams.gravity = Gravity.BOTTOM;
    }

    public enum LayoutType {
        TITLE, NO_TITLE
    }
}
