package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

public class container extends AppCompatActivity {

    // Dichiarazione
    private int work;
    private int rest;
    private int cycle;
    private TextView serie;

    private FragmentManager fm = getSupportFragmentManager();
    private FragmentTransaction ft = fm.beginTransaction();
    private Handler handler=new Handler();

    private int flagRest = 0;
    private int flagWork = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        serie = findViewById(R.id.serie);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // recupero la intent e le variabili
        Intent intent = getIntent();
        work = intent.getIntExtra("work", 0);
        rest = intent.getIntExtra("rest", 0);
        cycle = intent.getIntExtra("cycle", 0);

        work();
    }

    private void work(){
        //Aggiorno la textview serie
        serie.setText(""+(flagWork+1));

        // inserisco il fragment
        Work fragmentWork = Work.newInstance(work);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragmentWork)
                .commit();

        // distruggo il fragment dopo il tempo work
        Runnable r = new Runnable() {
            public void run() {
                ft.remove(fragmentWork);
                if(flagRest++ < cycle-1)
                    rest();
            }
        };
        handler.postDelayed(r, work*1000);
    }

    private void rest(){
        // inserisco il fragment
        Rest fragmentRest = Rest.newInstance(rest);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragmentRest)
                .commit();

        // distruggo il fragment dopo il tempo work
        Runnable r = new Runnable() {
            public void run() {
                ft.remove(fragmentRest);
                if(flagWork++ < cycle)
                    work();
            }
        };
        handler.postDelayed(r, rest*1000);
    }
}