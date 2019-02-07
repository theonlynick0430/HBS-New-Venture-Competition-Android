package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

public class CompanyMember {

    public String firstName;
    public String lastName;
    public String profileImageURL;
    public String email;
    public String phoneNumber;
    public String linkedInURL;
    public String education;
    public String position;
    public String website;

    public CompanyMember(String firstName, String lastName, String profileImageURL, String email, String phoneNumber, String linkedInURL, String education, String position, String website){
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageURL = profileImageURL;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.linkedInURL = linkedInURL;
        this.education = education;
        this.position = position;
        this.website = website;
    }

}
