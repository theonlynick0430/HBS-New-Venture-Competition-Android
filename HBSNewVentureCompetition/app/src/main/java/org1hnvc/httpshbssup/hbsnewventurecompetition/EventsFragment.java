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
import org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters.EventAdapter;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Firebase.FirebaseManager;
import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Event;

public class EventsFragment extends Fragment {

    private List<Event> events;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        events = new ArrayList<>();

        listView = getView().findViewById(R.id.listView);
        EventAdapter eventAdapter = new EventAdapter(getActivity().getApplicationContext(), R.layout.event_list_item, events);
        listView.setAdapter(eventAdapter);

        fetchEvents();

        return inflater.inflate(R.layout.fragment_events, null);
    }

    private void fetchEvents(){
        FirebaseManager.manager.fetchEvents(new FirebaseManager.EventCallback() {
            @Override
            public void onSuccess(Event[] events) {
                loadEvents(events);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), "An error occurred while fetching the events",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadEvents(Event[] events){
        this.events.clear();
        for(Event event: events){
            this.events.add(event);
        }
    }

}
