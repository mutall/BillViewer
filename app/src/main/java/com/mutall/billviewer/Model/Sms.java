package com.mutall.billviewer.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Sms implements Parcelable {
    private String number;
    private String message;

    public Sms() {
    }

    public Sms(String number, String message) {

        this.number = number;
        this.message = message;
    }

    protected Sms(Parcel in) {
        number = in.readString();
        message = in.readString();
    }

    public static final Creator<Sms> CREATOR = new Creator<Sms>() {
        @Override
        public Sms createFromParcel(Parcel in) {
            return new Sms(in);
        }

        @Override
        public Sms[] newArray(int size) {
            return new Sms[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("number: ").append(this.number).append("\n").append("body: ").append(this.message);
       return builder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(number);
        parcel.writeString(message);
    }
}
