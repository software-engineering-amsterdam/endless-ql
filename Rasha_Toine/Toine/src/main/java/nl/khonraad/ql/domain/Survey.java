package nl.khonraad.ql.domain;

import java.util.Optional;

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
import nl.khonraad.ql.cdi.SourcePathProvider;
import nl.khonraad.ql.domain.Question.BehaviouralType;
import nl.khonraad.ql.gui.application.VisualizeEvent;

@ApplicationScoped public class Survey implements Questionnaire {

    @Inject
    Logger                              logger;

    @Inject
    SourcePathProvider                  qLSource;

    @Inject
    Event<VisualizeEvent>               eventQueue;

    @Inject
    private Repository                  questionRepository;

    @Inject
    private QLAbstractSyntaxTreeBuilder qLAstBuilder;

    @Override
    public void visitSource( QLVisitor<Value> visitor ) {

        try {

            questionRepository.prepare();
            visitor.visit( qLAstBuilder.getTree() );

        } catch (IllegalStateException e) {

            logger.info( e.getMessage() );
        }
    }

    @Override
    public Iterable<Question> questions() {
        return questionRepository.questions();
    }

    @Override
    public Optional<Question> findComputedQuestion( Identifier identifier ) {
        return questionRepository.findQuestion( BehaviouralType.COMPUTED, identifier );
    }

    @Override
    public Optional<Question> findAnswerableQuestion( Identifier identifier ) {
        return questionRepository.findQuestion( BehaviouralType.ANSWERABLE, identifier );
    }

    @Override
    public void storeAnswerableQuestion( Identifier identifier, Label label, Type type ) {
        questionRepository.storeAnswerableQuestion( identifier, label, type );
    }

    @Override
    public Value storeComputedQuestion( Identifier identifier, Label label, Value value ) {
        return questionRepository.storeComputedQuestion( identifier, label, value );
    }

    @LoggingAspect
    @Override
    public void storeAnswer( Identifier identifier, Value value ) {

        questionRepository.storeAnwer( identifier, value );
        eventQueue.fire( new VisualizeEvent() );
    }
}
