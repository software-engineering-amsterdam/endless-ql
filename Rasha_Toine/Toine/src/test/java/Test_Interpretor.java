
import static org.junit.Assert.assertEquals;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import nl.khonraad.QLVisitor;
import nl.khonraad.domain.Question.BehaviouralType;
import nl.khonraad.domain.Type;
import nl.khonraad.domain.Value;
import nl.khonraad.utils.AbstractParseTreeFactory;

public class Test_Interpretor {

    @Test
    public void test_Interpretor() throws Exception {

        QLVisitor interpretingVisitor = new QLVisitor();

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

        ParseTree parseTree = AbstractParseTreeFactory.parseDataForTest( testData ).form();

        interpretingVisitor.visit( parseTree );

        // simulate answers given

        interpretingVisitor.form.findQuestion( BehaviouralType.ANSWERABLE, "hasSoldHouse" ).get()
                .parseThenSetValue( "True" );

        interpretingVisitor.visit( parseTree );
        interpretingVisitor.form.findQuestion( BehaviouralType.ANSWERABLE, "sellingPrice" ).get()
                .parseThenSetValue( "100.03" );
        interpretingVisitor.form.findQuestion( BehaviouralType.ANSWERABLE, "privateDebt" ).get()
                .parseThenSetValue( "25.00" );

        interpretingVisitor.visit( parseTree );

        assertEquals( "Calculated answer", new Value( Type.Money, "75.02" ),

                interpretingVisitor.form.findQuestion( BehaviouralType.COMPUTED, "valueResidue" ).get().getValue()

        );
        assertEquals( "Calculated answer", new Value( Type.Date, "04/01/1970" ),
                interpretingVisitor.form.findQuestion( BehaviouralType.COMPUTED, "testDate" ).get().getValue() );
        assertEquals( "Calculated answer", new Value( Type.String, "abcABC" ),
                interpretingVisitor.form.findQuestion( BehaviouralType.COMPUTED, "testString" ).get().getValue() );
    }

    @Test
    public void test_Operators() throws Exception {

        QLVisitor interpretingVisitor = new QLVisitor();

        String s = "";

        s = "form x { x: \"x:\" integer (4+5)   }";

        ParseTree parseTree = AbstractParseTreeFactory.parseDataForTest( s ).form();
        interpretingVisitor.visit( parseTree );
        assertEquals( "x", new Value( Type.Integer, "9" ),
                interpretingVisitor.form.findQuestion( BehaviouralType.COMPUTED, "x" ).get().getValue() );

        s = "form x { x: \"x:\" boolean (True || False)   }";
        ParseTree parseTree2 = AbstractParseTreeFactory.parseDataForTest( s ).form();
        interpretingVisitor.visit( parseTree2 );
        assertEquals( "x", new Value( Type.Boolean, "True" ),
                interpretingVisitor.form.findQuestion( BehaviouralType.COMPUTED, "x" ).get().getValue() );

        s = "form x { x: \"x:\" boolean (True == False)   }";
        ParseTree parseTree3 = AbstractParseTreeFactory.parseDataForTest( s ).form();
        interpretingVisitor.visit( parseTree3 );
        assertEquals( "x", new Value( Type.Boolean, "False" ),
                interpretingVisitor.form.findQuestion( BehaviouralType.COMPUTED, "x" ).get().getValue() );

        s = "form x { x: \"x:\" boolean (2.50 >= (5.50 - 3.00 * 1) )   }";
        ParseTree parseTree4 = AbstractParseTreeFactory.parseDataForTest( s ).form();
        interpretingVisitor.visit( parseTree4 );
        assertEquals( "x", new Value( Type.Boolean, "True" ),
                interpretingVisitor.form.findQuestion( BehaviouralType.COMPUTED, "x" ).get().getValue() );

    }

}