package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

public class Judge implements Comparable<Judge> {

    public String firstName;
    public String lastName;
    public String profileImageURL;
    public String linkedInURL;
    public String description;
    public Double order;

    public Judge(String firstName, String lastName, String profileImageURL, String linkedInURL, String description, Double order){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageURL = profileImageURL;
        this.linkedInURL = linkedInURL;
        this.description = description;
        this.order = order;
    }

    public int compareTo(Judge f) {

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
