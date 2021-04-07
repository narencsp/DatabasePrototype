package main;

import business.*;
import com.google.common.collect.Table;
import persistence.*;
import presentation.Create;
import presentation.QueryParser;

import presentation.UserCreation;
import presentation.UserLogin;

import java.util.*;

public class Main {
    static Scanner in;

    public static void main(String[] args) throws Exception {
        displayInitial();
    }

    private static void displayInitial() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("1. User Creation\n2. User Login");
        int choice = in.nextInt();
        UserLogin ul = new UserLogin();
        UserCreation uc = new UserCreation();
        switch (choice) {
            case 1:
                uc.createUser();
                displayInitial();
                break;
            case 2:
                ul.getUsername();
                if (ul.ulf.equals(UserLogin.UserLoginFlag.USER_VALID)) {
                    System.out.println("Session started");
                    startQuerySession();
                } else {
                    displayInitial();
                }
                break;
            case 3:
                System.out.println("Invalid Choice, enter right choice!");
                displayInitial();
                break;
            case 4:

                TableLock tableLock = new TableLock();
                tableLock.isTableLocked("T1");
                //System.out.println(result);
                break;
        }

    }

    private static void startQuerySession() throws Exception {
        String query = "";
        System.out.println("Enter Query:");
        Scanner in = new Scanner(System.in);
        query = in.nextLine();
        QueryParser queryParser = new QueryParser();
        if (queryParser.getQueryDetails(query)) {
            Map<String, List<String>> tokens = new HashMap<>();
            System.out.println(queryParser.type.toString());
            if (!queryParser.type.toString().equals("ERD") && !queryParser.type.toString().equals("DUMP")) {
                tokens = queryParser.get_tokens();
            }
            switch (queryParser.type) {
                case CREATE:
                    CreateTable createTable = new CreateTable();

                    //String status = createTable.createTable(tokens.get("table").get(0),tokens.get("database").get(0),tokens.get("column_name"),tokens.get("column_type"));
                    //   System.out.println(status);
                    if (tokens.containsKey("foreign_key")) {
                        String status = createTable.createTable(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0), tokens.get("column_name"), tokens.get("column_type"), tokens.get("foreign_key"));
                        System.out.println(status);
                    } else {
                        List<String> blank_list = new ArrayList<>();
                        String status = createTable.createTable(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0), tokens.get("column_name"), tokens.get("column_type"), blank_list);
                        System.out.println(status);
                    }
                    break;
                case DROP:
                    DeleteTable deleteTable = new DeleteTable();
                    deleteTable.deleteTable(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0));

                    break;
                case DELETE:
                    DeleteOperation deleteOperation = new DeleteOperation();
                    if (tokens.containsKey("values")) {
                        deleteOperation.deleteOperation(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0), tokens.get("values"));
                    } else {
                        List<String> blank_list = new ArrayList<>();
                        deleteOperation.deleteOperation(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0), blank_list);
                    }
                    break;
                case INSERT:
                    InsertOperation writeTable = new InsertOperation();
                    writeTable.checkInsertValues(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0), tokens.get("values"));
                    break;
                case SELECT:
                    SelectOperation selectOperation = new SelectOperation();
                    if (tokens.containsKey("condition")) {
                        selectOperation.selectQueryOperation(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0), tokens.get("columns"), tokens.get("condition"));
                    } else {
                        List<String> blank_list = new ArrayList<>();
                        selectOperation.selectQueryOperation(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0), tokens.get("columns"), blank_list);
                    }
                    break;
                case UPDATE:
                    UpdateOperation updateOperation = new UpdateOperation();
                    if (tokens.containsKey("condition")) {
                        updateOperation.updateOperation(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0), tokens.get("columns"), tokens.get("values"), tokens.get("condition"));
                    } else {
                        List<String> blank_list = new ArrayList<>();
                        updateOperation.updateOperation(tokens.get("table").get(0), tokens.get("database").get(0), tokens.get("location").get(0), tokens.get("columns"), tokens.get("values"), blank_list);
                    }
                    break;
                case ERD:
                    GenerateERD generateERD = new GenerateERD();
                    generateERD.displayERD();
                    break;
                case DUMP:
                    GenerateDump generateDump = new GenerateDump();
                    generateDump.dumpGenerator();
                    break;
                default:
                    System.out.println("Something went wrong!");
                    break;

            }
            startQuerySession();
        } else {
            startQuerySession();
        }

    }

}
