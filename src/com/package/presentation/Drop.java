package presentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Drop implements Query{
    String dropRegex = "DROP TABLE\\s+(\\S+);";

    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(dropRegex);
        Matcher matcher = re.matcher(statement);
        return matcher.matches();
    }

    @Override
    public void getTokens(String statement) {

    }

    @Override
    public String logEvent() {
        return null;
    }
}
