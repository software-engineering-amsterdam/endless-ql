
import static org.junit.Assert.assertEquals;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import nl.khonraad.domain.Type;
import nl.khonraad.domain.Value;
import nl.khonraad.visitors.InterpretingVisitor;
import utils.AbstractParserFactory;

public class Test_Interpretor {

	@Test
	public void test_Interpretor() throws Exception {

		InterpretingVisitor interpretingVisitor = new InterpretingVisitor();

		String testData =
				"form Box1HouseOwning {																			"+
				"   { _hasSoldHouse: \"Did you sell a house in 2010?\" boolean }									"+
				"   {{{{                                                                                          "+
				"     hasBoughtHouse: \"Did you by a house in 2010?\" boolean										"+
				"     hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\"  boolean				"+
				"	}}}}																							"+
				"	if (_hasSoldHouse) {																			"+
				"		sellingPrice: \"Price the house was sold for:\" money										"+
				"		privateDebt: \"Private debts for the sold house:\" money									"+
				"  		valueResidue: \"Value residue:\" money ((sellingPrice - privateDebt - 0.01))						"+
				"  	}																							"+
				"}	THIS IS IGNORED																							";

		ParseTree parseTree = AbstractParserFactory.parseDataForTest(testData).form();

		interpretingVisitor.visit(parseTree);

		// simulate answers given

		interpretingVisitor.answerableQuestions.get("_hasSoldHouse").parseThenSetValue("True") ;
		
		interpretingVisitor.visit(parseTree);

		interpretingVisitor.answerableQuestions.get("sellingPrice").parseThenSetValue( "100.03" );
		interpretingVisitor.answerableQuestions.get("privateDebt").parseThenSetValue(   "25.00" );

		interpretingVisitor.visit(parseTree);

		assertEquals("Calculated answer", new Value( Type.Money, 7502), interpretingVisitor.computedQuestions.get("valueResidue").getValue() );
	}
	
	@Test
	public void test_Operators() throws Exception {

		InterpretingVisitor interpretingVisitor = new InterpretingVisitor();

		String s = "";
		
		s = "form x { x: \"x:\" integer (4+5)   }";
		ParseTree parseTree = AbstractParserFactory.parseDataForTest(s).form();
		interpretingVisitor.visit(parseTree);
		assertEquals("x", new Value( Type.Integer, 9), interpretingVisitor.computedQuestions.get("x").getValue() );

		s = "form x { x: \"x:\" boolean (True || False)   }";
		ParseTree parseTree2 = AbstractParserFactory.parseDataForTest(s).form();
		interpretingVisitor.visit(parseTree2);
		assertEquals("x",new Value( Type.Boolean, 1), interpretingVisitor.computedQuestions.get("x").getValue() );

		s = "form x { x: \"x:\" boolean (True == False)   }";
		ParseTree parseTree3 = AbstractParserFactory.parseDataForTest(s).form();
		interpretingVisitor.visit(parseTree3);
		assertEquals("x",new Value( Type.Boolean, 0), interpretingVisitor.computedQuestions.get("x").getValue() );

		s = "form x { x: \"x:\" boolean (2.50 >= (5.50 - 3.00 * 1))   }";
		ParseTree parseTree4 = AbstractParserFactory.parseDataForTest(s).form();
		interpretingVisitor.visit(parseTree4);
		assertEquals("x",new Value( Type.Boolean, 4242), interpretingVisitor.computedQuestions.get("x").getValue() );
		
	}

}