package com.example.branchdemoapp.Models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ShopModel implements Parcelable {

    private Drawable dressImage;
    private String dressName;
    private int dressCost;

    public ShopModel(Drawable dressImage, String dressName, int dressCost) {
        this.dressImage = dressImage;
        this.dressName = dressName;
        this.dressCost = dressCost;
    }

    protected ShopModel(Parcel in) {
        dressName = in.readString();
        dressCost = in.readInt();
    }

    public static final Creator<ShopModel> CREATOR = new Creator<ShopModel>() {
        @Override
        public ShopModel createFromParcel(Parcel in) {
            return new ShopModel(in);
        }

        @Override
        public ShopModel[] newArray(int size) {
            return new ShopModel[size];
        }
    };

    public Drawable getDressImage() {
        return dressImage;
    }

    public void setDressImage(Drawable dressImage) {
        this.dressImage = dressImage;
    }

    public String getDressName() {
        return dressName;
    }

    public void setDressName(String dressName) {
        this.dressName = dressName;
    }

    public int getDressCost() {
        return dressCost;
    }

    public void setDressCost(int dressCost) {
        this.dressCost = dressCost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(dressName);
        parcel.writeInt(dressCost);
    }
}
