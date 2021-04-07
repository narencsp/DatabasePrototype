package presentation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Delete implements Query{
    String deleteRegex = "DELETE FROM\\s+(\\S+)\\s*(WHERE\\s(.*?)\\s*)?;";
    List<String> values_list = new ArrayList<>();
    String table_name, values = "";

    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(deleteRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
                System.out.println(matcher.group(1));
                table_name = matcher.group(1);
                if(matcher.group(2)!=null) {
                    System.out.println(matcher.group(2));
                    System.out.println(matcher.group(3));
                    values = matcher.group(3);
                }

        }
        return matcher.matches();
    }

    @Override
    public Map<String, List<String>> getTokens() {
        Map<String, List<String>> tokens = new HashMap<>();

        List<String> table_name_list = Arrays.asList(table_name.split("\\."));
        //table_name_list.add(table_name);
        List<String> table_name_temp_list = new ArrayList<>();
        table_name_temp_list.add(table_name_list.get(2));
        tokens.put("table", table_name_temp_list);
        List<String> db_temp_list = new ArrayList<>();
        db_temp_list.add(table_name_list.get(1));
        tokens.put("database", db_temp_list);
        List<String> location_list = new ArrayList<>();
        location_list.add(table_name_list.get(0));
        tokens.put("location", location_list);

        if(!values.equals("")) {
            values_list.add(values);
            tokens.put("values", values_list);
        }
        return tokens;
    }

    @Override
    public String logEvent() {
        return null;
    }
}
