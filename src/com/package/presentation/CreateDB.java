package presentation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateDB implements Query {
    String createRegex = "CREATE DATABASE (\\S+)\\s*\\;";
    String db;


    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(createRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
            db = matcher.group(1);
        }
        return matcher.matches();
    }

    @Override
    public Map<String, List<String>> getTokens() {
        Map<String, List<String>> tokens = new HashMap<>();

        List<String> db_list = new ArrayList<>();
        db_list.add(db);
        tokens.put("database", db_list);

        return tokens;
    }

    @Override
    public String logEvent() {
        return null;
    }
}
