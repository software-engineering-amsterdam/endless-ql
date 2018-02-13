/**
 * 
 */
package qlviz;

/**
 * @author deepa
 *
 */
public class TaxFormBaseListener extends qlBaseListener{
	@Override public void enterQuestionName(qlParser.QuestionNameContext ctx) {
		//to test if this method is called..sop to be removed later.
		System.out.println(ctx.getText());
	}
}
