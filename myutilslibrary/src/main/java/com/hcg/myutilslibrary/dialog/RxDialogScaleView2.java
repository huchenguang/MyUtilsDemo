package com.hcg.myutilslibrary.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcg.myutilslibrary.R;
import com.hcg.myutilslibrary.view.photoview.PhotoView;
import com.hcg.myutilslibrary.view.scaleimage.ImageSource;
import com.hcg.myutilslibrary.view.scaleimage.RxScaleImageView;

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
    private ImageView ivRotate;

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

    private void initView() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_scaleview2, null);
        mRxScaleImageView = rootView.findViewById(R.id.photo_view);
        tvRemark = rootView.findViewById(R.id.tv_remark);
        ImageView ivClose = rootView.findViewById(R.id.iv_close);
        ivRotate = rootView.findViewById(R.id.iv_rotate);
        ivClose.setOnClickListener(view -> cancel());
        ivRotate.setOnClickListener(v -> {
            mRxScaleImageView.setRotationBy(90);
        });
        mRxScaleImageView.setOnClickListener(v -> toggle());
        setFullScreen();
        setContentView(rootView);
    }

    public void setRemarkContent(String remark) {
        tvRemark.setText(remark);
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
