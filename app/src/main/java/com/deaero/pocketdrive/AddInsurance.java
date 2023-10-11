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

public class AddInsurance extends AppCompatActivity {

    EditText inputCurrentInsuranceDate, inputNextInsuranceDate;
    Button btnAddInsuranceRecord;
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
        setContentView(R.layout.add_insurance);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        vehicleID = sharedPreferences.getString(KEY_ID, null);

        inputCurrentInsuranceDate = findViewById(R.id.inputCurrentInsuranceDate);
        inputNextInsuranceDate = findViewById(R.id.inputNextInsuranceDate);
        btnAddInsuranceRecord = findViewById(R.id.btnAddInsuranceRecord);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        inputCurrentInsuranceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddInsurance.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                inputCurrentInsuranceDate.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });

        inputNextInsuranceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddInsurance.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                inputNextInsuranceDate.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });

        btnAddInsuranceRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddInsurance.this);
                myDB.updateVehicleInsurance(
                        vehicleID,
                        inputCurrentInsuranceDate.getText().toString().trim(),
                        inputNextInsuranceDate.getText().toString().trim()
                );
            }
        });
    }
}