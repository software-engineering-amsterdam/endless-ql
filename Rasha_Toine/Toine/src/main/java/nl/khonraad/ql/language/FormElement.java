package nl.khonraad.ql.language;

import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;

public class FormElement implements Question {

    public enum BehaviouralType {
        ANSWERABLE, COMPUTED;
    }

    private BehaviouralType behaviouralType;
    private Identifier      identifier;
    private Label           label;
    private Value           value;

    public FormElement( BehaviouralType behaviouralType, Identifier identifier, Label label, Value value ) {
        this.behaviouralType = behaviouralType;
        this.identifier = identifier;
        this.label = label;
        this.value = value;
    }

    @Override public BehaviouralType getBehaviouralType() {
        return behaviouralType;
    }

    @Override public Identifier identifier() {
        return identifier;
    }

    @Override public String label() {
        return label.string();
    }

    @Override public Value value() {
        return value;
    }

    @Override public Type type() {
        return value.type();
    }

    @Override public String string() {
        return value.string();
    }

    @Override public void answer( Value value ) {
        if ( this.value.type() != value.type() ) {
            throw new RuntimeException( "TYPE_ERROR" );
        }
        this.value = value;
    }
}
