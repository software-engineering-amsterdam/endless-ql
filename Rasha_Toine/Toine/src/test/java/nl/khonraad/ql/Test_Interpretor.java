package nl.khonraad.ql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.khonraad.ql.ast.AbstractParseTreeFactory;
import nl.khonraad.ql.domain.Questionnaire;
import nl.khonraad.ql.domain.Type;
import nl.khonraad.ql.domain.Value;

public class Test_Interpretor {

    @Test
    public void test_QLFarmer() throws Exception {

        QParser qParser = AbstractParseTreeFactory.resourceToStream( "/1.txt" );

        Questionnaire questionnaire = new Questionnaire( qParser.form() );

        questionnaire.visit();

        assertEquals( "Calculated answer", Value.FALSE,

                questionnaire.findComputed( "tB" ).getValue()

        );
        assertEquals( "Calculated answer", new Value( Type.Money, "-1.25" ),

                questionnaire.findComputed( "tA" ).getValue()

        );
        assertEquals( "Calculated answer", new Value( Type.Money, "1.25" ),

                questionnaire.findComputed( "tD" ).getValue()

        );
        // simulate answers given

        questionnaire.storeAnswer( "hasSoldHouse", Value.TRUE );
        questionnaire.visit();
        //
        questionnaire.storeAnswer( "sellingPrice", new Value( Type.Money, "100.03" ) );
        questionnaire.storeAnswer( "privateDebt", new Value( Type.Money, "25.00" ) );
        questionnaire.visit();

        assertEquals( "Calculated answer", new Value( Type.Money, "75.02" ),

                questionnaire.findComputed( "valueResidue" ).getValue()

        );
        assertEquals( "Calculated answer", new Value( Type.Date, "04/01/1970" ), questionnaire.findComputed( "testDate" ).getValue() );
        assertEquals( "Calculated answer", new Value( Type.String, "abcABC" ), questionnaire.findComputed( "testString" ).getValue() );
    }

    // @Test
    // public void test_Operators() throws Exception {
    //
    // String s = "";
    //
    // s = "form x { x: \"x:\" integer (4+5) }";
    //
    // QParser qParser = AbstractParseTreeFactory.fromString( s );
    // Questionnaire questionnaire = new Questionnaire( qParser );
    //
    // questionnaire = new Questionnaire( s );
    // questionnaire.visit();
    //
    // assertEquals( "x", new Value( Type.Integer, "9" ),
    // questionnaire.findComputed( "x" ).getValue() );
    //
    // s = "form x { x: \"x:\" boolean (True || False) }";
    // questionnaire = new Questionnaire( s );
    // questionnaire.visit();
    //
    // assertEquals( "x", Value.TRUE, questionnaire.findComputed( "x" ).getValue()
    // );
    //
    // s = "form x { x: \"x:\" boolean (True == False) }";
    // questionnaire = new Questionnaire( s );
    // questionnaire.visit();
    //
    // assertEquals( "x", Value.FALSE, questionnaire.findComputed( "x" ).getValue()
    // );
    //
    // s = "form x { x: \"x:\" boolean (2.50 >= (5.50 - 3.00 * 1) ) }";
    // questionnaire = new Questionnaire( s );
    // questionnaire.visit();
    //
    // assertEquals( "x", Value.TRUE, questionnaire.findComputed( "x" ).getValue()
    // );

    // }

}