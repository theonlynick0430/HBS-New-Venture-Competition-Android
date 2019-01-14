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
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.CoordinatorAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Coordinator;

public class CoordinatorsFragment extends Fragment {

    List<Coordinator> coordinators;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        coordinators = new ArrayList<>();
        //get coordinators from Firebase

        listView = getView().findViewById(R.id.listView);
        CoordinatorAdapter coordinatorAdapter = new CoordinatorAdapter(getActivity().getApplicationContext(), R.layout.coordinator_list_item, coordinators);
        listView.setAdapter(coordinatorAdapter);

        return inflater.inflate(R.layout.fragment_coordinators, null);
    }
}
