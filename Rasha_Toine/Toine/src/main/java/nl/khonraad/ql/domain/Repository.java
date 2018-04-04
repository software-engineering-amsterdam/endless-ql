package nl.khonraad.ql.domain;

import java.util.List;
import java.util.Optional;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.domain.Question.BehaviouralType;

public class Repository {

    private Questions     questions     = new Questions();
    private StickyAnswers stickyAnswers = new StickyAnswers();

    void prepare() {

        stickyAnswers.clear();

        for ( Question question : questions ) {
            stickAnswer( question );
        }
        questions.clear();
    }

    private void stickAnswer( Question question ) {

        if ( BehaviouralType.ANSWERABLE == question.getBehaviouralType() ) {
            stickyAnswers.add( question.identifier(), question.value() );
        }
    }

    void storeAnswerableQuestion( Identifier identifier, Label label, Type type ) {

        questions.add( new Question( BehaviouralType.ANSWERABLE, identifier, label, stickyAnswers.get( identifier, type ) ) );
    }

    Value storeComputedQuestion( Identifier identifier, Label label, Value value ) {

        Question question = new Question( BehaviouralType.COMPUTED, identifier, label, value );
        questions.add( question );
        return value;
    }

    Optional<Question> findQuestion( BehaviouralType behaviouralType, Identifier identifier ) {

        for ( Question question : questions ) {
            if ( behaviouralType.equals( question.getBehaviouralType() )
                    && question.identifier().equals( identifier ) ) {
                return Optional.of( question );
            }
        }
        return Optional.empty();
    }

    public List<Question> questions() {
        return questions.listQuestions();
    }

    public void storeAnwer( Identifier identifier, Value value ) {
        questions.storeAnswer( identifier, value );
    }
}
