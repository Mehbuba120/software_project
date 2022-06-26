package com.example.emergencyapp;

import android.widget.Button;

public class user {
    String fName, fdate, ftime, dispress, fheart, comment, syspress, serial;
    //Button call;

    public user() {
    }


    public user(String fName, String fdate, String ftime, String dispress, String fheart, Button call, String comment, String syspress) {
        this.fName = fName;
        this.fdate = fdate;
        this.ftime = ftime;
        this.dispress = dispress;
        fheart =fheart;
        //this.call = call;
        this.comment = comment;
        this.syspress=syspress;
        this.serial=serial;

    }

    public String getsyspress() {
        return syspress;
    }

    public void setsyspress(String syspress) {
        this.syspress =syspress;
    }

    public String getserial() {
        return serial;
    }

    public void setserial(String serial) {
        this.serial =serial;
    }


    public String getcomment() {
        return comment;
    }

    public void setcomment(String comment) {
        this.comment = comment;
    }

   /* public Button getCall() {
        return call;
    }

    public void setCall(Button call) {
        this.call = call;
    }*/

    //new

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfdate() {
        return fdate;
    }

    public void setfdate(String fdate) {
        this.fdate = fdate;
    }

    public String getftime() {
        return ftime;
    }

    public void setftime(String ftime) {
        this.ftime = ftime;
    }

    public String getdispress() {
        return dispress;
    }

    public void setdispress(String dispress) {
        this.dispress = dispress;
    }

    public String getfheart() {
        return fheart;
    }

    public void setfheart(String fheart) {
        fheart = fheart;
    }


}
