package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Dichiarazione
    private NumberPicker npWORK;
    private NumberPicker npREST;
    private NumberPicker npCYCLE;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // test firestore
/*
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        // Create a new user with a first, middle, and last name
        user = new HashMap<>();
        user.put("first", "Alan");
        user.put("middle", "Mathison");
        user.put("last", "Turing");
        user.put("born", 1912);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        // test lettura da db
        db.collection("users").whereEqualTo("first", "Ada")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, " ok ");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Inizializzazione
        npWORK = findViewById(R.id.numberPicker1);
        npREST = findViewById(R.id.numberPicker2);
        npCYCLE = findViewById(R.id.numberPicker3);

        setRange();

    }

    private void setRange(){
        npWORK.setMinValue(10);
        npWORK.setMaxValue(180);

        npREST.setMinValue(5);
        npREST.setMaxValue(120);

        npCYCLE.setMinValue(1);
        npCYCLE.setMaxValue(20);
    }

    public void reset(View view){
        npWORK.setValue(10);
        npREST.setValue(5);
        npCYCLE.setValue(1);
        Toast.makeText(getApplicationContext(),"Reset effettuato!",Toast.LENGTH_SHORT).show();
    }

    public void start(View view) {
        Intent intent = new Intent(this, container.class);

        intent.putExtra("work", npWORK.getValue());
        intent.putExtra("rest", npREST.getValue());
        intent.putExtra("cycle", npCYCLE.getValue());

        startActivity(intent);
    }

}