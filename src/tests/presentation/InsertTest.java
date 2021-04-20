package presentation;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class InsertTest {

    @Test
    public void checkCorrectSyntax() {
        String testInsertQuery = "INSERT INTO table_name (column1, column2, column3)\n" +
                "VALUES (value1, value2, value3);";
        Query insertTest = new Insert();
        assertTrue(insertTest.checkSyntax(testInsertQuery));
    }

    @Test
    public void checkIncorrectSyntax() {
        String testInsertQuery = "INSERT table_name INTO (column1, column2, column3)," +
                "(value1, value2, value3);";
        Query insertTest = new Insert();
        assertFalse(insertTest.checkSyntax(testInsertQuery));
    }

    @Test
    public void getTokens() {
        String testInsertQuery = "INSERT INTO this.this.table_name (column1, column2, column3)\n" +
                "VALUES (value1, value2, value3);";
        Query insertTest = new Insert();
        insertTest.checkSyntax(testInsertQuery);
        Map<String, List<String>> expectedTokens = insertTest.getTokens();
        List<String> columns = expectedTokens.get("columns");
        List<String> values = expectedTokens.get("values");
        assertTrue(columns.contains("column1"));
        assertTrue(values.contains("value1"));
    }
}