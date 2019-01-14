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
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.EventAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Event;

public class EventsFragment extends Fragment {

    List<Event> events;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        events = new ArrayList<>();
        //get events from Firebase

        listView = getView().findViewById(R.id.listView);
        EventAdapter eventAdapter = new EventAdapter(getActivity().getApplicationContext(), R.layout.event_list_item, events);
        listView.setAdapter(eventAdapter);

        return inflater.inflate(R.layout.fragment_events, null);
    }
}
