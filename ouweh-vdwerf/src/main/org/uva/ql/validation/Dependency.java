package org.uva.ql.validation;

class Dependency {

    private String from;
    private String to;

    Dependency(String left, String right){
        this.from = left;
        this.to = right;
    }

    boolean isReflexive() {
        return this.from.equals(this.to);
    }

    boolean isTransitive(Dependency input) {
        return this.to.equals(input.getFrom());
    }

    @Override
    public String toString() {
        return from + " -> " + to;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }
}
