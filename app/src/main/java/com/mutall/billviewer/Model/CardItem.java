package com.mutall.billviewer.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CardItem implements Parcelable {
    private String title;
    private String body;

    public CardItem(String title, String body) {
        this.title = title;
        this.body = body;
    }

    protected CardItem(Parcel in) {
        title = in.readString();
        body = in.readString();
    }

    public static final Creator<CardItem> CREATOR = new Creator<CardItem>() {
        @Override
        public CardItem createFromParcel(Parcel in) {
            return new CardItem(in);
        }

        @Override
        public CardItem[] newArray(int size) {
            return new CardItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(body);
    }
}
