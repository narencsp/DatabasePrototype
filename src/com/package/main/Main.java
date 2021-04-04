package main;

import presentation.QueryParser;

import presentation.UserCreation;
import presentation.UserLogin;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner in;
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
                if(ul.ulf.equals(UserLogin.UserLoginFlag.USER_VALID)){
                    System.out.println("Session started");
                    startQuerySession();
                }else {
                    displayInitial();
                }
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

    private static  void startQuerySession() {
        String query = "";
        System.out.println("Enter Query:");
        query = in.nextLine();
        QueryParser queryParser = new QueryParser();
        if(queryParser.getQueryDetails(query)){
            Map<String, List<String>> tokens = queryParser.get_tokens();
            switch (queryParser.type){
                case CREATE:

                    break;
                case DROP:

                    break;
                case DELETE:

                    break;
                case INSERT:

                    break;
                case SELECT:

                    break;
                case UPDATE:

                    break;
                default:
                    System.out.println("Something went wrong!");
                    break;

            }
            startQuerySession();
        }else{
            startQuerySession();
        }

    }

}
