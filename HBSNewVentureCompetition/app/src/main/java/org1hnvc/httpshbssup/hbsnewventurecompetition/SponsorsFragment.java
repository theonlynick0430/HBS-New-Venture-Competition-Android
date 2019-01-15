package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.SponsorAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase.FirebaseManager;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Sponsor;

public class SponsorsFragment extends Fragment {

    private List<Sponsor> sponsors;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        sponsors = new ArrayList<>();

        listView = getView().findViewById(R.id.listView);
        SponsorAdapter sponsorAdapter = new SponsorAdapter(getActivity().getApplicationContext(), R.layout.sponsor_list_item, sponsors);
        listView.setAdapter(sponsorAdapter);

        fetchSponsors();

        return inflater.inflate(R.layout.fragment_sponsors, null);
    }

    private void fetchSponsors(){
        FirebaseManager.manager.fetchSponsors(new FirebaseManager.SponsorCallback() {
            @Override
            public void onSuccess(Sponsor[] sponsors) {
                loadSponsors(sponsors);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), "An error occurred while fetching the sponsors",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadSponsors(Sponsor[] sponsors){
        this.sponsors.clear();
        for(Sponsor sponsor: sponsors){
            this.sponsors.add(sponsor);
        }
    }

}
