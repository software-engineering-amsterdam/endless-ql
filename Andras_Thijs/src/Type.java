import java.util.HashMap;
import java.util.Map;

public enum Type {
    BOOL ("boolean"),
    STRING ("string"),
    INT ("integer"),
    DATA ("date"),
    DECIMAL ("decimal"),
    MONEY ("money");

    private String value;

    Type (String value){
        this.value = value;
    }

    static Map<String, Type> map = new HashMap<String, Type>();

    static {
        for (Type type : Type.values()) {
            map.put(type.value, type);
        }
    }

    public static Type getByCode(String code) {
        return map.get(code);
    }
}
