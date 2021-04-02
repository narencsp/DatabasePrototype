package data;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;

public class Table {
    private String name;
    private Collection<String> columnNames;
    private Collection<Row> rows;

    public Table(String name, Collection<Row> rows) {
        this.name = name;
        this.rows = rows;
    }

    public Table(String name) {
        this.name = name;
        this.rows = new ArrayList<>();
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

    public void removeRow(Value value) {
        rows.removeIf(r -> r.getValues().contains(value));
    }

    public void removeRow(Value value, int index) {
        rows.removeIf(r -> r.getValues().toArray()[index].equals(value));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRows(Collection<Row> rows) {
        this.rows = rows;
    }

    public void setColumnNames(Collection<String> columnNames) {
        this.columnNames = columnNames;
    }

    public int getColumnNumber(String columnName) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(name, table.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
