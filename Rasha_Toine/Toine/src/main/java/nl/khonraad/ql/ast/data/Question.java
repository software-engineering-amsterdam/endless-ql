package nl.khonraad.ql.ast.data;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.algebra.value.Value;

public class Question {

    public enum BehaviouralType {

        ANSWERABLE, COMPUTED;
    }

    private BehaviouralType behaviouralType;
    private Identifier      identifier;
    private Label           label;
    private Value           value;

    public Question( BehaviouralType behaviouralType, Identifier identifier, Label label, Value value ) {

        this.behaviouralType = behaviouralType;
        this.identifier = identifier;
        this.label = label;
        this.value = value;
    }

    public BehaviouralType getBehaviouralType() {
        return behaviouralType;
    }

    public Identifier identifier() {
        return identifier;
    }

    public String label() {
        return label.string();
    }

    public Value value() {
        return value;
    }

    public Type type() {
        return value.type();
    }

    public String string() {
        return value.string();
    }

    Value setValue( Value value ) {

        if ( this.value.type() != value.type() )
            throw new RuntimeException( "TYPE_ERROR" );
        this.value = value;
        return value;
    }
}
