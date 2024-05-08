package com.example.ics108project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class User implements Serializable{
    private String username;
    private String passwaord;

    private boolean admin;
    private static  ArrayList<User> users = new ArrayList<>();

    private static final ArrayList<String> usernames = new ArrayList<>();
    public User(String username, String passwaord, boolean admin) {
        this.username = username;
        this.passwaord = passwaord;
        this.admin = admin;
//        User.users.add(this);
//        usernames.add(this.getUsername());

    }
    public static void createUser(String username, String passwaord, boolean admin) {
        User newUser = new User(username,passwaord,admin);
        User.users.add(newUser);
        usernames.add(newUser.getUsername());

    }


    public User() {
        this.username = "none";
        this.passwaord = "noPass";
    }

    public boolean checkPassword(String passwaord){
        if (this.passwaord.equals(passwaord))
            return true;
        else return false;

    }

    public boolean validUsername(){
        if (User.usernames.contains(username)){
            return false;
        }
        else return true;
    }

    public static boolean validUsername(String username) {
        if (usernames.contains(username)) {
            return false;
        }
        else {
            return true;
        }

    }
    public boolean isAdmin(){
        if (admin){
            return true;
        }
        else {
            return false;
        }
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<String> getUsernames() {
        return usernames;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return username;
    }
    public static void loadUsers() {
        try {
            FileInputStream fileStream  = new FileInputStream(new File("Users.txt"));
            ObjectInputStream os  = new ObjectInputStream(fileStream);
            Object userObject = null;
            while((userObject = os.readObject()) != null) {
                User castObject = (User) userObject;
                users.add(castObject);
                usernames.add(castObject.username);
            }
            os.close();
        }  catch(Exception ex) {
            if(ex instanceof EOFException) {
                out.println("Running Program");
            } else if(ex instanceof FileNotFoundException) {
                out.println("Users File not found!");
                for(int i =0; i < Event.getEvents().size(); i++){
                    Event.getEvents().get(i).clearTickets();
                }
            }
        }
    }

}