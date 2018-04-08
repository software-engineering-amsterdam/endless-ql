package nl.khonraad.ql.language;

import java.util.Optional;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.parser.QuestionsVisitor;

public interface QuestionsInterpretor {

    void assign( Question question, Value value );

    void declareAsAnswerableQuestion( Identifier identifier, Label label, Type type );

    Value declareAsComputedQuestion( Identifier identifier, Label label, Value value );

    void memoryDump();

    Optional<Question> queryComputedQuestion( Identifier identifier );

    Optional<Question> queryAnswerableQuestion( Identifier identifier );

    Optional<Question> queryQuestion( Identifier identifier );

    Iterable<Question> questions();

    void visitSource( QuestionsVisitor visitor );

}