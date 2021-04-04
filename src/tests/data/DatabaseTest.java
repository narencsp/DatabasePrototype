package data;


import org.junit.Before;
import org.junit.jupiter.api.Test;


import java.util.LinkedList;
import java.util.List;

public class DatabaseTest {

    Database testDatabase = null;
    Table testTable = null;

    @Before
    public void setUp() throws Exception {
        testDatabase = new Database(new LinkedList<>());
        testTable = new Table(new LinkedList<>());
        for (int i = 0; i < 10; i++) {
            List<Value> valueList = new LinkedList<>();
            for (int j = 0; j < 3; j++) {
                valueList.add(new Value("row" + j + "value" + j));
            }
            testTable.addRow(new Row(valueList));
        }
        testDatabase.addTable(testTable);
    }

    @Test
    public void getTables() {
        String test = "Test";
        Object o = test;
        System.out.println(o.toString());
        Database d = new Database(new LinkedList<>());

        Value testValue = new Value("Hello World!");
    }

    @Test
    public void createTable() {

    }

    @Test
    public void deleteTable() {
    }
}