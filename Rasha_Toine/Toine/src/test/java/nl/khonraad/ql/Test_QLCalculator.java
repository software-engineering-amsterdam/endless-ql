package nl.khonraad.ql;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

import nl.khonraad.ql.algebra.Identifier;
import nl.khonraad.ql.ast.data.Questionnaire;

public class Test_QLCalculator {

    @Test
    public void test_Calculations() throws Exception {

        InputStream stream = getClass().getResourceAsStream( "/Calculator" );

        Questionnaire questionnaire = new Questionnaire( stream );

        questionnaire.visit();

        assertEquals( "a", 14, Integer.parseInt( questionnaire.findComputed( new Identifier( "a" ) ).value().string() ) );
        assertEquals( "b", 20, Integer.parseInt( questionnaire.findComputed( new Identifier( "b" ) ).value().string() ) );
        assertEquals( "c", true, Boolean.parseBoolean( questionnaire.findComputed( new Identifier( "c" ) ).value().string() ) );
    }
}