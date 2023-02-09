package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RecordsActivity<eventListener> extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseDatabase firebaseDatabase,fire1;
    private DatabaseReference databaseReference,fire2;
    private FirebaseStorage storage;
    private StorageReference storageref;
    private TextView t;int i=0;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        firebaseDatabase = FirebaseDatabase.getInstance();
        fire1 = FirebaseDatabase.getInstance();
        fire2=fire1.getReference();
        databaseReference = firebaseDatabase.getReference();
        storageref=FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("records").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        t = findViewById(R.id.t1);
        scrollView = (ScrollView) findViewById(R.id.viewScroll);
        scrollView.post(new Runnable() { // Snippet from http://stackoverflow.com/a/4612082/1287554
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
        getdata();
        //Back button
        TextView t = (TextView) findViewById(R.id.text1);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecordsActivity.this, OptionsActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void getdata() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                while(i<5) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        String value = snapshot.getValue().toString();
                        t.setText(value);
                    }
                    i+=1;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecordsActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

  /*  mAuth = FirebaseAuth.getInstance();
    // Initialize Firebase Firestore
    db = FirebaseFirestore.getInstance();
     ref= FirebaseDatabase.getInstance().getReference().child("users");
    ref.addValueEventListener(new ValueEventListener){
        public void onDataChange(DataSnapshot datasnapshot){
            for(DataSnapshot snapshot:datasnapshot.getChildren())
            {
                String ma=snapshot.child(),getValue().toSTring();
            }
        }
        public void onCancelled(DatabaseError error)
        {
        }
    });
*/


