package com.it.onex.rvcommonadapter.bean;

/**
 * Created by Linsa on 2018/3/30:15:58.
 * des:
 */
public class Man {

    public Man(){

    }

    public Man(String sex, String name) {
        this.sex = sex;
        this.name = name;
    }

    private String sex;
    private String name;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Man{" +
                "sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
