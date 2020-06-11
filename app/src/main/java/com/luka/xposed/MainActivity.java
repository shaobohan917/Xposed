package com.luka.xposed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permission;

import de.robv.android.xposed.XSharedPreferences;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumber = findViewById(R.id.et_number);
        findViewById(R.id.bt_hook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNumber.getText())) {
                    Toast.makeText(MainActivity.this, getToastMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    saveData();
                }
            }
        });
    }

    private void saveData() {
        String number = etNumber.getText().toString();
        SharedPreferences mSharedPreferences = getSharedPreferences("config", Activity.MODE_WORLD_READABLE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(PreferenceUtils.AUTO_ALL, number).commit();

        Toast.makeText(MainActivity.this, "已成功设置余额：" + number + "元", Toast.LENGTH_SHORT).show();
    }

    public String getToastMsg(){
        return "请输入要修改的金额";
    }
}
