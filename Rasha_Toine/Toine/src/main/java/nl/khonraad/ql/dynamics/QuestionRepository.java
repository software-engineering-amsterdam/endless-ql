package nl.khonraad.ql.dynamics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;

import nl.khonraad.ql.algebra.Question;
import nl.khonraad.ql.algebra.Type;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.Question.BehaviouralType;

public class QuestionRepository {

    private List<Question>     questions     = new ArrayList<>();
    private Map<String, Value> stickyAnswers = new HashMap<>();

    public void forgetQuestionsRememberAnswers() {

        stickyAnswers.clear();

        for ( Question question : questions ) {
            if ( BehaviouralType.ANSWERABLE == question.getBehaviouralType() ) {
                stickyAnswers.put( question.getIdentifier(), question.getValue() );
            }
        }
        questions.clear();
    }

    public Value storeAnswerableQuestion( String identifier, String label, Type type ) {
        Question question = new Question( BehaviouralType.ANSWERABLE, identifier, label, answerSeenEarlier( identifier, type ) );

        questions.add( question );
        return question.getValue();
    }

    public Value storeComputedQuestion( String identifier, String label, Value value ) {
        Question question = new Question( BehaviouralType.COMPUTED, identifier, label, value );

        questions.add( question );
        return value;
    }

    public Question findQuestion( BehaviouralType behaviouralType, String identifier ) {

        for ( Question question : questions ) {
            if ( behaviouralType == question.getBehaviouralType()
                    && question.getIdentifier().equals( identifier ) ) { return question; }
        }
        return null;
    }

    public List<Question> listQuestions() {

        return questions;

    }

    private static Value initialValueOf( Type type ) {
        switch ( type ) {
            case Boolean:
                return Value.FALSE;
            case Date:
                return new Value( Type.Date, Value.SIMPLE_DATE_FORMAT.print( new DateTime() ) );
            case Integer:
                return new Value( Type.Integer, "0" );
            case Money:
                return new Value( Type.Money, "0.00" );
            case String:
                return new Value( Type.String, "" );
        }
        throw new RuntimeException( "'Constructor' not imlemented for type " + type );
    }

    private Value answerSeenEarlier( String identifier, Type type ) {

        return stickyAnswers.entrySet().stream().filter( entry -> identifier.equals( entry.getKey() ) ).map( Entry<String, Value>::getValue ).findFirst().orElse( initialValueOf( type ) );

    }

}
