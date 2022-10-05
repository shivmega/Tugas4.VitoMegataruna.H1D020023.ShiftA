package com.vito.tugas_4_prak.modul;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private  String nama, email, number;

    public User(String nama, String email, String number) {
        this.nama = nama;
        this.email = email;
        this.number = number;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.email);
        dest.writeString(this.number);
    }

    public void readFromParcel(Parcel source) {
        this.nama = source.readString();
        this.email = source.readString();
        this.number = source.readString();
    }

    protected User(Parcel in) {
        this.nama = in.readString();
        this.email = in.readString();
        this.number = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
