package nl.khonraad.qLanguage.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Question {

    private static final String ERROR_TYPEERROR = "Type error: ";

    public enum BehaviouralType {

        ANSWERABLE, COMPUTED;
    }

    private BehaviouralType behaviouralType;
    private String          identifier;
    private String          label;
    private Value           value;

    public Question(BehaviouralType behaviouralType, String identifier, String label, Value value) {

        this.behaviouralType = behaviouralType;
        this.identifier = identifier;
        this.label = label;
        this.value = value;
    }

    public BehaviouralType getBehaviouralType() {
        return behaviouralType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLabel() {
        return label;
    }

    public Value getValue() {
        return value;
    }

    public Value setValue( Value value ) {
        if ( this.getValue().getType() != value.getType() )
            throw new RuntimeException( ERROR_TYPEERROR + "Question " + identifier + " expects "
                    + this.getValue().getType() + " not " + value.getType() );
        this.value = value;
        return value;
    }

    @Override
    public String toString() {

        return new ToStringBuilder( this, ToStringStyle.MULTI_LINE_STYLE ).append( "identifier", identifier )
                .append( "label", label ).append( "value", value ).toString();
    }
}
