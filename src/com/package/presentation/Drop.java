package presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Drop implements Query{
    String dropRegex = "DROP TABLE\\s+(\\S+);";
    String table_name;


    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(dropRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            table_name = matcher.group(1);
        }
        return matcher.matches();
    }

    @Override
    public Map<String, List<String>> getTokens() {
        Map<String, List<String>> tokens = new HashMap<>();
        List<String> table_name_list = new ArrayList<>();
        table_name_list.add(table_name);
        tokens.put("table", table_name_list);
        return tokens;    }

    @Override
    public String logEvent() {
        return null;
    }
}
