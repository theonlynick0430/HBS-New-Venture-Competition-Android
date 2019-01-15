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
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.CoordinatorAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase.FirebaseManager;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Coordinator;

public class CoordinatorsFragment extends Fragment {

    private List<Coordinator> coordinators;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        coordinators = new ArrayList<>();

        listView = getView().findViewById(R.id.listView);
        CoordinatorAdapter coordinatorAdapter = new CoordinatorAdapter(getActivity().getApplicationContext(), R.layout.coordinator_list_item, coordinators);
        listView.setAdapter(coordinatorAdapter);

        fetchCoordinators();

        return inflater.inflate(R.layout.fragment_coordinators, null);
    }

    private void fetchCoordinators(){
        FirebaseManager.manager.fetchCoordinators(new FirebaseManager.CoordinatorCallback() {
            @Override
            public void onSuccess(Coordinator[] coordinators) {
                loadCoordinators(coordinators);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), "An error occurred while fetching the coordinators",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadCoordinators(Coordinator[] coordinators){
        this.coordinators.clear();
        for(Coordinator coordinator: coordinators){
            this.coordinators.add(coordinator);
        }
    }

}
