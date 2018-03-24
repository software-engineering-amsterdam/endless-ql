package nl.khonraad.ql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.khonraad.ql.ast.AbstractParseTreeFactory;
import nl.khonraad.ql.domain.Questionnaire;
import nl.khonraad.ql.domain.Value;

public class Test_Interpretor {

    @Test
    public void test_QLFarmer() throws Exception {

        QParser qParser = AbstractParseTreeFactory.resourceToStream( "/associative.txt" );

        Questionnaire questionnaire = new Questionnaire( qParser.form() );

        questionnaire.visit();

        assertEquals( "a", 14, Integer.parseInt( questionnaire.findComputed( "a" ).getValue().getText() ) );
        assertEquals( "b", 20, Integer.parseInt( questionnaire.findComputed( "b" ).getValue().getText() ) );
        assertEquals( "c", true, Boolean.parseBoolean(  questionnaire.findComputed( "c" ).getValue().getText() ) );

    }
}