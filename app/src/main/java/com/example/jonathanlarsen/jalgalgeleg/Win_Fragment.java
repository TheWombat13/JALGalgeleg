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
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;

import java.util.ArrayList;


public class Win_Fragment extends Fragment implements View.OnClickListener {


    private TextView timemessage;
    private Button cancel, tryagain;
    ArrayList<String> highscoreList;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_win, container, false);

        cancel = (Button) rod.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        tryagain = (Button) rod.findViewById(R.id.tryagain);
        tryagain.setOnClickListener(this);




 //       SharedPreferences preferences = this.getActivity().getSharedPreferences(MenuActivity.PREF_FILE_NAME, Context.MODE_PRIVATE);
 //       String wintime = preferences.getString("wintime", null);


        //     int seconds = preferences.getInt("seconds", 0);
        //     int minutes = preferences.getInt("minutes",0);


        timemessage = (TextView) rod.findViewById(R.id.winmessage);

   //     timemessage.setText("Tillykke, du har vundet! din tid er : "+ wintime);



        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == cancel) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Menu_Fragment())
                    .addToBackStack(null)
                    .commit();
        } else if (v == tryagain) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Game_Fragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void getArray() {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MenuActivity.PREF_FILE_NAME, Context.MODE_PRIVATE);
        highscoreList.clear();
        int highscoreSize = sharedPreferences.getInt("highscore_size", 0);

        for (int i = 0; i < highscoreSize; i++) {
            highscoreList.add(sharedPreferences.getString("highscore_" + i, null));
        }
    }

}
