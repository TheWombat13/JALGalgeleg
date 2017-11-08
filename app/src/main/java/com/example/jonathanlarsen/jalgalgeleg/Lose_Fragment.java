package com.example.jonathanlarsen.jalgalgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Lose_Fragment extends Fragment {

    Galgelogik logik = new Galgelogik();
    private TextView info;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_lose, container, false);

        info = (TextView)rod.findViewById(R.id.losemessage);


        info.setText("Du har tabt, ordet var : " + logik.getOrdet());
        return rod;
    }


}
