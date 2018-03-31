package nl.khonraad.ql.ast.data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;

import nl.khonraad.ql.QLLexer;
import nl.khonraad.ql.QLParser;
import nl.khonraad.ql.VisualizeEvent;
import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.Visitor;
import nl.khonraad.ql.ast.data.Question.BehaviouralType;
import nl.khonraad.ql.cdiSupport.QLSource;

@ApplicationScoped public class Questionnaire {

    @Inject
    Logger                logger;

    @Inject
    QLSource              qLSource;

    @Inject
    Event<VisualizeEvent> eventQueue;

    @Inject
    private Repository    questionRepository;

    private ParseTree     ast;

    @PostConstruct
    public void postConstruct() {

        QLLexer qLexer;

        try {

            InputStream inputStream = getClass().getResourceAsStream( qLSource.getPath() );

            qLexer = new QLLexer( CharStreams.fromStream( inputStream, StandardCharsets.UTF_8 ) );

            QLParser qParser = new QLParser( new CommonTokenStream( qLexer ) );

            qParser.addErrorListener( new ErrorListener() );

            ast = qParser.form();

        } catch (IOException e) {
            logger.info( e.getMessage() );
            e.printStackTrace();
        }

    }

    public void prepareAndVisit( Visitor visitor ) {

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

        if ( eventQueue != null ) {

            eventQueue.fire( new VisualizeEvent() );
        }
    }

    private final class ErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e ) {
            throw new IllegalStateException( "failed to parse at line " + line + " due to " + msg, e );
        }
    }

}
