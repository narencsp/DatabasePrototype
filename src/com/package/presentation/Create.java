package presentation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create implements Query{
    String createRegex = "CREATE TABLE (\\S+)\\s*\\((.*?)\\)\\;";
    String table_name, columns, only_table;


    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(createRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
            table_name = matcher.group(1);
            columns = matcher.group(2);
        }
        return matcher.matches();
    }

    @Override
    public Map<String, List<String>> getTokens() {
        Map<String, List<String>> tokens = new HashMap<>();
        List<String> table_name_list = Arrays.asList(table_name.split("\\."));
        if(table_name_list.size()>1){
            List<String> table_name_temp_list = new ArrayList<>();
            table_name_temp_list.add(table_name_list.get(2));
            only_table = table_name_list.get(2);
            tokens.put("table", table_name_temp_list);
            List<String> db_temp_list = new ArrayList<>();
            db_temp_list.add(table_name_list.get(1));
            tokens.put("database", db_temp_list);
            List<String> location_list = new ArrayList<>();
            location_list.add(table_name_list.get(0));
            tokens.put("location", location_list);

        }else{
            tokens.put("table", table_name_list);
        }

        List<String> columns_seperated = Arrays.asList(columns.split(","));
        List<String> column_name_list = new ArrayList<>();
        List<String> column_type_list = new ArrayList<>();

        int i=0;
        for(String columns : columns_seperated){
            List<String> columns_parts = Arrays.asList(columns.trim().split(" "));

            if(columns_parts.size()>2){
                if(columns.contains("FOREIGN")){
                    List<String> foreign_key = new ArrayList<>();
                    foreign_key.add(only_table);
                    String foreign_table = columns_parts.get(5);
                    List<String> foreign_table_list = Arrays.asList(foreign_table.split("\\("));
                    foreign_key.add(foreign_table_list.get(0));
                    foreign_key.add(columns_parts.get(0));
                    tokens.put("foreign_key", foreign_key);

                    column_name_list.add(columns_parts.get(0).trim());
                    column_type_list.add(columns_parts.get(1).trim());
                }else {
                    column_name_list.add(columns_parts.get(0).trim());
                    column_type_list.add(columns_parts.get(1).trim() + "PK");
                }
            }else{
                column_name_list.add(columns_parts.get(0).trim());
                column_type_list.add(columns_parts.get(1).trim());
            }
        }
        tokens.put("column_name", column_name_list);
        tokens.put("column_type", column_type_list);
        return tokens;
    }

    @Override
    public String logEvent() {
        return null;
    }
}
