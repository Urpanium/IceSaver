package com.tuc.icesaver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.io.File;

public class StartActivity extends AppCompatActivity {

    public Context ctx;
    Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
    }


    public void startBlank(View v) {
        Intent intent = new Intent(this, BlankActivity.class);
        startActivity(intent);
        finish();
    }






}
