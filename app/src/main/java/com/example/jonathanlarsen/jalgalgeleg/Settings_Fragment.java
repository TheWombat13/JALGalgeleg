package com.example.jonathanlarsen.jalgalgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Settings_Fragment extends Fragment implements View.OnClickListener {

    private Button resethighscore;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_settings, container, false);

        resethighscore = (Button)rod.findViewById(R.id.reset);
        resethighscore.setOnClickListener(this);


        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == resethighscore) {
            SharedPreferences preferences = this.getActivity().getSharedPreferences(MenuActivity.PREF_FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = preferences.edit();
            prefsEditor.clear().commit();
        }
    }
}
