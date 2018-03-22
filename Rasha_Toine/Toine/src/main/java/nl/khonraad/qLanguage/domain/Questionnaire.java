package nl.khonraad.qLanguage.domain;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.khonraad.qLanguage.ast.AbstractParseTreeFactory;
import nl.khonraad.qLanguage.ast.ParseTreeVisitor;
import nl.khonraad.qLanguage.domain.Question.BehaviouralType;

public class Questionnaire {

    private ParseTreeVisitor   parseTreeVisitor;
    private ParseTree          parseTree;
    private QuestionRepository questionRepository;

    public Questionnaire(String s) throws RecognitionException, IOException {

        parseTree = AbstractParseTreeFactory.parseDataForTest( s ).form();
        parseTreeVisitor = new ParseTreeVisitor( this );
        questionRepository = new QuestionRepository();

    }

    public void visit() {
        parseTreeVisitor.visit( parseTree );
    }

    public void forgetQuestionsRememberAnswers() {
        questionRepository.forgetQuestionsRememberAnswers();
    };

    public List<Question> getQuestionList() {
        return questionRepository.listQuestions();
    }

    public Question findAnswerable( String identifier ) {
        return questionRepository.findQuestion( BehaviouralType.ANSWERABLE, identifier );
    };

    public Question findComputed( String identifier ) {
        return questionRepository.findQuestion( BehaviouralType.COMPUTED, identifier );
    };

    public Value storeAnswerableQuestion( String identifier, String label, Type type ) {
        return questionRepository.storeAnswerableQuestion( identifier, label, type );
    };

    public Value storeComputedQuestion( String identifier, String label, Value value ) {
        return questionRepository.storeComputedQuestion( identifier, label, value );
    }

    public Value storeAnswer( String identifier, Value value ) {
        return findAnswerable( identifier ).setValue( value );

    };
}
