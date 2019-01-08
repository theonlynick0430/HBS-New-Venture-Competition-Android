package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

import java.net.URL;

public class CompanyMember {

    public String firstName;
    public String lastName;
    public String profileImageURL;
    public String email;
    public String phoneNumber;
    public URL linkedInURL;
    public String education;
    public String position;

    public CompanyMember(String firstName, String lastName, String profileImageURL, String email, String phoneNumber, URL linkedInURL, String education, String position){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageURL = profileImageURL;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.linkedInURL = linkedInURL;
        this.education = education;
        this.position = position;
    }

}
