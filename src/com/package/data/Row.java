package data;

import java.util.Collection;

public class Row {
    private final Collection<Value> values;

    public Row(Collection<Value> values) {
        this.values = values;
    }

    public Collection<Value> getValues() {
        return values;
    }
}
