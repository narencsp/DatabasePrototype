package data;

public class Value {
    private final Object object;

    public Value(Object object) {
        this.object = object;
    }

    private Object getValue() {
        return object;
    }
}
