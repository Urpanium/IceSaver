package com.tuc.icesaver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class BlankActivity extends AppCompatActivity {

    Switch carAvailibilitySwitch;
    Switch smokingSwitch;
    Switch beefSwitch;
    Spinner beefFrequencySpinner;
    EditText ageEditText;
    TextView ageErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity);
        findWidgets();
        ageErrorText.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.blank_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBlankDone();
        return super.onOptionsItemSelected(item);
    }
    void findWidgets() {
        carAvailibilitySwitch = (Switch) findViewById(R.id.swCar);
        beefSwitch = (Switch) findViewById(R.id.swBeef);
        beefFrequencySpinner = (Spinner) findViewById(R.id.spBeef);
        ageEditText = (EditText) findViewById(R.id.etAge);
        ageErrorText = (TextView) findViewById(R.id.tvWrongAge);

    }
    public void onBlankDone() {
        if (!ageEditText.getText().toString().equals("")) {
            int age = Integer.parseInt(ageEditText.getText().toString());
            if (age <= 150&&age > 0) {
                //Settings.smoking = smokingSwitch.isChecked();
                Settings.beef = beefSwitch.isChecked();
                if (Settings.beef)
                    Settings.beefFrequency = beefFrequencySpinner.getSelectedItemPosition();
                Settings.car = carAvailibilitySwitch.isChecked();

                Settings.save();
                //Toast.makeText(this, "new data " + FileUtil.readFile("data"),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                ageErrorText.setText(getResources().getString(R.string.blank_age_error));
            }
        }else {
            ageErrorText.setText(getResources().getString(R.string.blank_age_empty));
        }
    }
}
