package data;

import java.util.ArrayList;
import java.util.Collection;

public class Database {
    private String name;
    private final Collection<Table> tables;

    public Database(String name, Collection<Table> tables) {
        this.name = name;
        this.tables = tables;
    }

    public Database(String name) {
        this.name = name;
        this.tables = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return tables.size();
    }
}
