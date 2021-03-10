package main;

import presentation.UserCreation;
import presentation.UserLogin;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        displayInitial();
    }

    private static void displayInitial() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("1. User Creation\n2. User Login");
        int choice = in.nextInt();
        UserLogin ul= new UserLogin();
        UserCreation uc = new UserCreation();
        switch(choice){
            case 1:
                uc.createUser();
                displayInitial();
                break;
            case 2:
                ul.getUsername();
                displayInitial();
                break;
            case 3:
                System.out.println("Invalid Choice, enter right choice!");
                displayInitial();
                break;
        }

    }

}
