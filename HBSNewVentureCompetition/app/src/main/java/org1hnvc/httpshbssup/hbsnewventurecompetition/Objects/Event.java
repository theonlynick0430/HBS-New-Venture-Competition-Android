package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

import com.google.firebase.*;

public class Event implements Comparable<Event> {

    public String eventID;
    public Timestamp time;
    public String description;

    public Event(String eventID, Timestamp time, String description){
        this.eventID = eventID;
        this.time = time;
        this.description = description;
    }

    public int compareTo(Event f) {

        return time.compareTo(f.time);

    }

}
