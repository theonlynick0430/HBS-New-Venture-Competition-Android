package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

import java.net.URL;

public class Judge {

    public String firstName;
    public String lastName;
    public String profileImageURL;
    public String linkedInURL;
    public String description;

    public Judge() { }

    public Judge(String firstName, String lastName, String profileImageURL, String linkedInURL, String description){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageURL = profileImageURL;
        this.linkedInURL = linkedInURL;
        this.description = description;
    }

}
