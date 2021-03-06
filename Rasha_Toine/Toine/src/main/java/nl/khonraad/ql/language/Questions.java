package nl.khonraad.ql.language;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import nl.khonraad.ql.algebra.values.Value;
import nl.khonraad.ql.language.FormElement.BehaviouralType;

class Questions implements Iterable<Question> {

    private List<Question> questionList = new ArrayList<>();

    
    @Override
    public Iterator<Question> iterator() {
        return questionList.iterator();
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

    void clear() {
        questionList.clear();
    }

    void add( Question question ) {
        questionList.add( question );
    }

    void storeAnswer( Question question, Value value ) {
        question.answer( value );
    }
}
