package data;

public class StringValue extends Value {
    public StringValue(String stringValue) {
        this.object = stringValue;
    }

    public String getStringValue() {
        return (String) object;
    }
}
