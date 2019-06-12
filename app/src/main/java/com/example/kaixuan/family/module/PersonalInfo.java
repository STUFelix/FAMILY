package com.example.kaixuan.family.module;

import io.realm.RealmObject;

public class PersonalInfo extends RealmObject {
    private String name;
    private String birthday;
    private int age;
    private int isLiving;
    private String achievement;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIsLiving() {
        return isLiving;
    }

    public void setIsLiving(int isLiving) {
        this.isLiving = isLiving;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }


}
