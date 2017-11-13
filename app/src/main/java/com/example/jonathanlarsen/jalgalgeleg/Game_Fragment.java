package com.example.jonathanlarsen.jalgalgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class Game_Fragment extends Fragment implements View.OnClickListener {

    Timer time = new Timer();
    private TextView info, timeview;
    private Button playbutton;
    private EditText editText;
    private ImageView image;
    public int seconds;
    public int minutes;
    ArrayList<String> highscoreList;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_game, container, false);

        highscoreList = new ArrayList<>();

        playbutton = (Button)rod.findViewById(R.id.play);
        playbutton.setOnClickListener(this);

        info = (TextView)rod.findViewById(R.id.info);

        timeview = (TextView)rod.findViewById(R.id.timer);

        editText = (EditText)rod.findViewById(R.id.editText);
        editText.requestFocus();

        image = (ImageView)rod.findViewById(R.id.hanging);



        timecounter();

        return rod;
    }



    @Override
    public void onClick(View view) {
        String bogstav = editText.getText().toString();
        if (bogstav.length() != 1) {
            editText.setError("Skriv præcis ét bogstav");
            return;
        }
        MenuActivity.logik.gætBogstav(bogstav);
        editText.setText("");
        editText.setError(null);
        opdaterSkærm();
    }

    private void timecounter() {
        //Set the schedule function and rate
        time.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        timeview.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));

                        seconds += 1;
                        if(seconds == 59)

                        {
                            timeview.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                            seconds=0;
                            minutes=minutes+1;
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    public void stopRecording() {
        if (time != null) {
            time.cancel();
            time.purge();
            time = null;
        } else {
            Log.e("TRACK_RECORDING_SERVICE","Timer already null.");
        }
    }

    private void opdaterSkærm() {
        info.setText("Gæt ordet: " + MenuActivity.logik.getSynligtOrd());
        info.append("\n\nDu har " + MenuActivity.logik.getAntalForkerteBogstaver() + " forkerte:" + MenuActivity.logik.getBrugteBogstaver());

        if (MenuActivity.logik.getAntalForkerteBogstaver() == 1) {
            image.setImageResource(R.drawable.forkert1);
        }
        if (MenuActivity.logik.getAntalForkerteBogstaver() == 2) {
            image.setImageResource(R.drawable.forkert2);
        }
        if (MenuActivity.logik.getAntalForkerteBogstaver() == 3) {
            image.setImageResource(R.drawable.forkert3);
        }
        if (MenuActivity.logik.getAntalForkerteBogstaver() == 4) {
            image.setImageResource(R.drawable.forkert4);
        }
        if (MenuActivity.logik.getAntalForkerteBogstaver() == 5) {
            image.setImageResource(R.drawable.forkert5);
        }
        if (MenuActivity.logik.getAntalForkerteBogstaver() == 6) {
            image.setImageResource(R.drawable.forkert6);
        }

        if (MenuActivity.logik.erSpilletVundet()) {

            getArray();
            String wintime = minutes+":"+seconds;
            highscoreList.add(wintime);
            System.out.println("burde printe" + highscoreList.get(0));

            updateHighscore();

            SharedPreferences preferences = this.getActivity().getSharedPreferences(MenuActivity.PREF_FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = preferences.edit();
            prefsEditor.putString("wintime", wintime).apply();

           getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Win_Fragment())
                    .addToBackStack(null)
                    .commit();
           stopRecording();

        }
        if (MenuActivity.logik.erSpilletTabt()) {
            stopRecording();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Lose_Fragment())
                    .addToBackStack(null)
                    .commit();
        }




        }

        public void updateHighscore() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor prefsEditor = preferences.edit();
            prefsEditor.putInt("highscore_size", highscoreList.size());

            for (int i=0; i<highscoreList.size(); i++) {
                prefsEditor.remove("highscore_"+ i);
                prefsEditor.putString("highscore_"+i, highscoreList.get(i));

            }

            prefsEditor.apply();
            //reset sharedPreferences
            //prefsEditor.clear().commit();
    }

        public void getArray() {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            highscoreList.clear();
            int highscoreSize = sharedPreferences.getInt("highscore_size", 0);

            for (int i = 0; i < highscoreSize; i++) {
                highscoreList.add(sharedPreferences.getString("highscore_" + i, null));
            }
        }
}

