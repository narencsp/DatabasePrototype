package presentation;

import java.util.List;
import java.util.Map;

public interface Query {
    public Boolean checkSyntax(String statement);
    Map<String, List<String>> getTokens();
    public String logEvent();

}
