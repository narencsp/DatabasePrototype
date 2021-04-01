package data;

import java.util.Collection;

public class Table {
    private Collection<Row> rows;

    public Table(Collection<Row> rows) {
        this.rows = rows;
    }

    public Collection<Row> getRows() {
        return rows;
    }
    public void addRow(Row row) {
        rows.add(row);
    }
    public void removeRow(Row row) {
        rows.remove(row);
    }
}
