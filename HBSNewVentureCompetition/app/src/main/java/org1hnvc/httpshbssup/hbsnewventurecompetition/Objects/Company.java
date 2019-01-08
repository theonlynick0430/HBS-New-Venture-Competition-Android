package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

public class Company {

    public String companyID;
    public String name;
    public String description;
    public String logoImageURL;
    public Integer order;
    public Double stars;

    public Company(String companyID, String name, String description, String logoImageURL, Integer order, Double stars){
        this.companyID = companyID;
        this.name = name;
        this.description = description;
        this.logoImageURL = logoImageURL;
        this.order = order;
        this.stars = stars;
    }

}
