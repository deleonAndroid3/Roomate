package com.training.android.roomate.models;

/**
 * Created by cicct on 10/3/2017.
 */

public class Home {
    private String homeId;
    private String homeName;
    private String homeRate;
    private String homePic;

    public Home() {
    }

    public Home(String homeId, String homeName, String homeRate, String homePic) {
        this.homeId = homeId;
        this.homeName = homeName;
        this.homeRate = homeRate;
        this.homePic = homePic;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getHomeRate() {
        return homeRate;
    }

    public void setHomeRate(String homeRate) {
        this.homeRate = homeRate;
    }

    public String getHomePic() {
        return homePic;
    }

    public void setHomePic(String homePic) {
        this.homePic = homePic;
    }
}