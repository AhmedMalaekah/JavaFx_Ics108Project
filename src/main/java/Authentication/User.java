package Authentication;

import java.util.ArrayList;

public class User {
    private String username;
    private String passwaord;
    private boolean admin; //  it could be isadmin instead of admin
    private static ArrayList<String> usernames = new ArrayList<>();

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

}
