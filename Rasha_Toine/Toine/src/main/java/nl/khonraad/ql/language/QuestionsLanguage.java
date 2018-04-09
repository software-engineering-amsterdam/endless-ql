package nl.khonraad.ql.language;


import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.BeforeDestroyed;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.gui.VisualizeEvent;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.language.Question.BehaviouralType;
import nl.khonraad.ql.parser.QuestionsAST;
import nl.khonraad.ql.parser.QuestionsVisitor;

@ApplicationScoped public class QuestionsLanguage implements QuestionsInterpretor {

    @Inject
    Logger                  logger;

    @Inject
    Event<VisualizeEvent>   event;

    @Inject
    private QuestionsAST    questionsAST;

    private QuestionsMemory questionsMemory;

    @PostConstruct
    void postConstruct() {
        questionsMemory = new QuestionsMemory();
    }

    public void destroy( @Observes @BeforeDestroyed( ApplicationScoped.class ) Object init ) {
        questionsMemory.dump();
    }

    @Override
    public void visitSource( QuestionsVisitor visitor ) {
        
        questionsMemory.reset();
        visitor.visit( questionsAST.getTree() );
    }

    @Override
    public Iterable<Question> questions() {
        return questionsMemory.queryQuestions();
    }

    @Override
    public Optional<Question> queryComputedQuestion( Identifier identifier ) {
        return questionsMemory.queryQuestion( BehaviouralType.COMPUTED, identifier );
    }

    @Override
    public Optional<Question> queryAnswerableQuestion( Identifier identifier ) {
        return questionsMemory.queryQuestion( BehaviouralType.ANSWERABLE, identifier );
    }

    @Override
    public Optional<Question> queryQuestion( Identifier identifier ) {
        return questionsMemory.queryQuestion( identifier );
    }

    @Override
    public void declareAsAnswerableQuestion( Identifier identifier, Label label, Type type ) {
        questionsMemory.addAnswerableQuestion( identifier, label, type );
    }

    @Override
    public Value declareAsComputedQuestion( Identifier identifier, Label label, Value value ) {
        return questionsMemory.addComputedQuestion( identifier, label, value );
    }

    @Override
    public void assign( Question question, Value value ) {
        questionsMemory.storeAnwer( question, value );
        event.fire( new VisualizeEvent() );
    }

    @Override
    public void memoryDump() {
        questionsMemory.dump();
    }
}
