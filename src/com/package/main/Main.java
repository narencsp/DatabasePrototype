package main;

import business.*;
import persistence.*;
import presentation.Create;
import presentation.QueryParser;

import presentation.UserCreation;
import presentation.UserLogin;

import java.util.*;

public class Main {
    static Scanner in;
    String[] query_list;
    private int current_query;
    private boolean is_transaction = false;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.displayInitial();
    }

    public void displayInitial() throws Exception {
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


                //System.out.println(result);
                break;
        }

    }

    public void startQuerySession() throws Exception {
        String query = "";
        System.out.println("Enter Query:");
        Scanner in = new Scanner(System.in);
        query = in.nextLine();
        call_query(query);
    }

    public void call_query(String query) throws Exception {
        System.out.println(query);
        QueryParser queryParser = new QueryParser();
        if (queryParser.getQueryDetails(query)) {
            Map<String, List<String>> tokens = new HashMap<>();
            if (!queryParser.type.toString().equals("ERD") && !queryParser.type.toString().equals("DUMP") && !queryParser.type.toString().equals("TRANSACTION")) {
                tokens = queryParser.get_tokens();
            }
            switch (queryParser.type) {
                case CREATEDB:
                    CreateDatabase database = new CreateDatabase();
                    database.createDatabase(tokens.get("database").get(0));
                    break;
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
                    List<String> dump = Arrays.asList(queryParser.dump_text.split(" "));
                    GenerateDump generateDump = new GenerateDump();
                    generateDump.dumpGenerator(dump.get(1));
                    break;
                case TRANSACTION:
                    only_transactions();
                    break;
                default:
                    System.out.println("Something went wrong!");
                    break;

            }
            if(is_transaction && current_query==query_list.length-1) {
                is_transaction = false;
                startQuerySession();
            }else if(!is_transaction){
                startQuerySession();
            }
        } else {
            startQuerySession();
        }
    }

    public void only_transactions() throws Exception {
        System.out.println("Enter the number of query you want to execute: ");
        is_transaction = true;
        Scanner in = new Scanner(System.in);
        int total_query = in.nextInt();
        query_list = new String[total_query];
        in.nextLine();
        for(int i=0;i<total_query;i++){
            String query_temp = in.nextLine();
            query_list[i] = query_temp;
        }
        for(int out = 0; out < total_query;out++){
            current_query = out;
            call_query(query_list[out]);
        }
        /*int i=1;
        while(i<=total_query){
            call_query(query_list[i-1]);
            i++;
            System.out.println("here");
        }*/

    }

}
