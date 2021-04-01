package data;

import java.util.Collection;

public class Database {
    private final Collection<Table> tables;

    public Database(Collection<Table> tables) {
        this.tables = tables;
    }
    public Collection<Table> getTables() {
        return tables;
    }
    public boolean deleteTable(Table table) {
        return tables.remove(table);
    }
    public boolean addTable(Table table) {
        return tables.add(table);
    }
}
