package nl.khonraad.ql;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

import nl.khonraad.ql.dynamics.Questionnaire;

public class Test_QCalculator {

    @Test
    public void test_Calculations() throws Exception {

        InputStream stream = getClass().getResourceAsStream( "/Calculator" );

        Questionnaire questionnaire = new Questionnaire( stream );

        questionnaire.visit();

        assertEquals( "a", 14, Integer.parseInt( questionnaire.findComputed( "a" ).getValue().getText() ) );
        assertEquals( "b", 20, Integer.parseInt( questionnaire.findComputed( "b" ).getValue().getText() ) );
        assertEquals( "c", true, Boolean.parseBoolean( questionnaire.findComputed( "c" ).getValue().getText() ) );

    }
}