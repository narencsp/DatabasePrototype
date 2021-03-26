package presentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Delete implements Query{
    String deleteRegex = "DELETE FROM\\s+(\\S+)\\s*(WHERE\\s(.*?)\\s*)?;";

    @Override
    public Boolean checkSyntax(String statement) {
        Pattern re = Pattern.compile(deleteRegex);
        Matcher matcher = re.matcher(statement);
        while (matcher.find()) {
                System.out.println(matcher.group(1));
                if(matcher.group(2)!=null) {
                    System.out.println(matcher.group(2));
                    System.out.println(matcher.group(3));
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
