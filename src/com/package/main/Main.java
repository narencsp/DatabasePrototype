package main;

import iooperations.CreateDatabase;
import iooperations.CreateTable;
import iooperations.DeleteTable;
import iooperations.WriteTable;
import presentation.Create;
import presentation.UserCreation;
import presentation.UserLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        displayInitial();
    }

    private static void displayInitial() throws Exception {
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
            case 4:
                //for deleting a table
               /* DeleteTable deleteTable = new DeleteTable();
                String result = deleteTable.deleteTable("dummy1");*/

                //for creating a table
               /* CreateTable createTable = new CreateTable();
                List<String> columns = new ArrayList<>();
                columns.add("Name");
                columns.add("Age");
                columns.add("Gender");
                columns.add("Address");
                String result = createTable.createTable("T1","D1",columns);*/

                //for creating a database
                /*CreateDatabase createDatabase = new CreateDatabase();
                String result = createDatabase.createDatabase("D1");*/

                //for adding values to the table
          /*      WriteTable writeTable = new WriteTable();
                List<String> columns = new ArrayList<>();
                columns.add("Joe");
                columns.add("30");
                columns.add("Male");
                columns.add("France");
                String result = writeTable.insertIntoTable("T1",columns);*/

                //System.out.println(result);
                break;
        }

    }

}
