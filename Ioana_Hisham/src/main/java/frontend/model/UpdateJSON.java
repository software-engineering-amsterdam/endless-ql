package frontend.model;

/**
 * Created by Toshiba on 01/04/2018.
 */
public class UpdateJSON {

    private String identifier;
    private String value;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UpdateJSON{" +
                "identifier='" + identifier + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
