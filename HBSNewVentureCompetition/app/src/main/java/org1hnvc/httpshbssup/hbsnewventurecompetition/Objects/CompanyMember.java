package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

public class CompanyMember implements Comparable<CompanyMember> {

    public String firstName;
    public String lastName;
    public String profileImageURL;
    public String email;
    public String phoneNumber;
    public String linkedInURL;
    public String education;
    public String position;
    public Double order;

    public CompanyMember(String firstName, String lastName, String profileImageURL, String email, String phoneNumber, String linkedInURL, String education, String position, Double order){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageURL = profileImageURL;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.linkedInURL = linkedInURL;
        this.education = education;
        this.position = position;
        this.order = order;
    }

    public int compareTo(CompanyMember f) {

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
