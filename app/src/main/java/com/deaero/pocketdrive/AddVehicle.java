package com.deaero.pocketdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddVehicle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText inputVehicleNumber, inputChassisNumber, inputEngineNumber, inputVehicleModel;
    private RadioGroup rgVehicleType;
    private RadioButton rdbVehicleType;
    private Spinner spnFuelType;
    private Button addVehicleRecordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);

        inputVehicleNumber = findViewById(R.id.inputVehicleNumber);
        inputChassisNumber = findViewById(R.id.inputChassisNumber);
        inputEngineNumber = findViewById(R.id.inputEngineNumber);
        inputVehicleModel = findViewById(R.id.inputVehicleModel);
        rgVehicleType = findViewById(R.id.rdbgSelectVehicleType);
        addVehicleRecordBtn = findViewById(R.id.btnAddVehicleRecordBtn);

//      Populate Fuel Spinner
        spnFuelType = findViewById(R.id.spinFuelType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fueltypes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFuelType.setAdapter(adapter);
        spnFuelType.setOnItemSelectedListener(this);

        addVehicleRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddVehicle.this);
                myDB.AddVehicle(inputVehicleNumber.getText().toString().trim(),
                                inputChassisNumber.getText().toString().trim(),
                                inputEngineNumber.getText().toString().trim(),
                                inputVehicleModel.getText().toString().trim(),
                                rdbVehicleType.getText().toString().trim(),
                                spnFuelType.getSelectedItem().toString().trim(),
                                0);
            }
        });
    }

    public void checkButton(View v) {
        int selectedId = rgVehicleType.getCheckedRadioButtonId();
        rdbVehicleType = findViewById(selectedId);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}