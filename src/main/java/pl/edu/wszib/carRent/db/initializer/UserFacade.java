package pl.edu.wszib.carRent.db.initializer;

import pl.edu.wszib.carRent.model.User;

import java.util.Map;

public class UserFacade {
    private UserSubsystem userSubsystem;

    public UserFacade() {
        this.userSubsystem = new UserSubsystem();
    }

    public void registerUser(String username, String password) {
        userSubsystem.createUser(username, password);
    }

    public void unregisterUser(String username) {
        userSubsystem.deleteUser(username);
    }

    public boolean loginUser(String username, String password) {
        return userSubsystem.loginUser(username, password);
    }

    public Map<String, User> getAllUsers() {
        return userSubsystem.getAllUsers();
    }
}


