package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {

    public String companyID;
    public String name;
    public String description;
    public String logoImageURL;
    public Double order;
    public String website;

    public Company(String companyID, String name, String description, String logoImageURL, Double order, String website){
        this.companyID = companyID;
        this.name = name;
        this.description = description;
        this.logoImageURL = logoImageURL;
        this.order = order;
        this.website = website;
    }

    @Override
    public String toString() {
        return name;
    }


    protected Company(Parcel in) {
        companyID = in.readString();
        name = in.readString();
        description = in.readString();
        logoImageURL = in.readString();
        order = in.readDouble();
        website = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyID);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(logoImageURL);
        dest.writeDouble(order);
        dest.writeString(website);
    }

    public static final Parcelable.Creator<Company> CREATOR = new Parcelable.Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
}