package nl.khonraad.ql.ast.data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.QLAbstractSyntaxTreeBuilder;
import nl.khonraad.ql.ast.data.Question.BehaviouralType;
import nl.khonraad.ql.cdi.SourcePathProvider;
import nl.khonraad.ql.gui.application.VisualizeEvent;

@ApplicationScoped public class Questionnaire {

    @Inject
    Logger                logger;

    @Inject
    SourcePathProvider              qLSource;

    @Inject
    Event<VisualizeEvent> eventQueue;

    @Inject
    private Repository    questionRepository;

    @Inject
    private QLAbstractSyntaxTreeBuilder  qLAstBuilder;

    public void prepareAndVisit( ExtendedQLBaseVisitor visitor ) {

        try {
            questionRepository.prepare();

            visitor.visit( qLAstBuilder.getTree() );

        } catch (IllegalStateException e) {

            logger.info( e.getMessage() );
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
}
