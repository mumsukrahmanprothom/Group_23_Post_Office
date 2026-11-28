package com.example.group_23_post_office;

import java.util.ArrayList;

public class UserService {

    private ArrayList<User> users = new ArrayList<>();

    public UserService() {

        users.add(new CSO("cso", "1234", "Customer Service Officer"));

        users.add(new MRO("mro", "1234", "Mail Registration Officer"));
    }

    public User login(String username, String password) {

        for (User user : users) {

            if (user.getUsername().equals(username)
                    && user.checkPassword(password)
                    && user.isActive()) {

                return user;
            }
        }

        return null;
    }
}