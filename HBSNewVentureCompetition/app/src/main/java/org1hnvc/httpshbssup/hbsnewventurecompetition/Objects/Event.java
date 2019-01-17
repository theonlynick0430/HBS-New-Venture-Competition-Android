package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

import com.google.firebase.*;

public class Event {

    public String eventID;
    public Timestamp time;
    public String description;

    public Event(String eventID, Timestamp time, String description){
        this.eventID = eventID;
        this.time = time;
        this.description = description;
    }

}
