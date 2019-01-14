package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.SponsorAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Sponsor;

public class SponsorsFragment extends Fragment {

    List<Sponsor> sponsors;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        sponsors = new ArrayList<>();
        //get sponsors from Firebase

        listView = getView().findViewById(R.id.listView);
        SponsorAdapter sponsorAdapter = new SponsorAdapter(getActivity().getApplicationContext(), R.layout.sponsor_list_item, sponsors);
        listView.setAdapter(sponsorAdapter);

        return inflater.inflate(R.layout.fragment_sponsors, null);
    }
}
