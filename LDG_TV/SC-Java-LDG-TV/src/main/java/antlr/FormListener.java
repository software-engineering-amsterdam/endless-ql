// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr\Form.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormParser}.
 */
public interface FormListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormParser#formBuilder}.
	 * @param ctx the parse tree
	 */
	void enterFormBuilder(FormParser.FormBuilderContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#formBuilder}.
	 * @param ctx the parse tree
	 */
	void exitFormBuilder(FormParser.FormBuilderContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#formData}.
	 * @param ctx the parse tree
	 */
	void enterFormData(FormParser.FormDataContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#formData}.
	 * @param ctx the parse tree
	 */
	void exitFormData(FormParser.FormDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#questionStructure}.
	 * @param ctx the parse tree
	 */
	void enterQuestionStructure(FormParser.QuestionStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#questionStructure}.
	 * @param ctx the parse tree
	 */
	void exitQuestionStructure(FormParser.QuestionStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#ifStructure}.
	 * @param ctx the parse tree
	 */
	void enterIfStructure(FormParser.IfStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#ifStructure}.
	 * @param ctx the parse tree
	 */
	void exitIfStructure(FormParser.IfStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#statementBlockStructure}.
	 * @param ctx the parse tree
	 */
	void enterStatementBlockStructure(FormParser.StatementBlockStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#statementBlockStructure}.
	 * @param ctx the parse tree
	 */
	void exitStatementBlockStructure(FormParser.StatementBlockStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#questionLabel}.
	 * @param ctx the parse tree
	 */
	void enterQuestionLabel(FormParser.QuestionLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#questionLabel}.
	 * @param ctx the parse tree
	 */
	void exitQuestionLabel(FormParser.QuestionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#questionVariable}.
	 * @param ctx the parse tree
	 */
	void enterQuestionVariable(FormParser.QuestionVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#questionVariable}.
	 * @param ctx the parse tree
	 */
	void exitQuestionVariable(FormParser.QuestionVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#questionVariableType}.
	 * @param ctx the parse tree
	 */
	void enterQuestionVariableType(FormParser.QuestionVariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#questionVariableType}.
	 * @param ctx the parse tree
	 */
	void exitQuestionVariableType(FormParser.QuestionVariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#questionVariableValue}.
	 * @param ctx the parse tree
	 */
	void enterQuestionVariableValue(FormParser.QuestionVariableValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#questionVariableValue}.
	 * @param ctx the parse tree
	 */
	void exitQuestionVariableValue(FormParser.QuestionVariableValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#plainValue}.
	 * @param ctx the parse tree
	 */
	void enterPlainValue(FormParser.PlainValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#plainValue}.
	 * @param ctx the parse tree
	 */
	void exitPlainValue(FormParser.PlainValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FormParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FormParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpression(FormParser.BooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpression(FormParser.BooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(FormParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(FormParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#conditions}.
	 * @param ctx the parse tree
	 */
	void enterConditions(FormParser.ConditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#conditions}.
	 * @param ctx the parse tree
	 */
	void exitConditions(FormParser.ConditionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(FormParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(FormParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOperator(FormParser.BooleanOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOperator(FormParser.BooleanOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(FormParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(FormParser.OperatorContext ctx);
}