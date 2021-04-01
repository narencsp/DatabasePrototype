import data.Database;

import java.util.List;
import java.util.Map;

public class DeleteQuery {

    Database database;
    String tableName;
    String condition;

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
        }
        else {

        }
    }
}
