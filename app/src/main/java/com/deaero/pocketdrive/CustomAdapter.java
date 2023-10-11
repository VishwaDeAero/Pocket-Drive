package com.deaero.pocketdrive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.VehiclesViewHolder> {

    private Context context;
    private ArrayList vehicle_id, vehicle_number, vehicle_type;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "vehicle_info";
    private static final String KEY_ID = "vehicle_id";
    private static final String KEY_NUMBER = "vehicle_number";
    private static final String KEY_TYPE = "vehicle_type";

    CustomAdapter(Context context, ArrayList vehicle_id, ArrayList vehicle_number, ArrayList vehicle_type) {
        this.context = context;
        this.vehicle_id = vehicle_id;
        this.vehicle_number = vehicle_number;
        this.vehicle_type = vehicle_type;
    }
    @NonNull
    @Override
    public CustomAdapter.VehiclesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vehicle_btn, parent, false);
        return new VehiclesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.VehiclesViewHolder holder, int position) {
        holder.vehicle_btn.setText(String.valueOf(vehicle_number.get(position)));
        holder.vehicle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_ID,String.valueOf(vehicle_id.get(position)));
                editor.putString(KEY_NUMBER,String.valueOf(vehicle_number.get(position)));
                editor.putString(KEY_TYPE,String.valueOf(vehicle_type.get(position)));
                editor.apply();
                Intent intent = new Intent(context, VehicleMenu.class);
//                intent.putExtra("vehicleID",String.valueOf(vehicle_id.get(position)));
//                intent.putExtra("vehicleNumber",String.valueOf(vehicle_number.get(position)));
//                intent.putExtra("vehicleType",String.valueOf(vehicle_type.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicle_id.size();
    }

    public class VehiclesViewHolder extends RecyclerView.ViewHolder {

        Button vehicle_btn;
        LinearLayout vahicleBtnLayout;
        public VehiclesViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicle_btn = itemView.findViewById(R.id.btnVehicle);
            vahicleBtnLayout = itemView.findViewById(R.id.vahicleBtnLayout);
        }
    }
}