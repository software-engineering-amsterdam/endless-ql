package nl.khonraad.ql.language;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.ql.QLVisitor;
import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.ast.QLAbstractSyntaxTreeBuilder;
import nl.khonraad.ql.cdi.LoggingAspect;
import nl.khonraad.ql.gui.application.VisualizeEvent;
import nl.khonraad.ql.language.Question.BehaviouralType;

@ApplicationScoped public class QLLanguage implements QLInterpretor {

    @Inject
    Logger                              logger;

    @Inject
    Event<VisualizeEvent>               event;

    @Inject
    private QLAbstractSyntaxTreeBuilder qLAstBuilder;

    private Memory                      memory;

    @PostConstruct
    void postConstruct() {

        memory = new Memory();
    }

    @Override
    public void visitSource( QLVisitor<Value> visitor ) {

        try {

            memory.prepare();

            visitor.visit( qLAstBuilder.getTree() );

        } catch (IllegalStateException e) {

            logger.info( e.getMessage() );
        }
    }

    @Override
    public Iterable<Question> questions() {
        return memory.questions();
    }

    @Override
    public Optional<Question> queryComputedQuestion( Identifier identifier ) {
        return memory.queryQuestion( BehaviouralType.COMPUTED, identifier );
    }

    @Override
    public Optional<Question> queryAnswerableQuestion( Identifier identifier ) {
        return memory.queryQuestion( BehaviouralType.ANSWERABLE, identifier );
    }

    @Override
    public void declareAsAnswerableQuestion( Identifier identifier, Label label, Type type ) {
        memory.addAnswerableQuestion( identifier, label, type );
    }

    @Override
    public Value declareAsComputedQuestion( Identifier identifier, Label label, Value value ) {
        return memory.addComputedQuestion( identifier, label, value );
    }

    @LoggingAspect
    @Override
    public void assigns( Question question, Value value ) {

        memory.storeAnwer( question, value );
        event.fire( new VisualizeEvent() );
    }

    @Override
    public void dump() {
       memory.dump();
        
    }
}
