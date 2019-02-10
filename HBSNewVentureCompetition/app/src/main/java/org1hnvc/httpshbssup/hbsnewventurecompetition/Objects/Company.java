package org1hnvc.httpshbssup.hbsnewventurecompetition.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {

    public String companyID;
    public String name;
    public String description;
    public String logoImageURL;
    public Double order;
    public Double stars;
    public String website;

    public Company(String companyID, String name, String description, String logoImageURL, Double order, Double stars, String website){
        this.companyID = companyID;
        this.name = name;
        this.description = description;
        this.logoImageURL = logoImageURL;
        this.order = order;
        this.stars = stars;
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
        order = in.readByte() == 0x00 ? null : in.readDouble();
        stars = in.readByte() == 0x00 ? null : in.readDouble();
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
        if (order == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(order);
        }
        if (stars == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(stars);
        }
        dest.writeString(website);
    }

    @SuppressWarnings("unused")
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