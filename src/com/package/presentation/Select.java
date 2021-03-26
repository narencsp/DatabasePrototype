package presentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Select implements Query{
    String selectRegex = "SELECT\\s+(.+?)\\s*\\s+FROM\\s+(.*?)\\s*(WHERE\\s+(.*?)\\s*)?;";

    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(selectRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            if(matcher.group(3)!=null) {
                System.out.println(matcher.group(3));
                System.out.println(matcher.group(4));
            }

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
