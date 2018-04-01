package domain.model.value;

public class StringValue implements Value<String> {
    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String setValue(String value) {
        return value = value;
    }
}
