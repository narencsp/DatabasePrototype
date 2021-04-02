import data.Database;
import data.Table;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DeleteQuery {

    Database database = new Database("Test");
    String tableName;
    String condition;

    public DeleteQuery(Map<String, List<String>> tokens) {
        this.tableName = tokens.get("table").get(0);
        this.condition = tokens.get("values").get(0);
    }

    public DeleteQuery(String tableName) {
        this.tableName = tableName;
        this.condition = null;
    }

    public DeleteQuery(String tableName, String condition) {
        this.tableName = tableName;
        this.condition = condition;
    }

    public void execute() {
        // Load database object
        // Retrieve column
        // Process condition
        // If no condition, delete all records
        // If condition, delete records that match the condition

        if (condition == null) {
            // Delete all records from table
            Collection<Table> tables = database.getTables();
            tables.removeIf(t -> t.getName().equals(tableName));
            database.setTables(tables);
        }
        else {

        }
    }
}
