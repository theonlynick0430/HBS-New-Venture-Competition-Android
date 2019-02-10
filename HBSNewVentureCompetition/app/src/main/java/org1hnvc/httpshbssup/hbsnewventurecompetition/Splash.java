package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Splash extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        setTitle("Welcome");

        TextView welcomeTV = findViewById(R.id.welcomeTV);
        Button continueBtn = findViewById(R.id.continueBtn);

        final SharedPreferences prefs = getSharedPreferences("org1hnvc.httpshbssup.hbsnewventurecompetition", Context.MODE_PRIVATE);
        if (prefs.getBoolean("FirstLaunch", true) == false){
            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        welcomeTV.setMovementMethod(new ScrollingMovementMethod());
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.edit().putBoolean("FirstLaunch", false).apply();
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}
