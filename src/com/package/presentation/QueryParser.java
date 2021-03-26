package presentation;

public class QueryParser{

    Query query = null;
    String[] splitedForKeyword;
    String input_query;

    enum Types_of_query{
        SELECT,
        UPDATE,
        DELETE,
        DROP,
        INSERT
    }
    public void getQueryDetails(String input_query){
        this.input_query = input_query;
        Types_of_query type;
        if(queryHasType(input_query)){
            type = getStatementKeyword(input_query);
            System.out.println(type.toString());
            check_syntex(type);
        }else{
            System.out.println("Please enter correct query!");
        }
    }

    private void check_syntex(Types_of_query type) {
        switch (type) {
            case SELECT -> query = new Select();
            case INSERT -> query = new Insert();
            case UPDATE -> query = new Update();
            case DELETE -> query = new Delete();
            case DROP -> query = new Drop();
        }
        if(query.checkSyntax(input_query)){
            System.out.println("Syntex is correct!");
            query.getTokens(input_query);
        }else{
            System.out.println("Syntex is not correct!");
        }
    }

    private boolean queryHasType(String input_query) {
        int splited_word_counter = 0;
        splitedForKeyword = input_query.trim().split("\\s+");
        System.out.println(splitedForKeyword[0]);
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
                return types_of_query;
            }
        }
        return null;
    }

    public static void main(String[] args){
        QueryParser queryParser = new QueryParser();
        //queryParser.getQueryDetails("INSERT INTO student (as,sadsa) VALUES (4,5);");
        //queryParser.getQueryDetails("SELECT * FROM student WHERE id = 1;");
        //queryParser.getQueryDetails("DELETE FROM student WHERE i=1;");
        //queryParser.getQueryDetails("DROP TABLE student;");
        queryParser.getQueryDetails("UPDATE student SET name = 'kp' WHERE student_id = 1;");

    }


}
