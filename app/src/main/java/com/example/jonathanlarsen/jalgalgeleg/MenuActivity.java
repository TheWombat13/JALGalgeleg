package com.example.jonathanlarsen.jalgalgeleg;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import java.util.Timer;

public class MenuActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_frag);

        if (savedInstanceState == null) {
            Fragment fragment = new Menu_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragPlaceholder, fragment)
                    .commit();
        }

        setTitle("Hovedaktivitet");
        // Man kan trykke på app-ikonet i øverste venstre hjørne
        // (og det betyder at brugeren vil navigere op i hierakiet)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void Update() {
        // Write your logic here.
    }

}
