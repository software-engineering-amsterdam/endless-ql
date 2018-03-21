package nl.khonraad.qLanguage.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.khonraad.qLanguage.domain.Question.BehaviouralType;

public class QuestionRepository {

    private List<Question> questions     = new ArrayList<Question>();
    private Map<String, Value>    stickyAnswers = new HashMap<String, Value>();

    public void forgetQuestionsRememberAnswers() {

        stickyAnswers.clear();
        
        for ( Question question : questions) {
            if ( BehaviouralType.ANSWERABLE == question.getBehaviouralType() ) {
                stickyAnswers.put( question.getIdentifier(), question.getValue() );
            }
        }
        questions.clear();
    }

    public Value storeAnswerableQuestion( String identifier, String label, Type type ) {
        Question question = new Question( BehaviouralType.ANSWERABLE, identifier, label, 
                answerSeenEarlier( identifier, type ) );

        questions.add(  question );
        return question.getValue();
    }

    public Value storeComputedQuestion( String identifier, String label, Value value ) {
        Question question = new Question( BehaviouralType.COMPUTED, identifier, label,  value );

        questions.add( question );
        return value;
    }

    public Question findQuestion( BehaviouralType behaviouralType, String identifier ) {

        for ( Question question : questions) {
            if ( behaviouralType == question.getBehaviouralType() && question.getIdentifier().equals( identifier )) {
                return question;
            }
        }
        return null;
    }

    public List<Question> listQuestions() {

        return questions;

    }

    private static Value initialValueOf( Type type ) {

        switch ( type ) {
            case Boolean:
                return Value.INITIAL_BOOLEAN;
            case Date:
                return Value.INITIAL_DATE;
            case Integer:
                return Value.INITIAL_INTEGER;
            case Money:
                return Value.INITIAL_MONEY;
            case String:
                return Value.INITIAL_STRING;
        }
        throw new RuntimeException( "'Constructor' not imlemented for type " + type );
    }
    private Value answerSeenEarlier( String identifier, Type type ) {

        return stickyAnswers.entrySet().stream().filter( entry -> identifier.equals( entry.getKey() ) )
                .map( map -> map.getValue() ).findFirst().orElse( initialValueOf( type ) );

    }

}
