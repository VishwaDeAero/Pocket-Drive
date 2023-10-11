package com.deaero.pocketdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MotorCycleGuide extends AppCompatActivity {

    TextView txtDemoData1;
    LinearLayout card1MCLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motor_cycle_guide);
    }

    public void expandCollapse(View view) {
    }
}