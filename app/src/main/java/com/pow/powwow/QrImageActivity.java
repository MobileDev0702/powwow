package com.pow.powwow;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrImageActivity extends AppCompatActivity {

    private String qrCodeStr;

    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private ImageView iv_qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_image);

        iv_qrcode = findViewById(R.id.iv_qrcode);
        qrCodeStr = getIntent().getStringExtra("QRText");
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
//        smallerDimension = smallerDimension * 3 / 4;

        qrgEncoder = new QRGEncoder(
                qrCodeStr, null,
                QRGContents.Type.TEXT,
                smallerDimension);
        try {
            bitmap = qrgEncoder.getBitmap();
            iv_qrcode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}