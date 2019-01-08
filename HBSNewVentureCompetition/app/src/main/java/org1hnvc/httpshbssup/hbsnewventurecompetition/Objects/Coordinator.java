package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

import java.net.URL;

public class Coordinator {

    public String firstName;
    public String lastName;
    public String profileImageURL;
    public String position;
    public String organization;
    public URL linkedInURL;

    public Coordinator(String firstName, String lastName, String profileImageURL, String position, String organization, URL linkedInURL){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageURL = profileImageURL;
        this.position = position;
        this.organization = organization;
        this.linkedInURL = linkedInURL;
    }

}
