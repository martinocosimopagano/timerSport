package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Dichiarazione
    private NumberPicker npWORK;
    private NumberPicker npREST;
    private NumberPicker npCYCLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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