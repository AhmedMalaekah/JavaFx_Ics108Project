package com.example.ics108project;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.out;

public class User implements Serializable{
    private String username;
    private String password;

    private boolean admin;
    private static  ArrayList<User> users = new ArrayList<>();

    private static final ArrayList<String> usernames = new ArrayList<>();
    public User(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;

    }
    public static void createUser(String username, String password, boolean admin) {
        User newUser = new User(username,password,admin);
        User.users.add(newUser);
        usernames.add(newUser.getUsername());

    }


    public boolean checkPassword(String passwaord){
        if (this.password.equals(passwaord))
            return true;
        else return false;

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
        return admin;
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