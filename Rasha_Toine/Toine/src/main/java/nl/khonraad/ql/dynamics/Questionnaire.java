package nl.khonraad.ql.dynamics;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.khonraad.ql.QLexer;
import nl.khonraad.ql.QParser;
import nl.khonraad.ql.algebra.Question;
import nl.khonraad.ql.algebra.Type;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.Question.BehaviouralType;

public class Questionnaire {

    private Visitor            visitor;
    private ParseTree          ast;

    private QuestionRepository questionRepository;

    public Questionnaire(InputStream stream) throws IOException {

        QLexer qLexer = new QLexer( CharStreams.fromStream( stream, StandardCharsets.UTF_8 ) );

        QParser qParser = new QParser( new CommonTokenStream( qLexer ) );

        qParser.addErrorListener( new BaseErrorListener() {
            @Override
            public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                    int charPositionInLine, String msg, RecognitionException e ) {
                throw new IllegalStateException( "failed to parse at line " + line + " due to " + msg, e );
            }
        } );

        this.ast = qParser.form();
        
        this.visitor = new Visitor( this );
        
        this.questionRepository = new QuestionRepository();
    }

    public void visit() {
        visitor.visit( ast );
    }

    public void forgetQuestionsRememberAnswers() {
        questionRepository.forgetQuestionsRememberAnswers();
    }

    public Iterable<Question> getQuestionList() {
        return questionRepository.listQuestions();
    }

    public Question findAnswerable( String identifier ) {
        return questionRepository.findQuestion( BehaviouralType.ANSWERABLE, identifier );
    }

    public Question findComputed( String identifier ) {
        return questionRepository.findQuestion( BehaviouralType.COMPUTED, identifier );
    }

    public Value storeAnswerableQuestion( String identifier, String label, Type type ) {
        return questionRepository.storeAnswerableQuestion( identifier, label, type );
    }

    public Value storeComputedQuestion( String identifier, String label, Value value ) {
        return questionRepository.storeComputedQuestion( identifier, label, value );
    }

    public Value storeAnswer( String identifier, Value value ) {
        return findAnswerable( identifier ).setValue( value );

    }
}
