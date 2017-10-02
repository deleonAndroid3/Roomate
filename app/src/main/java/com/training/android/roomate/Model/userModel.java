package com.training.android.roomate.Model;

/**
 * Created by Dyste on 9/27/2017.
 */

public class userModel {

    private String Fname;
    private String Lname;
    private String contactnum;
    private String gender;
    private String age;

    public userModel() {
    }

    public userModel(String fname, String lname, String contactnum, String gender, String age) {
        Fname = fname;
        Lname = lname;
        this.contactnum = contactnum;
        this.gender = gender;
        this.age = age;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFname() {

        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getContactnum() {
        return contactnum;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }
}
