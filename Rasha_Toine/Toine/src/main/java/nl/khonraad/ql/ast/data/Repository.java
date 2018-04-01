package nl.khonraad.ql.ast.data;

import java.util.List;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.ql.ast.data.Question.BehaviouralType;

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

    Value storeAnswerableQuestion( Identifier identifier, Label label, Type type ) {

        questions.add( new Question( BehaviouralType.ANSWERABLE, identifier, label, stickyAnswers.get( identifier, type ) ) );
        return null;
    }

    Value storeComputedQuestion( Identifier identifier, Label label, Value value ) {

        Question question = new Question( BehaviouralType.COMPUTED, identifier, label, value );
        questions.add( question );
        return value;
    }

    Question findQuestion( BehaviouralType behaviouralType, Identifier identifier ) {

        for ( Question question : questions ) {
            if ( behaviouralType.equals( question.getBehaviouralType() )
                    && question.identifier().equals( identifier ) ) {
                return question;
            }
        }
        return null;
    }

    public List<Question> questions() {
        return questions.listQuestions();
    }

    public void storeAnwer( Identifier identifier, Value value ) {
        questions.storeAnswer( identifier, value );
    }
}
