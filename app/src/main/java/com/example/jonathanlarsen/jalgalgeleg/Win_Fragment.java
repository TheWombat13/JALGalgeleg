package com.example.jonathanlarsen.jalgalgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;


public class Win_Fragment extends Fragment {


    private TextView timemessage;
    public int seconds;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_win, container, false);

        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int id = pref.getInt("seconds", seconds);

        timemessage = (TextView)rod.findViewById(R.id.winmessage);

        timemessage.setText("Tillykke, du har vundet! din tid er : "+id);
        System.out.println(id);

        return rod;
    }


}
