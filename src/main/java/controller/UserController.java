package controller;

import model.User;
import repository.UserRepository;

import java.util.Optional;

public class UserController {
    private UserRepository userRepository = new UserRepository();

    public void createUser(User user) {
        if (isEmailPresent(user.getEmail())) {
            System.out.println("This email is already taken!");
            return;
        }

        user.setId(getNextId());
        userRepository.addUser(user);
    }

    public void createUser(String name, String email, String address) {
        if (isEmailPresent(email)) {
            System.out.println("This email is already taken!");
            return;
        }

        User user = new User(name, email, address);
        user.setId(getNextId());

        userRepository.addUser(user);
    }

    public void updateUser(int id, String name, String email, String address) {
        userRepository.updateUser(id, name, email, address);
    }

    private int getNextId() {
        if (userRepository.users.isEmpty()) return 0;

        return userRepository.users.getLast().getId() + 1;
    }

    public void printUsers() {
        System.out.println("Users list:");
        userRepository.getAllUsers().forEach(System.out::println);
    }

    private boolean isEmailPresent(String email) {
        for (var u : userRepository.getAllUsers()) {
            if (u.getEmail().equals(email)) return true;
        }

        return false;
    }

    public boolean deleteUser(int id) {
        Optional<User> user = userRepository.getUserById(id);
//        user.ifPresent(value -> userRepository.users.remove(value));

        if (user.isPresent()){
            userRepository.users.remove(user);
            return true;
        }

        return false;
    }

    public void printUser(int id){
        Optional<User> user = userRepository.getUserById(id);
        if (user.isPresent()) System.out.println(user);
    }
}
