package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

public class Sponsor {

    public String name;
    public String description;
    public String logoImageURL;
    public String prize;
    public String website;
    public String repProfileImageURL;
    public String repFirstName;
    public String repLastName;
    public String repEmail;

    public Sponsor(String name, String description, String logoImageURL, String prize, String website, String repProfileImageURL, String repFirstName, String repLastName, String repEmail){
        this.name = name;
        this.description = description;
        this.logoImageURL = logoImageURL;
        this.prize = prize;
        this.website = website;
        this.repProfileImageURL = repProfileImageURL;
        this.repFirstName = repFirstName;
        this.repLastName = repLastName;
        this.repEmail = repEmail;
    }

}
