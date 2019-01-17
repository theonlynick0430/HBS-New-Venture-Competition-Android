package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

public class Coordinator {

    public String firstName;
    public String lastName;
    public String profileImageURL;
    public String position;
    public String organization;
    public String linkedInURL;

    public Coordinator(String firstName, String lastName, String profileImageURL, String position, String organization, String linkedInURL){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageURL = profileImageURL;
        this.position = position;
        this.organization = organization;
        this.linkedInURL = linkedInURL;
    }

}
