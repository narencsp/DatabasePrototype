package data;

import java.util.Collection;

public abstract class Database {
    private final Collection<Table> tables;

    public Database(Collection<Table> tables) {
        this.tables = tables;
    }

    public Collection<Table> getTables() {
        return tables;
    }

    abstract void createTable();
    abstract void deleteTable();
}
