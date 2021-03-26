package data;

import java.util.Collection;

public abstract class Row {
    private final Collection<Value> values;

    public Row(Collection<Value> values) {
        this.values = values;
    }

    public Collection<Value> getValueList() {
        return values;
    }
}
