package presentation;

public interface Query {
    public Boolean checkSyntax(String statement);
    void getTokens(String statement);
    public String logEvent();

}
