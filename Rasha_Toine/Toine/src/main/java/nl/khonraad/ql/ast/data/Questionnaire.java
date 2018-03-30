package nl.khonraad.ql.ast.data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.khonraad.ql.QLLexer;
import nl.khonraad.ql.QLParser;
import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.Visitor;
import nl.khonraad.ql.ast.data.Question.BehaviouralType;

public class Questionnaire {

    private final class ErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e ) {
            throw new IllegalStateException( "failed to parse at line " + line + " due to " + msg, e );
        }
    }

    private Visitor    visitor;
    private ParseTree  ast;
    private Repository questionRepository;

    public Questionnaire(InputStream stream) throws IOException {

        QLLexer qLexer = new QLLexer( CharStreams.fromStream( stream, StandardCharsets.UTF_8 ) );

        QLParser qParser = new QLParser( new CommonTokenStream( qLexer ) );

        qParser.addErrorListener( new ErrorListener() );

        this.ast = qParser.form();

        this.visitor = new Visitor( this );

        this.questionRepository = new Repository();
    }

    public void visit() {

        try {
            questionRepository.prepare();
            visitor.visit( ast );
        } catch (IllegalStateException e) {
            System.out.println( e.getMessage() );
            System.exit( 1 );
        }

    }

    public Iterable<Question> getQuestionList() {
        return questionRepository.listQuestions();
    }

    public Question findComputed( Identifier identifier ) {
        return questionRepository.findQuestion( BehaviouralType.COMPUTED, identifier );
    }

    public Question findAnswerable( Identifier identifier ) {
        return questionRepository.findQuestion( BehaviouralType.ANSWERABLE, identifier );
    }

    public Value storeAnswerableQuestion( Identifier identifier, Label label, Type type ) {
        return questionRepository.storeAnswerableQuestion( identifier, label, type );
    }

    public Value storeComputedQuestion( Identifier identifier, Label label, Value value ) {
        return questionRepository.storeComputedQuestion( identifier, label, value );
    }

    public void storeAnswer( Identifier identifier, Value value ) {
        questionRepository.storeAnwer( identifier, value );
    }

}
