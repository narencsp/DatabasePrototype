package presentation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Insert implements Query {
    public String insertRegex = "INSERT INTO (\\S+).*\\s+\\((.*?)\\).*\\s+VALUES.*\\s+\\((.*?)\\).*\\;";
    String table_name, table_columns, values;
    List<String> table_columns_list = new ArrayList<>();
    List<String> values_list = new ArrayList<>();
    boolean is_match=false;
    boolean is_size_match=true;

    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(insertRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            table_name = matcher.group(1);
            System.out.println(matcher.group(2));
            table_columns = matcher.group(2).trim();
            table_columns_list = Arrays.asList(table_columns.split(","));
            System.out.println(matcher.group(3));
            values = matcher.group(3).trim();
            values_list = Arrays.asList(values.split(","));

            if (table_columns_list.size() != values_list.size()) {
                is_size_match = false;
            }
        }
        is_match = matcher.matches();

        if(is_match && is_size_match){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Map<String, List<String>> getTokens() {
        Map<String, List<String>> tokens = new HashMap<>();
        List<String> table_name_list = new ArrayList<>();
        table_name_list.add(table_name);
        tokens.put("table", table_name_list);
        tokens.put("columns", table_columns_list);
        tokens.put("values", values_list);
        return tokens;
    }

    @Override
    public String logEvent() {
        return null;
    }
}
