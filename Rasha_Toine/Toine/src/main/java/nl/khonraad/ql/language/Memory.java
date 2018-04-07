package nl.khonraad.ql.language;

import java.util.List;
import java.util.Optional;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.language.Question.BehaviouralType;

class Memory {

    private Questions     questions     = new Questions();
    private StickyAnswers stickyAnswers = new StickyAnswers();

    void prepare() {

        // TODO Explain sticky behaviour
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

    void addAnswerableQuestion( Identifier identifier, Label label, Type type ) {

        questions.add( new Question( BehaviouralType.ANSWERABLE, identifier, label, stickyAnswers.get( identifier, type ) ) );
    }

    Value addComputedQuestion( Identifier identifier, Label label, Value value ) {

        Question question = new Question( BehaviouralType.COMPUTED, identifier, label, value );
        questions.add( question );
        return value;
    }

    Optional<Question> queryQuestion( BehaviouralType behaviouralType, Identifier identifier ) {

        for ( Question question : questions ) {
            if ( behaviouralType.equals( question.getBehaviouralType() ) && question.identifier().equals( identifier ) ) {
                return Optional.of( question );
            }
        }
        return Optional.empty();
    }

    List<Question> questions() {
        return questions.listQuestions();
    }

    void storeAnwer( Question question, Value value ) {
        questions.storeAnswer( question, value );
    }

    public void dump() {

        System.out.println( "Dumping memory" );
        System.out.println( "--------------" );
        for ( Question question : questions ) {
            System.out.println( " > " + question.label() );
        }
        
    }
}
