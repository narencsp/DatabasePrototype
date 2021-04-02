package presentation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create implements Query{
    String createRegex = "CREATE TABLE (\\S+)\\s*\\((.*?)\\)\\;";
    String table_name, columns;

    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(createRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            table_name = matcher.group(1);
            System.out.println(matcher.group(2));
            columns = matcher.group(2);
        }
        return matcher.matches();
    }

    @Override
    public Map<String, List<String>> getTokens() {
        Map<String, List<String>> tokens = new HashMap<>();
        List<String> table_name_list = new ArrayList<>();
        table_name_list.add(table_name);
        tokens.put("table", table_name_list);

        List<String> columns_seperated = Arrays.asList(columns.split(","));
        List<String> column_name_list = new ArrayList<>();
        List<String> column_type_list = new ArrayList<>();

        int i=0;
        for(String columns : columns_seperated){
            List<String> columns_parts = Arrays.asList(columns.trim().split(" "));
            column_name_list.add(columns_parts.get(0).trim());
            column_type_list.add(columns_parts.get(1).trim());
            if(columns_parts.size()>2){
                List<String> primary_key_list = new ArrayList<>();
                primary_key_list.add(columns_parts.get(0).trim());
                tokens.put("primary_key", primary_key_list);
            }
        }
        tokens.put("column_name", column_name_list);
        tokens.put("column_type", column_type_list);
        return tokens;    }

    @Override
    public String logEvent() {
        return null;
    }
}
