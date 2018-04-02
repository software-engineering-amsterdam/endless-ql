package QL.classes;

import QL.classes.values.Value;

public class Question<T> {
    private String id;
    private String questionText;
    private Value<T> value;
    private boolean isFixed;
    private boolean isVisible;

    public Question(String id, String questionText, Value<T> value, boolean isFixed) {
        this.id = id;
        this.isFixed = isFixed;
        this.value = value;
        this.questionText = questionText;
    }

    public Question(String id, String questionText, Value<T> value, boolean isFixed, boolean isVisible) {
        this.id = id;
        this.isFixed = isFixed;
        this.value = value;
        this.questionText = questionText;
        this.isVisible = isVisible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getText() {
        return questionText;
    }

    public Value<T> getValue() {
        return value;
    }

    public Question setValue(Value<T> value) {
        this.value = value;
        return this;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isFixed() {
        return this.isFixed;
    }

    @Override
    public String toString() {
        return "Txt: " + questionText + " Val: " + value.getValue() + " Typ: " + value.getType() + " Vis: " + isVisible + " Fix: " + isFixed;
    }
}


