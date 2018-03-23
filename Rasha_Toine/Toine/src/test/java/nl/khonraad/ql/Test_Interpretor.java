package nl.khonraad.ql;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.khonraad.ql.domain.Questionnaire;
import nl.khonraad.ql.domain.Type;
import nl.khonraad.ql.domain.Value;

public class Test_Interpretor {

  @Test
  public void test_QLFarmer() throws Exception {

    String testData = "form Box1HouseOwning {																			"
        + "   hasSoldHouse: \"Did you sell a house in 2010?\" boolean										"
        + "   hasBoughtHouse: \"Did you by a house in 2010?\" boolean										"
        + "   hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\"  boolean				"
        + "  		tB: \"tB:\" boolean (!True)					         			" 
        + "  		tA: \"tA:\" money (-1.25)					         			"
        + "  		tD: \"tA:\" money (-(-1.25))					         			"
        + "																								"
        + "	if (hasSoldHouse) {																			"
        + "		sellingPrice: \"Price the house was sold for:\" money										"
        + "		privateDebt: \"Private debts for the sold house:\" money									"
        + "  		valueResidue: \"Value residue:\" money (sellingPrice - privateDebt - 0.01 )			"
        + "  		testDate: \"Testdate:\" date (01/01/1970 + 3  )										"
        + "  		testString: \"testString:\" string (\"abc\"  + \"ABC\")								"
        + "  	}																						" + "}																								";

    Questionnaire questionnaire = new Questionnaire( testData );

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