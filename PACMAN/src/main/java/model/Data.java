package model;

import Movables.Characters;
import Movables.Ghost;
import javafx.stage.Stage;
import model.Map.Block;

import java.util.ArrayList;

public class Data {
    public static final double H = 900, V = 600;
    public static Stage stage;
    public static int length = 5;
    public static int numberOfDots = 0;
    public static double lengthImage = Data.V/Data.length;
    public static double speed = 2.5;
    public static Block[][] grid;



    ////// data of usersss
    private static final ArrayList<User> users = new ArrayList<User>();
    private static String loggedUser;

    public static User getUserByUsername(String username) {
        for(User user : users) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }
    public static void addUser(User user) {
        users.add(user);
    }

    public static User getLoggedUser() {
        return Data.getUserByUsername(loggedUser);
    }
    public static void setLoggedUser(String loggedUser) {
        Data.loggedUser = loggedUser;
    }
}
