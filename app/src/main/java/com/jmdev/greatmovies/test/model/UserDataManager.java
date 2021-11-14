package com.jmdev.greatmovies.test.model;

import java.util.ArrayList;

public class UserDataManager {

    private ArrayList<User> users = new ArrayList<>();

    public UserDataManager() {
        fakeUserData();
    }

    public boolean checkUser(String userName, String password) {
        boolean isUser = false;

        for (User u:users) {
            if (u.getUserName().equals(userName) && u.getUserPassword().equals(password)) {
                isUser = true;
            }
        }
        return isUser;
    }

    private void fakeUserData() {
        users.add(new User("11111", "jhon11", "01111"));
        users.add(new User("11112", "david12", "01112"));
        users.add(new User("11113", "matthew13", "01113"));
        users.add(new User("11114", "sidney14", "01114"));
        users.add(new User("11115", "juacosoft", "123456"));
    }
}