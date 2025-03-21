package application;

import controller.UserController;

public class Commands {
    UserController userController;

    public Commands(UserController userController) {
        this.userController = userController;
    }

    public void interpretCommand(String keyboardInput) {
        int option =  getFirstNumCommand(keyboardInput);

        // "aaaa123cccc4" - 123

        System.out.println("Raw input was: " + keyboardInput);
        System.out.println("Input was: " + option);
    }

    private int getFirstNumCommand(String input){
        int result = 0;

        char[] inputArray = input.toCharArray();
        for (int i = 0; i < inputArray.length; i++){
            if(inputArray[i] >= 48 && inputArray[i] <= 57){
                System.out.println((int)inputArray[i]);
                result = result * 10 + ((int)inputArray[i] - (int)'0');
            }
        }

        return result;
    }
}
