package nl.khonraad.ql.language;

import java.util.Optional;

import nl.khonraad.ql.QLVisitor;
import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;

public interface QLInterpretor {

    void visitSource( QLVisitor<Value> visitor );
    
    Iterable<Question> questions();

    Optional<Question> queryComputedQuestion( Identifier identifier );

    Optional<Question> queryAnswerableQuestion( Identifier identifier );

    Optional<Question> queryQuestion( Identifier identifier );

    void declareAsAnswerableQuestion( Identifier identifier, Label label, Type type );

    Value declareAsComputedQuestion( Identifier identifier, Label label, Value value );

    void assign( Question question, Value value );
    
    void memoryDump();

}