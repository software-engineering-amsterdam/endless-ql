
import static org.junit.Assert.assertEquals;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import nl.khonraad.visitors.ValuationVisitor;
import utils.StaticUtils;

public class ValuationVisitorTest {

	@Test
	public void testValuation() throws Exception {

		ValuationVisitor valuationVisitor = new ValuationVisitor();

		String testData =
				"form Box1HouseOwning {																			"+
				"   hasSoldHouse: \"Did you sell a house in 2010?\" boolean										"+
				"   hasBoughtHouse: \"Did you by a house in 2010?\" boolean										"+
				"   hasMaintLoan: \"Did you enter a loan for maintenance/reconstruction?\"  boolean				"+
				"																								"+
				"	if (hasSoldHouse) {																			"+
				"		sellingPrice: \"Price the house was sold for:\" money										"+
				"		privateDebt: \"Private debts for the sold house:\" money									"+
				"  		valueResidue: \"Value residue:\" money (sellingPrice - privateDebt)						"+
				"  	}																							"+
				"}																								";

		ParseTree parseTree = StaticUtils.parseDataForTest(testData).form();

		valuationVisitor.visit(parseTree);

		assertEquals("Number of questions seen", 6, valuationVisitor.questions.size() );

		
		// simulate answers given

		valuationVisitor.questions.get("sellingPrice").setValue("100");
		valuationVisitor.questions.get("privateDebt").setValue("25");

		valuationVisitor.visit(parseTree);

		assertEquals("Calculated answer", 75, valuationVisitor.questions.get("valueResidue").getValue() );

	}
}