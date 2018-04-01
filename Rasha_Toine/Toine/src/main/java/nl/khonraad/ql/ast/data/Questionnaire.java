package nl.khonraad.ql.ast.data;

import nl.khonraad.ql.QLVisitor;
import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;

public interface Questionnaire {

    void visitSource( QLVisitor<Value> visitor );

    Iterable<Question> questions();

    Question findComputedQuestion( Identifier identifier );

    Question findAnswerableQuestion( Identifier identifier );

    Value storeAnswerableQuestion( Identifier identifier, Label label, Type type );

    Value storeComputedQuestion( Identifier identifier, Label label, Value value );

    void storeAnswer( Identifier identifier, Value value );

}