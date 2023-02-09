package com.example.example;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PermissionsActivity extends AppCompatActivity {
    BluetoothAdapter bluetoothAdapter;
    LocationManager locationManager;
    boolean GpsStatus;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        Button b1 = (Button) findViewById(R.id.btn1);
        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(i2);
            }

        });
        Button gps = (Button) findViewById(R.id.btn2);
        context = getApplicationContext();
        CheckGpsStatus();
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i1);
            }
        });
        //ok button to move to next page
        Button b2 = (Button) findViewById(R.id.btnOk);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PermissionsActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        //Back button
        TextView t = (TextView) findViewById(R.id.text1);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PermissionsActivity.this, OptionsActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void CheckGpsStatus() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}
