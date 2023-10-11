package com.deaero.pocketdrive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewVehicles;
    FloatingActionButton addVehicleBtn;

    MyDatabaseHelper myDB;
    ArrayList<String> vehicle_id, vehicle_number, vehicle_type;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new MyDatabaseHelper(MainActivity.this);
        vehicle_id = new ArrayList<>();
        vehicle_number = new ArrayList<>();
        vehicle_type = new ArrayList<>();

        storeBtnDataInArrays();
        recyclerViewVehicles = findViewById(R.id.recyclerViewVehicles);
        customAdapter = new CustomAdapter(MainActivity.this, vehicle_id, vehicle_number, vehicle_type);
        recyclerViewVehicles.setAdapter(customAdapter);
        recyclerViewVehicles.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        addVehicleBtn = findViewById(R.id.btnAddVehicle);
        addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddVehicle.class);
                startActivity(intent);
            }
        });
    }

    void storeBtnDataInArrays() {
        Cursor cursor = myDB.readAllVehicleData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Vehicles in Database", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                vehicle_id.add(cursor.getString(0));
                vehicle_number.add(cursor.getString(1));
                vehicle_type.add(cursor.getString(5));
            }
        }
    }
}