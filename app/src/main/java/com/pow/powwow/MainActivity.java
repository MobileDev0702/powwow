package com.pow.powwow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_text;
    private Button btn_generate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        onClickGenerateBtn();
    }

    private void initComponent() {
        et_text = findViewById(R.id.et_text);
        btn_generate = findViewById(R.id.btn_generate);
    }

    private void onClickGenerateBtn() {
        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QrImageActivity.class);
                intent.putExtra("QRText", et_text.getText().toString());
                startActivity(intent);
            }
        });
    }
}