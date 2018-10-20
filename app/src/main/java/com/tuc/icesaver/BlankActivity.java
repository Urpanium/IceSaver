package com.tuc.icesaver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class BlankActivity extends AppCompatActivity {

    Switch carAvailibilitySwitch;
    //Switch smokingSwitch;
    Switch beefSwitch;
    Switch milkSwitch;
    Spinner beefFrequencySpinner;
    EditText ageEditText;
    TextView ageErrorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity);
        findWidgets();
        ageErrorText.setText("");
        beefSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                beefFrequencySpinner.setEnabled(isChecked);
                beefFrequencySpinner.setClickable(isChecked);
            }
        });
        if (!Settings.isFirstRun) {
            carAvailibilitySwitch.setChecked(Settings.car);
            beefSwitch.setChecked(Settings.beef);
            beefFrequencySpinner.setSelection(Settings.beefFrequency);
            milkSwitch.setChecked(Settings.milk);
            ageEditText.setText(Settings.age + "", TextView.BufferType.EDITABLE);
        }
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
        carAvailibilitySwitch = findViewById(R.id.swCar);
        beefSwitch = findViewById(R.id.swBeef);
        beefFrequencySpinner = findViewById(R.id.spBeef);
        milkSwitch = findViewById(R.id.swMilk);
        ageEditText = findViewById(R.id.etAge);
        ageErrorText = findViewById(R.id.tvWrongAge);
    }

    public void onBlankDone() {
        if (!ageEditText.getText().toString().equals("")) {
            int age = Integer.parseInt(ageEditText.getText().toString());
            if (age <= 120 && age > 0) {
                Settings.beef = beefSwitch.isChecked();
                if (Settings.beef)
                    Settings.beefFrequency = beefFrequencySpinner.getSelectedItemPosition();
                Settings.car = carAvailibilitySwitch.isChecked();
                Settings.age = age;
                Settings.milk = milkSwitch.isChecked();
                Settings.save();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                ageErrorText.setText(getResources().getString(R.string.blank_age_error));
            }
        } else {
            ageErrorText.setText(getResources().getString(R.string.blank_age_empty));
        }
    }
}
