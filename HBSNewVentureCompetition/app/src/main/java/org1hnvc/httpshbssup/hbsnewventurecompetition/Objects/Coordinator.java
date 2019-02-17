package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

public class Coordinator implements Comparable<Coordinator> {

    public String firstName;
    public String lastName;
    public String profileImageURL;
    public String position;
    public String organization;
    public String linkedInURL;
    public Double order;

    public Coordinator(String firstName, String lastName, String profileImageURL, String position, String organization, String linkedInURL, Double order){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageURL = profileImageURL;
        this.position = position;
        this.organization = organization;
        this.linkedInURL = linkedInURL;
        this.order = order;
    }

    public int compareTo(Coordinator f) {

        if (order > f.order) {
            return 1;
        }
        else if (order < f.order) {
            return -1;
        }
        else {
            return 0;
        }

    }

}
