package com.example.jonathanlarsen.jalgalgeleg;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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


public class Game_Fragment extends Fragment implements View.OnClickListener {

    Galgelogik logik = new Galgelogik();
    private TextView info;
    private Button playbutton;
    private EditText editText;
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_game, container, false);


        playbutton = (Button)rod.findViewById(R.id.play);
        playbutton.setOnClickListener(this);

        info = (TextView)rod.findViewById(R.id.info);

        editText = (EditText)rod.findViewById(R.id.editText);
        editText.requestFocus();

        image = (ImageView)rod.findViewById(R.id.hanging);



        return rod;
    }

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Velkomst_frag", "fragmentet blev vist!");

        // Programmatisk layout

        TableLayout tl = new TableLayout(getActivity());

        info = new TextView(getActivity());
        info.setText("Velkommen til mit fantastiske spil." +
                "\nDu skal gætte dette ord: "+logik.getSynligtOrd() +
                "\nSkriv et bogstav herunder og tryk 'Spil'.\n");
        String velkomst = getArguments().getString("velkomst");
        if (velkomst!=null) info.append(velkomst);
        tl.addView(info);

        et = new EditText(getActivity());
        et.setHint("Skriv et bogstav her.");
        tl.addView(et);

        spilKnap = new Button(getActivity());
        spilKnap.setText("Spil");
        spilKnap.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_media_play, 0, 0, 0);
        tl.addView(spilKnap);

        spilKnap.setOnClickListener(this);

        return tl;
    }

    */

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

            info.append("\nDu har vundet");
        }
        if (logik.erSpilletTabt()) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragPlaceholder, new Lose_Fragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
