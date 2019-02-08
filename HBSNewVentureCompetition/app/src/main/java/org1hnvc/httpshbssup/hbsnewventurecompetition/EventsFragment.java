package org1hnvc.httpshbssup.hbsnewventurecompetition;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private EventAdapter eventAdapter;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null){
            return  view;
        }

        events = new ArrayList<>();

        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        listView = rootView.findViewById(R.id.listView);
        eventAdapter = new EventAdapter(getActivity(), R.layout.event_list_item, events);
        listView.setAdapter(eventAdapter);
        view = rootView;

        fetchEvents();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.event_action_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.event_action_bar_arrow){
            FirebaseManager.manager.fetchCurrentEvent(new FirebaseManager.CurrentEventCallback() {
                @Override
                public void onSuccess(String currentEvent) {
                    for (int index = 0; index < events.size(); index++){
                        if (events.get(index).eventID.equals(currentEvent)){
                            final int i = index;
                            listView.post(new Runnable() {
                                @Override
                                public void run() {
                                    listView.requestFocusFromTouch();
                                    listView.setSelection(i);
                                    listView.setChoiceMode(ListView.CHOICE_MODE_NONE);
                                }
                            });
                            break;
                        }
                    }
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(getActivity(), "An error occurred while fetching the current event",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
        return super.onOptionsItemSelected(item);
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
            eventAdapter.notifyDataSetChanged();
        }
    }

}
