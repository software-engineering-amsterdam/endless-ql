public class Question {

    private String name;
    private String label;
    private Type type;
    //TODO: private boolean condition;
    //TODO: private <something> expression

    public Question(String name, String label, String type) {
        this.name = name;
        this.label = label;
        this.type = Type.valueOf(type.toUpperCase());
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type.toString();
    }
}
