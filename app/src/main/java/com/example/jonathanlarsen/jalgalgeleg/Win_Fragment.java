package com.example.jonathanlarsen.jalgalgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.SharedPreferences;


public class Win_Fragment extends Fragment {



    private TextView timemessage;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_win, container, false);

        SharedPreferences pref = this.getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        int idseconds = getResources().getInteger()
      //  int idseconds = pref.getInt("seconds", 0);
      //  int idminutes = pref.getInt("minutes", 0);


        timemessage = (TextView)rod.findViewById(R.id.winmessage);

        timemessage.setText("Tillykke, du har vundet! din tid er : "+idminutes+":"+idseconds);
        System.out.println(idseconds);

        FragmentManager fm = getFragmentManager();
        Game_Fragment fragm = (Game_Fragment)fm.findFragmentById(R.id.timer);


        return rod;
    }


}
