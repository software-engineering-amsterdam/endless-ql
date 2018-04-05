package nl.khonraad.ql.domain;

import java.util.Optional;

import nl.khonraad.ql.QLVisitor;
import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;

public interface Questionnaire {

    void visitSource( QLVisitor<Value> visitor );

    Iterable<Question> questions();

    Optional<Question> findComputedQuestion( Identifier identifier );

    Optional<Question> findAnswerableQuestion( Identifier identifier );

    void storeAnswerableQuestion( Identifier identifier, Label label, Type type );

    Value storeComputedQuestion( Identifier identifier, Label label, Value value );

    void storeAnswer( Question question, Value value );

}