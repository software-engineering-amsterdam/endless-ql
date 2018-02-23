package model;

public abstract class Statement {
    public boolean isQuestion() {
        return false;
    }

    public boolean isCondition() {
        return false;
    }
}
