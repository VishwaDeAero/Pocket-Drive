package com.deaero.pocketdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyRecords extends AppCompatActivity {

    MyDatabaseHelper myDB;
    String vehicleID, vehicleNumber, vehicleChassisNumber, vehicleEngineNumber, fuelType, vehicleModel, vehicleType;
    String licenseDate, nextLicenseDate, insuranceDate, nextInsuranceDate, serviceDate, nextServiceDate;
    TextView txtVehicleNumber, txtVehicleModel, txtChassisNumber, txtEngineNumber, txtFuelType, txtVehicleType;
    TextView txtLicenseRenew, txtLicenseExpire, txtInsuranceRenew, txtInsuranceExpire, txtLastService, txtNextService;
    Button btnAddLicense, btnAddInsurance, btnAddService, btnShowQR;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "vehicle_info";
    private static final String KEY_ID = "vehicle_id";
    private static final String KEY_NUMBER = "vehicle_number";
    private static final String KEY_TYPE = "vehicle_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_records);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        myDB = new MyDatabaseHelper(MyRecords.this);
        vehicleID = new String();
        vehicleNumber = new String();
        vehicleChassisNumber = new String();
        vehicleEngineNumber = new String();
        vehicleModel = new String();
        vehicleType = new String();
        fuelType = new String();
        licenseDate = new String();
        nextLicenseDate = new String();
        insuranceDate = new String();
        nextInsuranceDate = new String();
        serviceDate = new String();
        nextServiceDate = new String();

        txtVehicleNumber = findViewById(R.id.txtVehicleNumber);
        txtVehicleModel = findViewById(R.id.txtVehicleModel);
        txtChassisNumber = findViewById(R.id.txtChassisNumber);
        txtEngineNumber = findViewById(R.id.txtEngineNumber);
        txtFuelType = findViewById(R.id.txtFuelType);
        txtVehicleType = findViewById(R.id.txtVehicleType);
        txtLicenseRenew = findViewById(R.id.txtLicenseRenew);
        txtLicenseExpire = findViewById(R.id.txtLicenseExpire);
        txtInsuranceRenew = findViewById(R.id.txtInsuranceRenew);
        txtInsuranceExpire = findViewById(R.id.txtInsuranceExpire);
        txtLastService = findViewById(R.id.txtLastService);
        txtNextService = findViewById(R.id.txtNextService);

        btnAddLicense = findViewById(R.id.btnAddLicense);
        btnAddInsurance = findViewById(R.id.btnAddInsurance);
        btnAddService = findViewById(R.id.btnAddService);
//        btnShowQR = findViewById(R.id.btnShowQR);

        getAndSetIntentData();
        storeDataInStrings();

        txtVehicleNumber.setText(vehicleNumber);
        txtChassisNumber.setText(vehicleChassisNumber);
        txtEngineNumber.setText(vehicleEngineNumber);
        txtFuelType.setText(fuelType);
        txtVehicleModel.setText(vehicleModel);
        txtVehicleType.setText(vehicleType);
        txtLicenseRenew.setText(licenseDate);
        txtLicenseExpire.setText(nextLicenseDate);
        txtInsuranceRenew.setText(insuranceDate);
        txtInsuranceExpire.setText(nextInsuranceDate);
        txtLastService.setText(serviceDate);
        txtNextService.setText(nextServiceDate);

        btnAddLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyRecords.this,AddLicense.class);
                startActivity(intent);
            }
        });
        btnAddInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyRecords.this,AddInsurance.class);
                startActivity(intent);
            }
        });
        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyRecords.this,AddService.class);
                startActivity(intent);
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("vehicleID")) {
            vehicleID = getIntent().getStringExtra("vehicleID");
            vehicleNumber = getIntent().getStringExtra("vehicleNumber");
            vehicleType = getIntent().getStringExtra("vehicleType");
        } else {
            vehicleID = sharedPreferences.getString(KEY_ID, null);
            vehicleNumber = sharedPreferences.getString(KEY_NUMBER, null);
            vehicleType = sharedPreferences.getString(KEY_TYPE, null);
            if(vehicleID == null || vehicleNumber == null || vehicleType == null){
                Toast.makeText(this, "No Data Passed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void storeDataInStrings() {
        if(vehicleID != null) {
            Cursor cursor = myDB.readVehicleData(Integer.parseInt(vehicleID.toString().trim()));
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No Vehicles in Database", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    vehicleID = cursor.getString(0);
                    vehicleNumber = cursor.getString(1);
                    vehicleChassisNumber = cursor.getString(2);
                    vehicleEngineNumber = cursor.getString(3);
                    vehicleModel = cursor.getString(4);
                    vehicleType = cursor.getString(5);
                    fuelType = cursor.getString(6);
                    licenseDate = cursor.getString(7);
                    nextLicenseDate = cursor.getString(8);
                    insuranceDate = cursor.getString(9);
                    nextInsuranceDate = cursor.getString(10);
                    serviceDate = cursor.getString(11);
                    nextServiceDate = cursor.getString(12);
                }
            }
        } else {
            Toast.makeText(this, "No VehicleId Found", Toast.LENGTH_SHORT).show();
        }
    }
}