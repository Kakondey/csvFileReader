package com.kakondey701.kd.csvreader;

/**
 * Created by PUJA on 1/19/2018.
 */

public class Details
{
    private String roll_number;

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;



    @Override
    public String toString() {
        return
                "Name: " + name +"\t\t"+
                ", Roll No:" + roll_number
                ;
    }
}
