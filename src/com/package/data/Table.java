package data;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;

public class Table {
    private String name;
    private final Collection<Row> rows;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
