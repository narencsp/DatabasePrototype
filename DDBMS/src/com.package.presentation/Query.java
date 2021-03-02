public interface Query {
    public String getStatementKeyword(String keyword);
    public Boolean checkSyntax(String statement);
    public String logEvent();

}
