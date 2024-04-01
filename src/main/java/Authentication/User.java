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
        passwords.add(this.getPasswaord());

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
        for (int i = 0; i < users.size(); i++) {
            passwords.add(users.get(i).getPasswaord());
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
    public static ArrayList<String> getPasswords() {
        return passwords;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswaord() {
        return passwaord;
    }

    public void setPasswaord(String passwaord) {
        this.passwaord = passwaord;
    }

    @Override
    public String toString() {
        return username;
}
}
