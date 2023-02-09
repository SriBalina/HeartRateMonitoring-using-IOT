package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends SignUpActivity {

    EditText edtFullName,edtMobile;
    EditText BGrp;
    EditText Age;
    RadioButton genderradioButton,male,female;
    RadioGroup radioGroup;
    ProgressBar progressBar;
    Button btnSignUp;
    String txtFullName,txtMobile,txtAge,txtBGrp,txtGender;
    String UPassword, UName,UEmail,UMob;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseDatabase firebaseDatabase,fire1;
    private DatabaseReference databaseReference,fire2;
    private FirebaseStorage storage;
    private StorageReference storageref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional);
        edtFullName = findViewById(R.id.edtAddFullName);
        edtMobile = findViewById(R.id.edtSignUpMobile);
        btnSignUp = findViewById(R.id.btnConf);
        progressBar = findViewById(R.id.AddProgressBar);
        BGrp=findViewById(R.id.bldgrp);
        Age=findViewById(R.id.age);
        radioGroup=findViewById(R.id.radioGroup);
        male=findViewById(R.id.rbtn1);
        female=findViewById(R.id.rbtn1);




        Intent intent=getIntent();
        UName=intent.getStringExtra("FullName");
        UEmail=intent.getStringExtra("Email");
        UPassword=intent.getStringExtra("pass");
        UMob=intent.getStringExtra("MbNo");

        firebaseDatabase = FirebaseDatabase.getInstance();
        fire1 = FirebaseDatabase.getInstance();
        fire2=fire1.getReference();
        databaseReference = firebaseDatabase.getReference();
        storageref=FirebaseStorage.getInstance().getReference();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFullName = edtFullName.getText().toString();
                txtMobile = edtMobile.getText().toString().trim();
                txtAge = Age.getText().toString().trim();
                txtBGrp = BGrp.getText().toString().trim();



                if (!TextUtils.isEmpty(txtFullName)) {
                    if (!TextUtils.isEmpty(txtMobile)) {
                        if (txtMobile.length() == 10) {
                            SignUpUser();
                                }
                        else {
                            edtMobile.setError("Enter a valid Mobile");
                             }
                            } else {
                        edtMobile.setError("Mobile Number Field can't be empty");
                    }
                } else {
                    edtFullName.setError("Full Name Field can't be empty");
                }
            }
        });

    }

    public void SignUpUser() {
        progressBar.setVisibility(View.VISIBLE);
        btnSignUp.setVisibility(View.INVISIBLE);

                Map<String, Object> user = new HashMap<>();
                user.put("FullName", UName);
                user.put("Email", UEmail);
                user.put("Mobile", UMob);
                user.put("GuardName", txtFullName);
                user.put("GuardMobileNumber", txtMobile);
                user.put("Gender",txtGender);
                user.put("Age",txtAge);
                user.put("Blood group",txtBGrp);



                databaseReference.child("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddActivity.this, OptionsActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        btnSignUp.setVisibility(View.VISIBLE);
                    }
                });

    }
}