package com.deaero.pocketdrive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "VehicleDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String VEHICLE_TABLE = "vehicles";
    private static final String VEHICLE_ID = "_id";
    private static final String VEHICLE_NUMBER = "vehicle_number";
    private static final String VEHICLE_CH_NUMBER = "chassis_number";
    private static final String VEHICLE_ENG_NUMBER = "engine_number";
    private static final String VEHICLE_MODEL = "vehicle_model";
    private static final String VEHICLE_TYPE = "vehicle_type";
    private static final String VEHICLE_FUEL = "fuel_type";
    private static final String VEHICLE_NEW_LC_DATE = "cur_license_date";
    private static final String VEHICLE_NEXT_LC_DATE = "next_license_date";
    private static final String VEHICLE_NEW_INS_DATE = "cur_insurance_date";
    private static final String VEHICLE_NEXT_INS_DATE = "next_insurance_date";
    private static final String VEHICLE_NEW_SRV_DATE = "cur_service_date";
    private static final String VEHICLE_NEXT_SRV_DATE = "next_service_date";
    private static final String VEHICLE_HAS_QR = "has_qr";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE "+VEHICLE_TABLE+" ( " +
                        VEHICLE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        VEHICLE_NUMBER +" TEXT," +
                        VEHICLE_CH_NUMBER +" TEXT," +
                        VEHICLE_ENG_NUMBER +" TEXT," +
                        VEHICLE_MODEL +" TEXT," +
                        VEHICLE_TYPE +" TEXT," +
                        VEHICLE_FUEL +" TEXT," +
                        VEHICLE_NEW_LC_DATE +" DATE," +
                        VEHICLE_NEXT_LC_DATE +" DATE," +
                        VEHICLE_NEW_INS_DATE +" DATE," +
                        VEHICLE_NEXT_INS_DATE +" DATE," +
                        VEHICLE_NEW_SRV_DATE +" DATE," +
                        VEHICLE_NEXT_SRV_DATE +" DATE," +
                        VEHICLE_HAS_QR +" INTEGER DEFAULT 0 );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+VEHICLE_TABLE);
        onCreate(db);
    }

    void AddVehicle(String vehicle_number, String chassis_number, String engine_number, String vehicle_model,
                    String vehicle_type, String fuel_type, int has_qr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(VEHICLE_NUMBER, vehicle_number);
        cv.put(VEHICLE_CH_NUMBER, chassis_number);
        cv.put(VEHICLE_ENG_NUMBER, engine_number);
        cv.put(VEHICLE_MODEL, vehicle_model);
        cv.put(VEHICLE_TYPE, vehicle_type);
        cv.put(VEHICLE_FUEL, fuel_type);
        cv.put(VEHICLE_HAS_QR, has_qr);

        long result = db.insert(VEHICLE_TABLE,null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "New Vehicle Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllVehicleData() {
        String query = "SELECT * FROM "+VEHICLE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readVehicleData(int id) {
        String query = "SELECT * FROM "+VEHICLE_TABLE+" WHERE _id = "+id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateVehicleData(String vehicle_id, String vehicle_number, String chassis_number, String engine_number, String vehicle_model,
                           String vehicle_type, String fuel_type, int has_qr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(VEHICLE_NUMBER, vehicle_number);
        cv.put(VEHICLE_CH_NUMBER, chassis_number);
        cv.put(VEHICLE_ENG_NUMBER, engine_number);
        cv.put(VEHICLE_MODEL, vehicle_model);
        cv.put(VEHICLE_TYPE, vehicle_type);
        cv.put(VEHICLE_FUEL, fuel_type);
        cv.put(VEHICLE_HAS_QR, has_qr);

        long result = db.update(VEHICLE_TABLE, cv, "_id=?", new String[]{vehicle_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Details Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void updateVehicleLicense(String vehicle_id, String vehicle_license, String vehicle_next_license){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(VEHICLE_NEW_LC_DATE, vehicle_license);
        cv.put(VEHICLE_NEXT_LC_DATE, vehicle_next_license);

        long result = db.update(VEHICLE_TABLE, cv, "_id=?", new String[]{vehicle_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "License Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void updateVehicleInsurance(String vehicle_id, String vehicle_insurance, String vehicle_next_insurance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(VEHICLE_NEW_INS_DATE, vehicle_insurance);
        cv.put(VEHICLE_NEXT_INS_DATE, vehicle_next_insurance);

        long result = db.update(VEHICLE_TABLE, cv, "_id=?", new String[]{vehicle_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Insurance Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void updateVehicleService(String vehicle_id, String vehicle_service, String vehicle_next_service){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(VEHICLE_NEW_SRV_DATE, vehicle_service);
        cv.put(VEHICLE_NEXT_SRV_DATE, vehicle_next_service);

        long result = db.update(VEHICLE_TABLE, cv, "_id=?", new String[]{vehicle_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Service Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteVehicle(String vehicle_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(VEHICLE_TABLE, "_id=?", new String[]{vehicle_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Vehicle Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllVehicles(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + VEHICLE_TABLE);
    }
}
