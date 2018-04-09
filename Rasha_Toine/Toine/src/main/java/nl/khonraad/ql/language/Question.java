package nl.khonraad.ql.language;

import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.language.FormElement.BehaviouralType;

public interface Question {

    BehaviouralType getBehaviouralType();

    Identifier identifier();

    String label();

    Value value();

    Type type();

    String string();

    void answer( Value value );

}