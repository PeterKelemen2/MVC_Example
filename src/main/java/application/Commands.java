package application;

import com.sun.security.jgss.GSSUtil;
import controller.UserController;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commands {
    UserController userController;

    public Commands(UserController userController) {
        this.userController = userController;
    }

    public void interpretCommand(String keyboardInput) {
        int option =  getFirstNumCommand(keyboardInput);

        switch(option){
            case 1:
                userController.printUsers();
                break;
            case 2:
                createUserCommand();
                break;
            case 3:
                deleteUserCommand();
                break;
            case 4:
                updateUserCommand();
                break;
            default:
                System.out.println("Not a valid option!");
        }
    }

    private void createUserCommand(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write data:");
        String input = scanner.nextLine().trim();
        String[] inputArr = input.split("\\s+");

        if(inputArr.length < 3){
            System.out.println("Not enough arguments!");
            return;
        }

        String name = inputArr[0];
        String email = inputArr[1];
        String address = inputArr[2];

        userController.createUser(name, email, address);
        System.out.println("User added successfully!");
        userController.printUsers();
    }

    private void deleteUserCommand(){
        Scanner scanner = new Scanner(System.in);
        userController.printUsers();
        System.out.println("Write ID to delete:");
        String input = scanner.nextLine().trim();

        int id = getFirstNumCommand(input);
        boolean result;
        result = userController.deleteUser(id);
        if(result){
            System.out.println("User deleted successfully!");
            userController.printUsers();
        }
        else{
            System.out.println("Deletion failed!");
        }
    }

    private void updateUserCommand(){
        Scanner scanner = new Scanner(System.in);
        userController.printUsers();
        System.out.println("Write ID to update:");
        String input = scanner.nextLine().trim();

        int id = getFirstNumCommand(input);
        userController.printUser(id);

        input = scanner.nextLine().trim();
        String[] inputArr = input.split("\\s+");
        if(inputArr.length < 3){
            System.out.println("Not enough arguments!");
            return;
        }else {
            int l = 3;
            for(int i = 0; i <= 2; i++){
                if(inputArr[i].equals("_")) l--;
            }
            if(l == 0) {
                System.out.println("No need for updating");
                return;
            }
        }

        String name = inputArr[0].equals("_") ? null : inputArr[0];
//        String name;
//        if(inputArr[0].equals("_")){
//            name = null;
//        }
//        else{
//            name = inputArr[0];
//        }

        String email = inputArr[1].equals("_") ? null : inputArr[1];
        String address = inputArr[2].equals("_") ? null : inputArr[2];

        userController.updateUser(id, name, email, address);

        userController.printUsers();
    }

    private int getFirstNumCommand(String input){
        int result = 0;
        boolean foundNumber = false;

        for(char c : input.toCharArray()){
            if(Character.isDigit(c)){
                result = result * 10 + (c - '0');
                foundNumber = true;
            } else if(foundNumber){
                break;
            }
        }

        return result;
    }
}
