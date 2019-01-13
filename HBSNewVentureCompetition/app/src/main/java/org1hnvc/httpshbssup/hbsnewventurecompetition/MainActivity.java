package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(new EventsFragment(), "Program");
    }

    private boolean loadFragment(Fragment fragment, String title){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            getSupportActionBar().setTitle(title);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        String title = "";

        switch (menuItem.getItemId()){
            case R.id.navigation_program:
                fragment = new EventsFragment();
                title = "Program";
                break;
            case R.id.navigation_startups:
                fragment = new CompaniesFragment();
                title = "Startups";
                break;
            case R.id.navigation_judges:
                fragment = new JudgesFragment();
                title = "Judges";
                break;
            case R.id.navigation_sponsors:
                fragment = new SponsorsFragment();
                title = "Sponsors";
                break;
            case R.id.navigation_coordinators:
                fragment = new CoordinatorsFragment();
                title = "Coordinators";
                break;
        }

        return loadFragment(fragment, title);
    }
}
