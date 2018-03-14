package nl.khonraad.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import nl.khonraad.domain.Question.BehaviouralType;

public class Form {

    private Map<String, Question> questions     = new HashMap<String, Question>();
    private Map<String, Value>    stickyAnswers = new HashMap<String, Value>();

    public void forgetQuestionsRememberAnswers() {

        for (Map.Entry<String, Question> entry : questions.entrySet()) {
            if ( BehaviouralType.ANSWERABLE == entry.getValue().getBehaviouralType() ) {
                stickyAnswers.put( entry.getKey(), entry.getValue().getValue() );
            }
        }
        questions.clear();
    }

    public Value saveAnswerableQuestion( String identifier, String label, Type type ) {
        Question question = new Question( BehaviouralType.ANSWERABLE, identifier, label, type,
                answerSeenEarlier( identifier, type ) );

        questions.put( identifier, question );
        return question.getValue();
    }

    public Value saveComputedQuestion( String identifier, String label, Type type, Value value ) {
        Question question = new Question( BehaviouralType.COMPUTED, identifier, label, type, value );

        questions.put( identifier, question );
        return value;
    }

    public Optional<Question> findQuestion( BehaviouralType behaviouralType, String identifier ) {

        return questions.entrySet().stream()
                .filter( map -> behaviouralType.equals( map.getValue().getBehaviouralType() ) )
                .filter( map -> identifier.equals( map.getValue().getIdentifier() ) ).map( map -> map.getValue() )
                .findFirst();
    }

    public List<Question> listQuestions() {

        return questions.entrySet().stream().map( map -> map.getValue() ).collect( Collectors.toList() );

    }

    private Value answerSeenEarlier( String identifier, Type type ) {

        return stickyAnswers.entrySet().stream().filter( entry -> identifier.equals( entry.getKey() ) )
                .map( map -> map.getValue() ).findFirst().orElse( new Value( type ) );

    }

}
