package com.pow.powwow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String QRCODE = "PREFERENCE_QRCODE";
    public static final String LANG = "PREFERENCE_LANG";
    private TextView tv_input;
    private EditText et_text;
    private Button btn_generate;
    private Switch sw_lan;

    SharedPreferences qrTextPref;
    SharedPreferences langPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        initValue();
        setTextListener();
        setSwitchListener();
        onClickGenerateBtn();
    }

    private void initComponent() {
        tv_input = findViewById(R.id.tv_input);
        sw_lan = findViewById(R.id.sw_lan);
        et_text = findViewById(R.id.et_text);
        btn_generate = findViewById(R.id.btn_generate);
    }

    private void initValue() {
        qrTextPref = getSharedPreferences(QRCODE, Context.MODE_PRIVATE);
        langPref = getSharedPreferences(LANG, Context.MODE_PRIVATE);
        String qrValue = qrTextPref.getString("qrText", "");
        Boolean lang = langPref.getBoolean("Language", false);
        et_text.setText(qrValue);
        if (lang) {
            sw_lan.setChecked(true);
            tv_input.setText(R.string.inputtext_es);
            btn_generate.setText(R.string.generate_es);
        } else {
            sw_lan.setChecked(false);
            tv_input.setText(R.string.inputtext);
            btn_generate.setText(R.string.generate);
        }
        if (et_text.length() == 0) {
            btn_generate.setEnabled(false);
        } else {
            btn_generate.setEnabled(true);
        }
    }

    private void setTextListener() {
        et_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    btn_generate.setEnabled(false);
                } else {
                    btn_generate.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setSwitchListener() {
        sw_lan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setAllText(b);
            }
        });
    }

    private void setAllText(Boolean checked) {
        if (checked) {
            tv_input.setText(R.string.inputtext_es);
            btn_generate.setText(R.string.generate_es);
            langPref.edit().putBoolean("Language", true).commit();
        } else {
            tv_input.setText(R.string.inputtext);
            btn_generate.setText(R.string.generate);
            langPref.edit().putBoolean("Language", false).commit();
        }
    }

    private void onClickGenerateBtn() {
        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences qrText = getSharedPreferences(QRCODE, Context.MODE_PRIVATE);
                qrText.edit().putString("qrText", et_text.getText().toString()).commit();

                Intent intent = new Intent(MainActivity.this, QrImageActivity.class);
                intent.putExtra("QRText", et_text.getText().toString());
                startActivity(intent);
            }
        });
    }
}