package com.tuc.icesaver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView iceMeltPerDayText;
    TextView iceMeltPerLifeText;

    final double iceMeltPerDayStandart = 62.230919765;//kg
    double iceMeltPerDay1 = iceMeltPerDayStandart;
    double iceMeltPerDayAll = iceMeltPerDayStandart * 7000000;
    int carInfluence = 15;
    int beefInfluence = 0;
    int milkInfluence = 0;

    int totalInfluence = 0;
    //double currentIceValue = 0;
    //double previousCalculationTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findWidgets();
        if (Settings.isFirstRun) {
            if (Settings.age > 18)
                Settings.lastIceMeltAllValue = 18 * 159 + (Settings.age - 18) * 53;
            else
                Settings.lastIceMeltAllValue = Settings.age * 159;

        }
        Settings.isFirstRun = false;
        startIntervalUpdating();

    }

    void startIntervalUpdating() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        update();
                    }
                });
            }
        }, 0, 1000);
    }

    /*void update() {
        //deltaTime=1;
        float iceMeltPerSecond = iceMeltPerDay1 / 24 / 60 / 60;
        Settings.lastIceMelt1Value += iceMeltPerSecond;
        Settings.lastTime = new Date().getTime();
        iceMeltPerLifeText.setText(Settings.lastIceMelt1Value+"kg/life");
    }*/

    void updateInfluence() {
        if (!Settings.car)
            carInfluence = 0;
        if (Settings.beef)
            beefInfluence = (2 - Settings.beefFrequency) * 5;
        else
            beefInfluence = -5;
        if (!Settings.milk)
            milkInfluence = -10;
        totalInfluence = carInfluence + beefInfluence + milkInfluence;
        Debug.d(null, "totalInfluence: " + totalInfluence + ", iceMeltPerDayStandart: " + iceMeltPerDayStandart);
        double newIceMeltPerDay = (((100 + totalInfluence) * iceMeltPerDayStandart) / 100);
        iceMeltPerDay1 = newIceMeltPerDay;
    }

    void update() {

        long timeNow = (new Date()).getTime();
        double missedDays = (timeNow - Settings.lastTime) / 1000 / 60 / 60 / 24;
        double iceMelted = iceMeltPerDayAll * missedDays;
        Settings.lastIceMeltAllValue += iceMelted * 7000000;
        Settings.lastTime = timeNow;
        updateInfluence();
        iceMeltPerDayText.setText(iceMeltPerDay1 + " " + getResources().getString(R.string.main_ice_melt_per_day));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBlankChange();
        return super.onOptionsItemSelected(item);
    }

    void findWidgets() {
        iceMeltPerDayText = findViewById(R.id.main_melted_ice_per_day);
        iceMeltPerLifeText = findViewById(R.id.main_melted_for_life);
    }

    void onBlankChange() {
        Intent intent = new Intent(this, BlankActivity.class);
        startActivity(intent);
        finish();
    }

    public void startAddIce(View v) {
        Intent intent = new Intent(this, AddIce.class);
        startActivity(intent);
    }

    public void startAddPenguin(View v) {
        Intent intent = new Intent(this, AddPenguin.class);
        startActivity(intent);
    }
}
