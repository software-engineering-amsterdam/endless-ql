package nl.khonraad.ql.ast.data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.ql.VisualizeEvent;
import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.ExtendedQLBaseVisitor;
import nl.khonraad.ql.ast.data.Question.BehaviouralType;
import nl.khonraad.ql.cdisupport.QLSource;

@ApplicationScoped public class Questionnaire {

    @Inject
    Logger                logger;

    @Inject
    QLSource              qLSource;

    @Inject
    Event<VisualizeEvent> eventQueue;

    @Inject
    private Repository    questionRepository;

    @Inject
    private QLAstBuilder  qLAstBuilder;

    public void prepareAndVisit( ExtendedQLBaseVisitor visitor ) {

        try {
            questionRepository.prepare();

            visitor.visit( qLAstBuilder.ast );

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
}
