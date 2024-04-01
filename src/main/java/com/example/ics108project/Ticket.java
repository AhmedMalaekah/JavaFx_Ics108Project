package com.example.ics108project;

import Authentication.User;

public class Ticket{
    private User user;
    private int setNum;
    Ticket(User user,int setNum){
        this.user = user;
        this.setNum = setNum;
    }
    Ticket(){}
}

