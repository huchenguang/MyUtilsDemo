package com.hcg.myutilslibrary.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcg.myutilslibrary.R;
import com.hcg.myutilslibrary.view.photoview.PhotoView;

/**
 * @author vondear
 * @date 2016/7/19
 * 查看图片并支持手势缩放
 */
public class RxDialogScaleView2 extends RxDialog {

    public PhotoView mRxScaleImageView;
    private String filePath;
    private Uri fileUri;
    private String fileAssetName;
    private Bitmap fileBitmap;
    private int resId;
    private int maxScale = 100;
    public TextView tvRemark;
    public View rootView;
    public ImageView ivRotate;

    public RxDialogScaleView2(Context context) {
        super(context);
        initView();
    }

    public RxDialogScaleView2(Activity context) {
        super(context);
        initView();
    }


    public RxDialogScaleView2(Context context, Uri uri) {
        super(context);
        initView();
        setImage(uri);
    }

    public RxDialogScaleView2(Context context, int resId, boolean isResId) {
        super(context);
        initView();
        if (isResId) {
            setImage(resId);
        }
    }

    public RxDialogScaleView2(Context context, Bitmap bitmap) {
        super(context);
        initView();
        setImage(bitmap);
    }

    public RxDialogScaleView2(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public RxDialogScaleView2(Context context, boolean cancelable, OnCancelListener
            cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }


    public RxDialogScaleView2(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    public PhotoView getRxScaleImageView() {
        return mRxScaleImageView;
    }

    public void setImage(Uri uri) {
        this.fileUri = uri;
        mRxScaleImageView.setImageURI(uri);
    }

    public void setImage(int resId) {
        this.resId = resId;
        mRxScaleImageView.setImageResource(resId);
    }

    public void setImage(Bitmap bitmap) {
        this.fileBitmap = bitmap;
        mRxScaleImageView.setImageBitmap(bitmap);
    }

    private int screenType = 0;

    private void initView() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_scaleview2, null);
        mRxScaleImageView = rootView.findViewById(R.id.photo_view);
        tvRemark = rootView.findViewById(R.id.tv_remark);
        ImageView ivClose = rootView.findViewById(R.id.iv_close);
        ivRotate = rootView.findViewById(R.id.iv_rotate);
        ivClose.setOnClickListener(view -> cancel());
        ivRotate.setOnClickListener(v -> {
            if (myOrientationListener != null) {
                return;
            }
            screenType++;
            mRxScaleImageView.setRotationBy(90);
        });
        mRxScaleImageView.setOnClickListener(v -> toggle());
        setFullScreen();
        setContentView(rootView);
    }

    public void setRemarkContent(String remark) {
        if (TextUtils.isEmpty(remark)) {
            tvRemark.setVisibility(View.INVISIBLE);
        } else {
            tvRemark.setVisibility(View.VISIBLE);
            tvRemark.setText(remark);
        }
    }

    private MyOrientationListener myOrientationListener;

    public void setCanScreenAutoChange() {
        ivRotate.setVisibility(View.GONE);
        if (myOrientationListener == null) {
            myOrientationListener = new MyOrientationListener(mContext);
            myOrientationListener.enable();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        myOrientationListener.disable();
        myOrientationListener = null;

    }

    class MyOrientationListener extends OrientationEventListener {

        public MyOrientationListener(Context context) {
            super(context);
        }

        public MyOrientationListener(Context context, int rate) {
            super(context, rate);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            if (((orientation >= 0) && (orientation < 45)) || (orientation > 315)) {//设置竖屏
                screenType = 0;
                
                mRxScaleImageView.setRotationTo(0);
            } else if (orientation > 225 && orientation < 315) { //设置横屏
                screenType = 1;
                mRxScaleImageView.setRotationTo(90);
            } else if (orientation > 45 && orientation < 135) {// 设置反向横屏
                screenType = 2;
                mRxScaleImageView.setRotationTo(270);
            } else if (orientation > 135 && orientation < 225) {//反向竖屏
                screenType = 3;
                mRxScaleImageView.setRotationTo(180);
            }
        }
    }

    private boolean canClick = true;

    private void toggle() {
        if (canClick) {
            canClick = false;
            if (tvRemark.getVisibility() == View.VISIBLE) {
                ivRotate.setAlpha(1f);
                tvRemark.setAlpha(1f);
                ViewPropertyAnimator animator = tvRemark.animate().alpha(0f).setDuration
                        (300);
                ivRotate.animate().alpha(0f).setDuration(300).start();
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        tvRemark.setVisibility(View.INVISIBLE);
                        canClick = true;
                    }
                });
            } else {
                tvRemark.setVisibility(View.VISIBLE);
                tvRemark.setAlpha(0f);
                ivRotate.setAlpha(0f);
                ivRotate.animate().alpha(1f).setDuration(300).start();
                ViewPropertyAnimator animator = tvRemark.animate().alpha(1f).setDuration
                        (300);
                animator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        tvRemark.setVisibility(View.VISIBLE);
                        canClick = true;
                    }
                });
            }
        }

    }


}
