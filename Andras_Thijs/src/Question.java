public class Question {

    private String name;
    private String label;
    private String type;
    //TODO: private boolean condition;
    //TODO: private <something> expression

    public Question(String name, String label, String type) {
        this.name = name;
        this.label = label;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getString() {
        return type;
    }
}
