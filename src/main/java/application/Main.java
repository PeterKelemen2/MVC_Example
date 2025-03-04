package application;

import controller.UserController;
import model.User;
import repository.UserRepository;

public class Main {
    public static UserController userController = new UserController();

    public static void main(String args[]) {
        userController.createUser("John Doe", "john.doe@email.com", "valahol");
        userController.createUser("John Doe", "john.doe2@email.com", "valahol");

        userController.printUsers();

        userController.delete(1);

        userController.printUsers();
    }
}