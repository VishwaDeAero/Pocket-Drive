package com.deaero.pocketdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class AddLicense extends AppCompatActivity {

    EditText inputCurrentLicenseDate, inputNextLicenseDate;
    Button btnAddLicenseRecord;
    String vehicleID;
    DatePickerDialog.OnDateSetListener setListener;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "vehicle_info";
    private static final String KEY_ID = "vehicle_id";
    private static final String KEY_NUMBER = "vehicle_number";
    private static final String KEY_TYPE = "vehicle_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_license);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        vehicleID = sharedPreferences.getString(KEY_ID, null);

        inputCurrentLicenseDate = findViewById(R.id.inputCurrentLicenseDate);
        inputNextLicenseDate = findViewById(R.id.inputNextLicenseDate);
        btnAddLicenseRecord = findViewById(R.id.btnAddLicenseRecord);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        inputCurrentLicenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddLicense.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                inputCurrentLicenseDate.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });

        inputNextLicenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddLicense.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                inputNextLicenseDate.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });

        btnAddLicenseRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddLicense.this);
                myDB.updateVehicleLicense(
                        vehicleID,
                        inputCurrentLicenseDate.getText().toString().trim(),
                        inputNextLicenseDate.getText().toString().trim()
                );
            }
        });
    }
}