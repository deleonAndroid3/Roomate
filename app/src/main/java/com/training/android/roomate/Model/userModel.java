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
    private String desc;

    public userModel() {
    }

    public userModel(String fname, String lname, String contactnum, String gender, String age, String desc) {
        Fname = fname;
        Lname = lname;
        this.contactnum = contactnum;
        this.gender = gender;
        this.age = age;
        this.desc = desc;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getContactnum() {
        return contactnum;
    }

    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
