package com.example.ics108project;

import Authentication.User;

import java.io.Serializable;


public class Ticket implements Serializable {


    private User user;
//    private int seatNum;
    Ticket(User user){
        this.user = user;
//        this.seatNum = seatNum;
    }

    public User getUser() {
        return user;
    }
}

