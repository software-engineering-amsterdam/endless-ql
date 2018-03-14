
import static org.junit.Assert.assertEquals;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import nl.khonraad.QLVisitor;
import nl.khonraad.domain.Question.BehaviouralType;
import nl.khonraad.domain.Type;
import nl.khonraad.domain.Value;
import nl.khonraad.utils.AbstractParserFactory;

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
				+ "  		valueResidue: \"Value residue:\" money (sellingPrice - privateDebt - 0.01 )				"
				+ "  		testDate: \"Testdate:\" date (01/01/1970 + 3 )				"
				+ "  		testString: \"testString:\" string (\"abc\"  + \"ABC\")				"
				+ "  	}																							"
				+ "}																								";

		ParseTree parseTree = AbstractParserFactory.parseDataForTest( testData ).form();

		interpretingVisitor.visit( parseTree );

		// simulate answers given

		interpretingVisitor.findQuestion( BehaviouralType.ANSWERABLE, "hasSoldHouse" ).get().parseThenSetValue( "True" );
		
		
		interpretingVisitor.visit( parseTree );
		interpretingVisitor.findQuestion( BehaviouralType.ANSWERABLE, "sellingPrice" ).get().parseThenSetValue( "100.03" );
		interpretingVisitor.findQuestion( BehaviouralType.ANSWERABLE, "privateDebt" ).get().parseThenSetValue( "25.00" );


		interpretingVisitor.visit( parseTree );

		assertEquals( "Calculated answer", new Value( Type.Money, "75.02" ),

				interpretingVisitor.findQuestion( BehaviouralType.COMPUTED, "valueResidue" ).get().getValue()

		);
		assertEquals( "Calculated answer", new Value( Type.Date, "04/01/1970" ),
				interpretingVisitor.findQuestion( BehaviouralType.COMPUTED, "testDate" ).get().getValue() );
		assertEquals( "Calculated answer", new Value( Type.String, "abcABC" ),
				interpretingVisitor.findQuestion( BehaviouralType.COMPUTED, "testString" ).get().getValue() );
	}

	@Test
	public void test_Operators() throws Exception {

		QLVisitor interpretingVisitor = new QLVisitor();

		String s = "";

		s = "form x { x: \"x:\" integer (4+5)   }";
		
		ParseTree parseTree = AbstractParserFactory.parseDataForTest( s ).form();
		interpretingVisitor.visit( parseTree );
		assertEquals( "x", new Value( Type.Integer, "9" ),
				interpretingVisitor.findQuestion( BehaviouralType.COMPUTED,"x" ).get().getValue() );

		s = "form x { x: \"x:\" boolean (True || False)   }";
		ParseTree parseTree2 = AbstractParserFactory.parseDataForTest( s ).form();
		interpretingVisitor.visit( parseTree2 );
		assertEquals( "x", new Value( Type.Boolean, "True" ),
				interpretingVisitor.findQuestion( BehaviouralType.COMPUTED,"x" ).get().getValue() );

		s = "form x { x: \"x:\" boolean (True == False)   }";
		ParseTree parseTree3 = AbstractParserFactory.parseDataForTest( s ).form();
		interpretingVisitor.visit( parseTree3 );
		assertEquals( "x", new Value( Type.Boolean, "False" ),
				interpretingVisitor.findQuestion( BehaviouralType.COMPUTED,"x" ).get().getValue() );

		s = "form x { x: \"x:\" boolean (2.50 >= (5.50 - 3.00 * 1) )   }";
		ParseTree parseTree4 = AbstractParserFactory.parseDataForTest( s ).form();
		interpretingVisitor.visit( parseTree4 );
		assertEquals( "x", new Value( Type.Boolean, "True" ),
				interpretingVisitor.findQuestion( BehaviouralType.COMPUTED,"x" ).get().getValue() );

	}

}