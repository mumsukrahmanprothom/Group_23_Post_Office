package com.postoffice.service;

import com.postoffice.model.AssistantPostMaster;
import com.postoffice.model.PostMaster;
import com.postoffice.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final UserService INSTANCE = new UserService();

    private final List<User> users = new ArrayList<>();

    private UserService() {
        register(new PostMaster("postmaster", "admin123", "Main Post Master"));
        register(new AssistantPostMaster("assistant", "assist123", "Assistant Post Master"));
    }

    public static UserService getInstance() { return INSTANCE; }

    private User find(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    public User authenticate(String username, String password, String role) {
        User user = find(username);
        if (user != null && user.isActive() && user.checkPassword(password) && user.getRole().equals(role)) {
            return user;
        }
        return null;
    }

    public boolean register(User user) {
        if (find(user.getUsername()) != null) return false;
        users.add(user);
        return true;
    }

    public List<User> getAllUsers() { return new ArrayList<>(users); }

    public boolean remove(String username) {
        User u = find(username);
        if (u == null) return false;
        users.remove(u);
        return true;
    }
}
