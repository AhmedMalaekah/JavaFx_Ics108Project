package com.example.ics108project;

import com.example.ics108project.User;

import java.io.Serializable;


public class Ticket implements Serializable {


    private String username;
    private int seatNum;
    Ticket(User user, int seatNum){
        this.username = username;
        this.seatNum = seatNum;
    }

    public String getUsername() {
        return username;
    }
}

