package com.tuc.icesaver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddIce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ice_activity);

    }

    public void backToMain(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }





}