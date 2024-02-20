package pl.edu.wszib.carRent.db.initializer;

import pl.edu.wszib.carRent.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserSubsystem {
    private Map<String, User> users;

    public UserSubsystem() {
        this.users = new HashMap<>();
        users.put("user", new User("user", "user123"));
        users.put("admin", new User("admin", "admin123"));
    }

    public void createUser(String username, String password) {
        if (!users.containsKey(username)) {
            User newUser = new User(username, password);
            users.put(username, newUser);
            System.out.println("Tworzenie użytkownika: " + newUser);
        } else {
            System.out.println("Użytkownik " + username + " już istnieje.");
        }
    }

    public void deleteUser(String username) {
        if (users.containsKey(username)) {
            User deletedUser = users.remove(username);
            System.out.println("Usuwanie użytkownika: " + deletedUser);
        } else {
            System.out.println("Nie znaleziono użytkownika  " + username);
        }
    }

    public boolean loginUser(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                return true;
            } else {
                System.out.println("Niepoprawne hasło dla użytkownika: " + username);
            }
        } else {
            System.out.println("Nie znaleziono użytkownika  " + username);
        }
        return false;
    }

    public Map<String, User> getAllUsers() {
        return users;
    }
}
