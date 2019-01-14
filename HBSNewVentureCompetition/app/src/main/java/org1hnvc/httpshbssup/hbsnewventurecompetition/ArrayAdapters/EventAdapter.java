package org1hnvc.httpshbssup.hbsnewventurecompetition.ArrayAdapters;

import org1hnvc.httpshbssup.hbsnewventurecompetition.Objects.Event;
import org1hnvc.httpshbssup.hbsnewventurecompetition.R;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    Context mCtx;
    int resource;
    List<Event> eventList;

    public EventAdapter(Context mCtx, int resource, List<Event> eventList){
        super(mCtx, resource, eventList);
        this.mCtx = mCtx;
        this.resource = resource;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = eventList.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);
        TextView timeTV = view.findViewById(R.id.timeTV);
        TextView descriptionTV = view.findViewById(R.id.descriptionTV);

        Date date = event.time.toDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        timeTV.setText(simpleDateFormat.format(date));
        descriptionTV.setText(event.description);

        return view;
    }
}
