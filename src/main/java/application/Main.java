package application;

import controller.UserController;
import model.User;
import repository.UserRepository;

import java.util.Scanner;

public class Main {
    public static UserController userController = new UserController();
    public static Commands commands = new Commands(userController);

    public static void main(String args[]) {
        userController.createUser("John Doe", "john.doe@email.com", "valahol");
        userController.createUser("John Doe", "john.doe2@email.com", "valahol");

        userController.printUsers();
        userController.updateUser(1, "John Doe Edited", null, null);
//        userController.delete(1);

        userController.printUsers();

        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1 - Add user");
            System.out.println("Choose an option:");
            String keyboardInput = scanner.next();

            commands.interpretCommand(keyboardInput);
        }
    }
}