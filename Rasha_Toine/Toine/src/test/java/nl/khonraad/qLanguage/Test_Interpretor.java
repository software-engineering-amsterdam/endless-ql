package nl.khonraad.qLanguage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.khonraad.qLanguage.domain.Questionnaire;
import nl.khonraad.qLanguage.domain.Type;
import nl.khonraad.qLanguage.domain.Value;

public class Test_Interpretor {

    @Test
    public void test_QLFarmer() throws Exception {

        String testData = "form Box1HouseOwning {																			"
                + "   hasSoldHouse: \"Did you sell a house in 2010?\" boolean										"
                + "   hasBoughtHouse: \"Did you by a house in 2010?\" boolean										"
                + "   hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\"  boolean				"
                + "																								"
                + "	if (hasSoldHouse) {																			"
                + "		sellingPrice: \"Price the house was sold for:\" money										"
                + "		privateDebt: \"Private debts for the sold house:\" money									"
                + "  		valueResidue: \"Value residue:\" money (sellingPrice - privateDebt - 0.01 )			"
                + "  		testDate: \"Testdate:\" date (01/01/1970 + 3 )										"
                + "  		testString: \"testString:\" string (\"abc\"  + \"ABC\")								"
                + "  	}																						"
                + "}																								";

        Questionnaire questionnaire = new Questionnaire( testData );

        questionnaire.visit();

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
        assertEquals( "Calculated answer", new Value( Type.Date, "04/01/1970" ),
                questionnaire.findComputed( "testDate" ).getValue() );
        assertEquals( "Calculated answer", new Value( Type.String, "abcABC" ),
                questionnaire.findComputed( "testString" ).getValue() );
    }

    @Test
    public void test_Operators() throws Exception {

        Questionnaire questionnaire;

        String s = "";

        s = "form x { x: \"x:\" integer (4+5)   }";
        questionnaire = new Questionnaire( s );
        questionnaire.visit();

        assertEquals( "x", new Value( Type.Integer, "9" ), questionnaire.findComputed( "x" ).getValue() );

        s = "form x { x: \"x:\" boolean (True || False)   }";
        questionnaire = new Questionnaire( s );
        questionnaire.visit();

        assertEquals( "x", Value.TRUE, questionnaire.findComputed( "x" ).getValue() );

        s = "form x { x: \"x:\" boolean (True == False)   }";
        questionnaire = new Questionnaire( s );
        questionnaire.visit();

        assertEquals( "x", Value.FALSE, questionnaire.findComputed( "x" ).getValue() );

        s = "form x { x: \"x:\" boolean (2.50 >= (5.50 - 3.00 * 1) )   }";
        questionnaire = new Questionnaire( s );
        questionnaire.visit();

        assertEquals( "x", Value.TRUE, questionnaire.findComputed( "x" ).getValue() );

    }

}