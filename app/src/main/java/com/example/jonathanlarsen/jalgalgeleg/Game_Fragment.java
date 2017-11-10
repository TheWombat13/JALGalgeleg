package com.example.jonathanlarsen.jalgalgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.TableLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;



public class Game_Fragment extends Fragment implements View.OnClickListener {

    Timer time = new Timer();
    Galgelogik logik = new Galgelogik();
    private TextView info, timeview;
    private Button playbutton;
    private EditText editText;
    private ImageView image;
    public int seconds;
    public int minutes;



    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_game, container, false);



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
        logik.gætBogstav(bogstav);
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


    public void SaveTimer(String key, int value) {
        SharedPreferences preferences = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.putInt(key, seconds).apply();
        prefsEditor.putInt(key, minutes).apply();
        boolean savedOrNot = prefsEditor.commit();

        if(savedOrNot){
            System.out.println("saved"+minutes+":"+seconds);
        }else{
            System.out.println("Notsaved");
        }
    }

    public void LoadTimer() {
        SharedPreferences pref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        int idseconds = pref.getInt("seconds", seconds);
        int idminutes = pref.getInt("minutes", minutes);
        info.setText(idminutes+":"+idseconds);
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
        info.setText("Gæt ordet: " + logik.getSynligtOrd());
        info.append("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());

        if (logik.getAntalForkerteBogstaver() == 1) {
            image.setImageResource(R.drawable.forkert1);
        }
        if (logik.getAntalForkerteBogstaver() == 2) {
            image.setImageResource(R.drawable.forkert2);
        }
        if (logik.getAntalForkerteBogstaver() == 3) {
            image.setImageResource(R.drawable.forkert3);
        }
        if (logik.getAntalForkerteBogstaver() == 4) {
            image.setImageResource(R.drawable.forkert4);
        }
        if (logik.getAntalForkerteBogstaver() == 5) {
            image.setImageResource(R.drawable.forkert5);
        }
        if (logik.getAntalForkerteBogstaver() == 6) {
            image.setImageResource(R.drawable.forkert6);
        }

        if (logik.erSpilletVundet()) {


            SaveTimer("seconds",seconds);

           getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Win_Fragment())
                    .addToBackStack(null)
                    .commit();
           stopRecording();

        }
        if (logik.erSpilletTabt()) {
            stopRecording();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Lose_Fragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}

