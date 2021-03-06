package nl.khonraad.ql.language;

import java.util.List;
import java.util.Optional;

import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.language.FormElement.BehaviouralType;

class QuestionsMemory {

    private Questions     questions     = new Questions();
    private StickyAnswers stickyAnswers = new StickyAnswers();

    void reset() {

        resetStickyAnswers();
        resetQuestions();
    }

    void addAnswerableQuestion( Identifier identifier, Label label, Type type ) {
        questions.add( new FormElement( BehaviouralType.ANSWERABLE, identifier, label, stickyAnswers.get( identifier, type ) ) );
    }

    Value addComputedQuestion( Identifier identifier, Label label, Value value ) {

        FormElement question = new FormElement( BehaviouralType.COMPUTED, identifier, label, value );
        questions.add( question );
        return value;
    }

    Optional<Question> queryQuestion( Identifier identifier ) {
        for ( Question question : questions ) {
            if ( question.identifier().equals( identifier ) ) {
                return Optional.of( question );
            }
        }
        return Optional.empty();
    }

    Optional<Question> queryQuestion( BehaviouralType behaviouralType, Identifier identifier ) {

        for ( Question question : questions ) {
            if ( behaviouralType.equals( question.getBehaviouralType() ) && question.identifier().equals( identifier ) ) {
                return Optional.of( question );
            }
        }
        return Optional.empty();
    }

    List<Question> queryQuestions() {
        return questions.list();
    }

    void storeAnwer( Question question, Value value ) {
        questions.storeAnswer( question, value );
    }

    void dump() {

        String LINEHEADER = "==========================================================================================================================================";

        System.out.println();
        System.out.println( LINEHEADER );
        System.out.println( "Speaking the \"vernacular\", these are the questions and resp. answers in good-old CVS-format" );
        System.out.println( LINEHEADER );

        final String QUOTE = "\"";
        final String COMMA = ", ";

        System.out.println( QUOTE + "Question" + QUOTE + COMMA + QUOTE + "Answer" + QUOTE );

        for ( Question question : questions ) {

            System.out.println( QUOTE + question.label() + QUOTE + COMMA + QUOTE + question.value().string() + QUOTE );
        }
        System.out.println( LINEHEADER );
        System.out.println();

    }

    private void resetStickyAnswers() {
        stickyAnswers.clear();
    
        for ( Question question : questions ) {
            
            if ( BehaviouralType.ANSWERABLE == question.getBehaviouralType() ) {
                stickyAnswers.add( question.identifier(), question.value() );
            }
        }
    }

    private void resetQuestions() {
        questions.clear();
    }
}
