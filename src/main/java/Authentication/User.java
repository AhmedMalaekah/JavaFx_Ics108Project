package Authentication;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String passwaord;

    private boolean admin; //  it could be isadmin instead of admin
    private static final ArrayList<User> users = new ArrayList<>();

    private static final ArrayList<String> usernames = new ArrayList<>();
    private static final ArrayList<String> passwords = new ArrayList<>();

    public User(String username, String passwaord, boolean admin) {
        this.username = username;
        this.passwaord = passwaord;
        this.admin = admin;
        User.users.add(this);
        usernames.add(this.getUsername());

    }
    public boolean checkPassword(String passwaord){
        if (this.passwaord.equals(passwaord))
            return true;
        else return false;

    }

    public User() {
        this.username = "none";
        this.passwaord = "nopass";
    }

        public static boolean validUsername(String username) {
        if (usernames.contains(username)) {
            return false;
        }
        else {
            return true;
        }

    }
    private static void fillUsers(){
        for (int i = 0; i < users.size(); i++) {
            usernames.add(users.get(i).getUsername());
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
}
