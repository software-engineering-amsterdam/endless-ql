
import static org.junit.Assert.assertEquals;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

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
				"  		valueResidue: \"Value residue:\" money (sellingPrice - privateDebt - 0.01)						"+
				"  	}																							"+
				"}	THIS IS IGNORED																							";

		ParseTree parseTree = AbstractParserFactory.parseDataForTest(testData).form();

		interpretingVisitor.visit(parseTree);

		assertEquals("Number of answerableQuestions seen", 3, interpretingVisitor.answerableQuestions.size() );
		assertEquals("Number of computedQuestions seen", 0, interpretingVisitor.computedQuestions.size() );
		
		// simulate answers given

		interpretingVisitor.answerableQuestions.get("_hasSoldHouse").parseAndSetValue("True") ;
		interpretingVisitor.visit(parseTree);

		assertEquals("Number of answerableQuestions seen", 5, interpretingVisitor.answerableQuestions.size() );
		assertEquals("Number of computedQuestions seen", 1, interpretingVisitor.computedQuestions.size() );

		interpretingVisitor.answerableQuestions.get("sellingPrice").parseAndSetValue( "100.03" );
		interpretingVisitor.answerableQuestions.get("privateDebt").parseAndSetValue(   "25.00" );

		interpretingVisitor.visit(parseTree);

		assertEquals("Number of answerableQuestions seen", 5, interpretingVisitor.answerableQuestions.size() );
		assertEquals("Number of computedQuestions seen", 1, interpretingVisitor.computedQuestions.size() );

		assertEquals("Calculated answer", 750200, interpretingVisitor.computedQuestions.get("valueResidue").getValue() );

	}
	
	@Test
	public void test_Operators() throws Exception {

		InterpretingVisitor interpretingVisitor = new InterpretingVisitor();

		String s = "";
		
		s = "form x { x: \"x:\" integer (4+5)   }";
		ParseTree parseTree = AbstractParserFactory.parseDataForTest(s).form();
		interpretingVisitor.visit(parseTree);
		assertEquals("x",9, interpretingVisitor.computedQuestions.get("x").getValue() );

		s = "form x { x: \"x:\" integer (True || False)   }";
		ParseTree parseTree2 = AbstractParserFactory.parseDataForTest(s).form();
		interpretingVisitor.visit(parseTree2);
		assertEquals("x",1, interpretingVisitor.computedQuestions.get("x").getValue() );

	}
}