package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

public class Sponsor implements Comparable<Sponsor> {

    public String name;
    public String description;
    public String logoImageURL;
    public String prize;
    public String website;
    public String repProfileImageURL;
    public String repFirstName;
    public String repLastName;
    public String repEmail;
    public Double order;

    public Sponsor(String name, String description, String logoImageURL, String prize, String website, String repProfileImageURL, String repFirstName, String repLastName, String repEmail, Double order){
        this.name = name;
        this.description = description;
        this.logoImageURL = logoImageURL;
        this.prize = prize;
        this.website = website;
        this.repProfileImageURL = repProfileImageURL;
        this.repFirstName = repFirstName;
        this.repLastName = repLastName;
        this.repEmail = repEmail;
        this.order = order;
    }

    public int compareTo(Sponsor f) {

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
