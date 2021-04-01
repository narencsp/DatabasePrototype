package data;

import java.util.Collection;

public abstract class Table {
    private Collection<Column> rows;
    public Collection<Column> getRows() {
        return rows;
    }
    abstract void addRow();
    abstract void removeRow();
}
