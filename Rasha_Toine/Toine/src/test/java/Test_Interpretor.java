
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
				"   hasSoldHouse: \"Did you sell a house in 2010?\" boolean										"+
				"   hasBoughtHouse: \"Did you by a house in 2010?\" boolean										"+
				"   hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\"  boolean				"+
				"																								"+
				"	if (hasSoldHouse) {																			"+
				"		sellingPrice: \"Price the house was sold for:\" money										"+
				"		privateDebt: \"Private debts for the sold house:\" money									"+
				"  		valueResidue: \"Value residue:\" money (sellingPrice - privateDebt )						"+
				"  	}																							"+
				"}																								";

		ParseTree parseTree = AbstractParserFactory.parseDataForTest(testData).form();

		interpretingVisitor.visit(parseTree);

		assertEquals("Number of questions seen", 3, interpretingVisitor.questions.size() );
		
		// simulate answers given

		interpretingVisitor.questions.get("hasSoldHouse").setValue("true");
		interpretingVisitor.visit(parseTree);

		assertEquals("Number of questions seen", 6, interpretingVisitor.questions.size() );

		interpretingVisitor.questions.get("sellingPrice").setValue("100");
		interpretingVisitor.questions.get("privateDebt").setValue("25");

		interpretingVisitor.visit(parseTree);

		assertEquals("Number of questions seen", 6, interpretingVisitor.questions.size() );
		assertEquals("Calculated answer", 75, interpretingVisitor.questions.get("valueResidue").getValue() );

	}
}