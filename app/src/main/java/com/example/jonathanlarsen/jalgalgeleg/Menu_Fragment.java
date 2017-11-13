package com.example.jonathanlarsen.jalgalgeleg;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Menu_Fragment extends Fragment implements View.OnClickListener {

    Button gamebutton, settingsbutton, helpbutton, highscorebutton;



    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_menu, container, false);

        gamebutton = (Button)rod.findViewById(R.id.game);
        gamebutton.setOnClickListener(this);

        settingsbutton = (Button)rod.findViewById(R.id.settings);
        settingsbutton.setOnClickListener(this);

        helpbutton = (Button)rod.findViewById(R.id.help);
        helpbutton.setOnClickListener(this);

        highscorebutton = (Button)rod.findViewById(R.id.highscore);
        highscorebutton.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {

        if (v == gamebutton) {
            Game_Fragment fragment = new Game_Fragment();
            Bundle argumenter = new Bundle(); // Overfør data til fragmentet
            argumenter.putString("velkomst", "\n\nHalløj fra Hovedmenu_frag!\n");
            fragment.setArguments(argumenter);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, fragment)
                    .addToBackStack(null)
                    .commit();
        }

         else if (v == settingsbutton) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Settings_Fragment())
                    .addToBackStack(null)
                    .commit();
        }

        else if (v == helpbutton) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Help_Fragment())
                    .addToBackStack(null)
                    .commit();
        }

        else if (v == highscorebutton) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new HighScore_Fragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
