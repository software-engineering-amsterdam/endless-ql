package nl.khonraad.ql.algebra;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

import org.junit.Test;

import nl.khonraad.ql.dynamics.Question;

public class Test_Question {

    @Test
    public void identifier() {
        qt().forAll( integers().allPositive() ).check( ( i ) -> new Question( Question.BehaviouralType.ANSWERABLE, "q"
                + i, "l" + i, new Value( i ) ).getIdentifier().equals( "q" + i ) );
    }

    @Test
    public void label() {
        qt().forAll( integers().allPositive() ).check( ( i ) -> new Question( Question.BehaviouralType.ANSWERABLE, "q"
                + i, "l" + i, new Value( i ) ).getLabel().equals( "l" + i ) );
    }


}