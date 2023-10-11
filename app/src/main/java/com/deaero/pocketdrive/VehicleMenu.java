package com.deaero.pocketdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VehicleMenu extends AppCompatActivity {

    TextView txtVehicleName;
    Button btnShowMyRecords, btnShowGuideBook;
    String vehicleID, vehicleNumber, vehicleType;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "vehicle_info";
    private static final String KEY_ID = "vehicle_id";
    private static final String KEY_NUMBER = "vehicle_number";
    private static final String KEY_TYPE = "vehicle_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_menu);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        txtVehicleName = findViewById(R.id.txtVehicleName);
        btnShowMyRecords = findViewById(R.id.btnShowMyRecords);
        btnShowGuideBook = findViewById(R.id.btnShowGuideBook);

        getAndSetIntentData();

        btnShowMyRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicleMenu.this, MyRecords.class);
                intent.putExtra("vehicleID",vehicleID);
                startActivity(intent);
            }
        });

        btnShowGuideBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vehicleType.equals("Motor Cycle")){
                    Intent intent = new Intent(VehicleMenu.this, MotorCycleGuide.class);
                    startActivity(intent);
                }else if(vehicleType.equals("Car")){
                    Intent intent = new Intent(VehicleMenu.this, CarGuide.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(VehicleMenu.this, "Vehicle Type "+vehicleType+" Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("vehicleID") && getIntent().hasExtra("vehicleNumber") && getIntent().hasExtra("vehicleType")) {
            vehicleID = getIntent().getStringExtra("vehicleID");
            vehicleNumber = getIntent().getStringExtra("vehicleNumber");
            vehicleType = getIntent().getStringExtra("vehicleType");
            txtVehicleName.setText(vehicleNumber);
        } else {
            vehicleID = sharedPreferences.getString(KEY_ID, null);
            vehicleNumber = sharedPreferences.getString(KEY_NUMBER, null);
            vehicleType = sharedPreferences.getString(KEY_TYPE, null);
            if(vehicleNumber != null){
                txtVehicleName.setText(vehicleNumber);
            } else {
                Toast.makeText(this, "No Data Passed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}