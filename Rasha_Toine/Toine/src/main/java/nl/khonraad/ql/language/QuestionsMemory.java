package nl.khonraad.ql.language;

import java.util.List;
import java.util.Optional;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.values.Type;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.language.Question.BehaviouralType;

class QuestionsMemory {

    private Questions     questions     = new Questions();
    private StickyAnswers stickyAnswers = new StickyAnswers();

    void reset() {

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

    public Optional<Question> queryQuestion( Identifier identifier ) {
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

        final String LINEHEADER = "==========================================================================================================================================";

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
}
