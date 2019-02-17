package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    CompaniesFragment companiesFragment;
    EventsFragment eventsFragment;
    JudgesFragment judgesFragment;
    SponsorsFragment sponsorsFragment;
    CoordinatorsFragment coordinatorsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        companiesFragment = new CompaniesFragment();
        eventsFragment = new EventsFragment();
        judgesFragment = new JudgesFragment();
        sponsorsFragment = new SponsorsFragment();
        coordinatorsFragment = new CoordinatorsFragment();

        loadFragment(companiesFragment, "Startups");
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
            case R.id.navigation_startups:
                fragment = companiesFragment;
                title = "Startups";
                break;
            case R.id.navigation_program:
                fragment = eventsFragment;
                title = "Program";
                break;
            case R.id.navigation_judges:
                fragment = judgesFragment;
                title = "Judges";
                break;
            case R.id.navigation_sponsors:
                fragment = sponsorsFragment;
                title = "Sponsors";
                break;
            case R.id.navigation_coordinators:
                fragment = coordinatorsFragment;
                title = "Organizers";
                break;
        }

        return loadFragment(fragment, title);
    }
}
