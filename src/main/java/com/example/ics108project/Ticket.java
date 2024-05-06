package com.example.ics108project;

import com.example.ics108project.User;

import java.io.Serializable;


public class Ticket implements Serializable {


    private User user;
    private int seatNum;
    Ticket(User user, int seatNum){
        this.user = user;
        this.seatNum = seatNum;
    }

    public User getUser() {
        return user;
    }
}

