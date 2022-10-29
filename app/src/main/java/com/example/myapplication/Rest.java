package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Rest extends Fragment {


    private int time;

    public static Rest newInstance(int time) {
        Rest fragment = new Rest();

        Bundle args = new Bundle();
        args.putInt("time", time);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            time = getArguments().getInt("time");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Instantiate the contents of layout XML files into their corresponding View objects
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment, container, false); //pass the correct layout name for the fragment

        // Imposto il contenuto della textview
        TextView tv = (TextView) view.findViewById(R.id.textView);

        int cd = (time+1)* 1000;
        new CountDownTimer(cd, 1000) {
            public void onTick(long millisUntilFinished) {
                tv.setText("Recupera per altri: " + millisUntilFinished / 1000 + " secondi!");
            }

            public void onFinish() {
                tv.setText("Fine!");
            }

        }.start();

        return view;
    }
}