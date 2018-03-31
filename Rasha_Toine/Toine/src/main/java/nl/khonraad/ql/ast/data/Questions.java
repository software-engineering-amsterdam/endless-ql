package nl.khonraad.ql.ast.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.algebra.Label;
import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.ast.data.Question.BehaviouralType;

public class Questions implements Iterable<Question> {

    private List<Question> questions = new ArrayList<>();

    Value storeComputedQuestion( Identifier identifier, Label label, Value value ) {
        Question question = new Question( BehaviouralType.COMPUTED, identifier, label, value );

        questions.add( question );
        return value;
    }

    Question findQuestion( BehaviouralType behaviouralType, Identifier identifier ) {

        for ( Question question : questions ) {
            if ( behaviouralType == question.getBehaviouralType() && question.identifier().equals( identifier ) ) {
                return question;
            }
        }
        return null;
    }

    List<Question> listQuestions() {

        return questions;

    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }

    void clear() {
        questions.clear();
    }

    void add( Question question ) {
        questions.add( question );

    }

    void storeAnswer( Identifier identifier, Value value ) {
        Question question = findQuestion( BehaviouralType.ANSWERABLE, identifier );
        question.setValue( value );
    }

}
