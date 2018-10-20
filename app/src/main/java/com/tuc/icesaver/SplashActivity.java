package com.tuc.icesaver;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = getApplicationContext();
        FileUtil.path = new File(ctx.getFilesDir().toString());
        Settings.ctx = ctx;
        Settings.isFirstRun = FileUtil.isFirstRun();


        if (!Settings.isFirstRun) {

            Intent intent = new Intent(this, MainActivity.class);
            Settings.load();
            startActivity(intent);
            finish();
        } else {

            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }

    }
}