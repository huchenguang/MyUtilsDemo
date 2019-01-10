package com.hcg.myutilsdemo.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hcg.myutilsdemo.R;
import com.hcg.myutilslibrary.dialog.RxDialogChooseImage;
import com.hcg.myutilslibrary.dialog.RxDialogScaleView;
import com.hcg.myutilslibrary.dialog.RxDialogScaleView2;
import com.hcg.myutilslibrary.view.scaleimage.RxScaleImageView;

public class DialogMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_main);
    }

    public void onChooseImgClick(View view) {
        new RxDialogChooseImage(this,
                RxDialogChooseImage.LayoutType.TITLE)
                .setOnCameraViewClickListener(new RxDialogChooseImage.OnMyClickListener() {
                    @Override
                    public void onClick(RxDialogChooseImage dialog) {
                        Toast.makeText(DialogMainActivity.this, "你点击了照相机", Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .setOnFileViewClickListener(new RxDialogChooseImage.OnMyClickListener() {
                    @Override
                    public void onClick(RxDialogChooseImage dialog) {
                        Toast.makeText(DialogMainActivity.this, "你点击了相册", Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .show();
    }

    public void onScaleImageClick(View view) {
        new RxDialogScaleView2(this, R.mipmap.ic_launcher, true)
                .show();
    }
}
