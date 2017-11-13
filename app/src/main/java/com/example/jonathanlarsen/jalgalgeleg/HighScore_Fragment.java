package com.example.jonathanlarsen.jalgalgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class HighScore_Fragment extends android.support.v4.app.Fragment {

    ListView listView;
    ArrayList<String> highscoreList;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_highscore, container, false);

        highscoreList = new ArrayList<>();

        listView = rod.findViewById(R.id.list);

        getArray();

      //  System.out.println(highscoreList.get(0));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, highscoreList);

        listView.setAdapter(adapter);

        return rod;
    }

    public void getArray() {

        SharedPreferences preferences = this.getActivity().getSharedPreferences(MenuActivity.PREF_FILE_NAME, Context.MODE_PRIVATE);
        highscoreList.clear();
        int highscoreSize = preferences.getInt("highscore_size",0);
        System.out.println("størrelsen på highscore er: " + highscoreSize);

        for (int i = 0; i < highscoreSize; i++) {
            highscoreList.add(preferences.getString("highscore_" + i, null));
            System.out.println("tilføjer til listen: " + preferences.getString("highscore_" + i, null));
        }
    }
}
