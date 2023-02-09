package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        //options to permission
        Button b1=(Button)findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OptionsActivity.this,PermissionsActivity.class);
                startActivity(i);
                finish();
            }
        });
        //Back button
        TextView t=(TextView)findViewById(R.id.text1);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OptionsActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        //moving from options to records
        Button b2 = (Button) findViewById(R.id.btn2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OptionsActivity.this, RecordsActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}