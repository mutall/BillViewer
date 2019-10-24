package com.mutall.billviewer.Model;

public class Sms {
    private String number;
    private String message;

    public Sms() {
    }

    public Sms(String number, String message) {

        this.number = number;
        this.message = message;
    }

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
}
