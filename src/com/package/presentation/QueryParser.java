package presentation;

import java.util.List;
import java.util.Map;

public class QueryParser{

    Query query = null;
    String[] splitedForKeyword;
    String input_query;
    public Types_of_query type;
    public String dump_text = "";

    public enum Types_of_query{
        SELECT,
        UPDATE,
        DELETE,
        DROP,
        INSERT,
        CREATE,
        ERD,
        DUMP,
        CREATEDB,
        TRANSACTION
    }
    public boolean getQueryDetails(String input_query){
        this.input_query = input_query;
        if(input_query.equals("ERD")){
            type = Types_of_query.ERD;
            query = null;
            return true;
        }else if(input_query.contains("DUMP")){
            type = Types_of_query.DUMP;
            dump_text = input_query;
            query = null;
            return true;
        }else if(input_query.contains("TRANSACTION")){
            type = Types_of_query.TRANSACTION;
            dump_text = input_query;
            query = null;
            return true;
        }
        if(queryHasType(input_query)){
            type = getStatementKeyword(input_query);
            return check_syntex(type);
        }else{
            System.out.println("Please enter correct query!");
            return false;
        }
    }

    private boolean check_syntex(Types_of_query type) {
        switch (type) {
            case SELECT: query = new Select();
            case INSERT: query = new Insert();
            case UPDATE: query = new Update();
            case DELETE: query = new Delete();
            case DROP: query = new Drop();
            case CREATE: query = new Create();
            case CREATEDB: query = new CreateDB();
        }
        if(query.checkSyntax(input_query)){
            System.out.println("Syntex is correct!");
            return true;
        }else{
            System.out.println("Syntex is not correct!");
            return false;
        }
    }

    public Map<String,List<String>> get_tokens(){
        return query.getTokens();
    }

    private boolean queryHasType(String input_query) {
        int splited_word_counter = 0;
        splitedForKeyword = input_query.trim().split("\\s+");
        for(Types_of_query types_of_query : Types_of_query.values()){
            if(splitedForKeyword[splited_word_counter].equals(types_of_query.toString())){
                return true;
            }
        }
        return false;
    }

    public Types_of_query getStatementKeyword(String keyword) {
        int splited_word_counter = 0;
        for(Types_of_query types_of_query : Types_of_query.values()){
            if(splitedForKeyword[splited_word_counter].equals(types_of_query.toString())){
                if(types_of_query.toString().equals("CREATE") && splitedForKeyword[1].equals("DATABASE")){
                    return Types_of_query.CREATEDB;
                }
                return types_of_query;
            }
        }
        return null;
    }

    /*public static void main(String[] args){
        QueryParser queryParser = new QueryParser();
        //boolean is_correct = queryParser.getQueryDetails("INSERT INTO remote.db1.student (as,sadsa) VALUES (4,5);");
        //boolean is_correct =queryParser.getQueryDetails("SELECT * FROM remote.db1.student WHERE id = 1;");
        boolean is_correct =queryParser.getQueryDetails("CREATE TABLE remote.db1.student (id INT PRIMARY KEY,name VARCHAR(100),last_name VARCHAR(100) FOREIGN KEY REFERENCES T1(last_name);");
        //boolean is_correct =queryParser.getQueryDetails("DELETE FROM remote.db1.student WHERE i=1;");
        //boolean is_correct =queryParser.getQueryDetails("DROP TABLE remote.db1.student;");
        //boolean is_correct =queryParser.getQueryDetails("UPDATE remote.db1.student SET name = 'kp' WHERE student_id = 1;");
        //boolean is_correct =queryParser.getQueryDetails("CREATE DATABASE db1;");

        if(is_correct){
            Map<String, List<String>> tokens = queryParser.get_tokens();
            //System.out.println(tokens.get("table").get(0));
            System.out.println(tokens.get("database").get(0));
            //System.out.println(tokens.get("location").get(0));
            //System.out.println(tokens.get("column_type").get(0));
            //System.out.println(tokens.get("foreign_key").get(0));
            //System.out.println(tokens.get("foreign_key").get(1));
            //System.out.println(tokens.get("foreign_key").get(2));

            //System.out.println(tokens.get("primary_key").get(0));
            //System.out.println(tokens.get("column_name").get(1));
            //System.out.println(tokens.get("columns").get(0));
            //System.out.println(tokens.get("values").get(0));
        }

    }*/

}
