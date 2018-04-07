package nl.khonraad.ql.language;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.language.Question.BehaviouralType;

class Questions implements Iterable<Question> {

    private List<Question> questionList = new ArrayList<>();

    Value storeComputedQuestion( Identifier identifier, Label label, Value value ) {

        Question question = new Question( BehaviouralType.COMPUTED, identifier, label, value );
        questionList.add( question );
        return value;
    }

    Optional<Question> findQuestion( BehaviouralType behaviouralType, Identifier identifier ) {

        for ( Question question : questionList ) {
            if ( behaviouralType == question.getBehaviouralType() && question.identifier().equals( identifier ) ) {
                return Optional.of( question );
            }
        }
        return Optional.empty();
    }

    List<Question> list() {
        return questionList;
    }

    @Override
    public Iterator<Question> iterator() {
        return questionList.iterator();
    }

    void clear() {
        questionList.clear();
    }

    void add( Question question ) {
        questionList.add( question );
    }

    void storeAnswer( Question question, Value value ) {
        question.setValue( value );
    }
}
