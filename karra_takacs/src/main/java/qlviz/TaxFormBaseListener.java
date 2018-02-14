/**
 * 
 */
package qlviz;

/**
 * @author deepa
 *
 */
public class TaxFormBaseListener extends QLBaseListener{
	@Override public void enterQuestionName(QLParser.QuestionNameContext ctx) {
		//to test if this method is called..sop to be removed later.
		System.out.println(ctx.getText());
	}
}
