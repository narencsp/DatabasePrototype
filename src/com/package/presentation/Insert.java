package presentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Insert implements Query {
    public String insertRegex = "INSERT INTO (\\S+).*\\s+\\((.*?)\\).*\\s+VALUES.*\\s+\\((.*?)\\).*\\;";

    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(insertRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(2));
                System.out.println(matcher.group(3));

        }
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
