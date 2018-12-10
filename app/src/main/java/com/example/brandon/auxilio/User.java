package com.example.brandon.auxilio;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Brandon on 2/20/2016.
 */
public class User implements Parcelable {
    private String address;
    private boolean helper;
    private int housing;
    private int food;
    private int medical;

    public User(String address, boolean helper, int housing, int food, int medical) {
        if (!helper) {
            this.address = "";
            this.housing = 0;
            this.food = 0;
            this.medical = 0;
        } else {
            this.address = address;
            this.housing = housing;
            this.food = food;
            this.medical = medical;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isHelper() {
        return helper;
    }

    public void setHelper(boolean helper) {
        this.helper = helper;
    }

    public int getHousing() {
        return housing;
    }

    public void setHousing(int housing) {
        this.housing = housing;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getMedical() {
        return medical;
    }

    public void setMedical(int medical) {
        this.medical = medical;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeInt(helper ? 1 : 0);
        dest.writeInt(housing);
        dest.writeInt(food);
        dest.writeInt(medical);
    }

    private User(Parcel in) {
        address = in.readString();
        if (in.readInt() == 0) {
            helper = false;
        } else {
            helper = true;
        }
        housing = in.readInt();
        food = in.readInt();
        medical = in.readInt();
    }
}
