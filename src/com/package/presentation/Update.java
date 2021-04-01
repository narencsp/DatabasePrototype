package presentation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update implements Query{
    String updateRegex = "UPDATE\\s+(\\S+)\\s*SET\\s+(.*?)\\s*WHERE\\s+(.*?);";
    String table_name, value, condition;

    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(updateRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            table_name = matcher.group(1);
            System.out.println(matcher.group(2));
            value = matcher.group(2);
            System.out.println(matcher.group(3));
            condition = matcher.group(3);
        }
        return matcher.matches();
    }

    @Override
    public Map<String, List<String>> getTokens() {
        Map<String, List<String>> tokens = new HashMap<>();
        List<String> table_name_list = new ArrayList<>();
        table_name_list.add(table_name);
        tokens.put("table", table_name_list);

        List<String> column_list = new ArrayList<>();
        List<String> value_list = new ArrayList<>();

        List<String> temp_values = Arrays.asList(value.split(","));
        int i=0;
        for(String single_value : temp_values){
            List<String> seperated_by_equals = Arrays.asList(single_value.split("="));
            column_list.add(seperated_by_equals.get(0).trim());
            value_list.add(seperated_by_equals.get(1).trim());
            i++;
        }

        tokens.put("columns", column_list);
        tokens.put("values", value_list);

        List<String> condition_list = new ArrayList<>();
        tokens.put("condition", condition_list);
        return tokens;
    }

    @Override
    public String logEvent() {
        return null;
    }
}
