package repository;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    public List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(int id, String name, String email, String address) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) return;

        if (name != null && !name.equals(user.get().getName())) user.get().setName(name);
        if (email != null && !email.equals(user.get().getEmail())) user.get().setEmail(email);
        if (address != null && !email.equals(user.get().getAddress())) user.get().setAddress(address);
    }

    public void deleteUser(int id) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) users.remove(user);
    }
}
