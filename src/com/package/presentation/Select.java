package presentation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Select implements Query {
    String selectRegex = "SELECT\\s+(.+?)\\s*\\s+FROM\\s+(.*?)\\s*(WHERE\\s+(.*?)\\s*)?;";
    String columns, table_name, condition = "";

    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(selectRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            columns = matcher.group(1).trim();
            System.out.println(matcher.group(2));
            table_name = matcher.group(2).trim();
            if (matcher.group(3) != null) {
                System.out.println(matcher.group(3));
                System.out.println(matcher.group(4).trim());
                condition = matcher.group(4);
            }
        }
        return matcher.matches();
    }

    @Override
    public Map<String, List<String>> getTokens() {
        Map<String, List<String>> tokens = new HashMap<>();

        List<String> table_name_list = Arrays.asList(table_name.split("\\."));
        List<String> table_name_temp_list = new ArrayList<>();
        table_name_temp_list.add(table_name_list.get(2));
        tokens.put("table", table_name_temp_list);
        List<String> db_temp_list = new ArrayList<>();
        db_temp_list.add(table_name_list.get(1));
        tokens.put("database", db_temp_list);
        List<String> location_list = new ArrayList<>();
        location_list.add(table_name_list.get(0));
        tokens.put("location", location_list);

        List<String> columns_list = Arrays.asList(columns.split(","));
        tokens.put("columns", columns_list);

        if (!condition.equals("")) {
            List<String> condition_column_list = new ArrayList<>();
            condition_column_list.add(condition);
            tokens.put("condition", condition_column_list);
        }
        return tokens;
    }

    @Override
    public String logEvent() {
        return null;
    }
}
