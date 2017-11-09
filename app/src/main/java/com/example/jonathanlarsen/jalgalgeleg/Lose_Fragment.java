package com.example.jonathanlarsen.jalgalgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;


public class Lose_Fragment extends Fragment implements View.OnClickListener{

    Galgelogik logik = new Galgelogik();
    private TextView info;
    private Button cancel, tryagain;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_lose, container, false);

        info = (TextView)rod.findViewById(R.id.losemessage);

        cancel = (Button)rod.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        tryagain = (Button)rod.findViewById(R.id.tryagain);
        tryagain.setOnClickListener(this);


        info.setText("Du har tabt, ordet var : " + logik.getOrdet());
        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == cancel) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Menu_Fragment())
                    .addToBackStack(null)
                    .commit();
        }
        else if (v == tryagain) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Game_Fragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}