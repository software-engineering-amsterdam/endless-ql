// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr/ql\Form.g4 by ANTLR 4.7
package antlr.ql;
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
	 * Enter a parse tree produced by {@link FormParser#questionNodeStructure}.
	 * @param ctx the parse tree
	 */
	void enterQuestionNodeStructure(FormParser.QuestionNodeStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#questionNodeStructure}.
	 * @param ctx the parse tree
	 */
	void exitQuestionNodeStructure(FormParser.QuestionNodeStructureContext ctx);
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
	 * Enter a parse tree produced by {@link FormParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(FormParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(FormParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(FormParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(FormParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(FormParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(FormParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#variableType}.
	 * @param ctx the parse tree
	 */
	void enterVariableType(FormParser.VariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#variableType}.
	 * @param ctx the parse tree
	 */
	void exitVariableType(FormParser.VariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#variableValue}.
	 * @param ctx the parse tree
	 */
	void enterVariableValue(FormParser.VariableValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#variableValue}.
	 * @param ctx the parse tree
	 */
	void exitVariableValue(FormParser.VariableValueContext ctx);
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
	 * Enter a parse tree produced by {@link FormParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryBooleanExpression(FormParser.UnaryBooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryBooleanExpression(FormParser.UnaryBooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#gtExpression}.
	 * @param ctx the parse tree
	 */
	void enterGtExpression(FormParser.GtExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#gtExpression}.
	 * @param ctx the parse tree
	 */
	void exitGtExpression(FormParser.GtExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#gteoqExpression}.
	 * @param ctx the parse tree
	 */
	void enterGteoqExpression(FormParser.GteoqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#gteoqExpression}.
	 * @param ctx the parse tree
	 */
	void exitGteoqExpression(FormParser.GteoqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#stExpression}.
	 * @param ctx the parse tree
	 */
	void enterStExpression(FormParser.StExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#stExpression}.
	 * @param ctx the parse tree
	 */
	void exitStExpression(FormParser.StExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#stoeqExpression}.
	 * @param ctx the parse tree
	 */
	void enterStoeqExpression(FormParser.StoeqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#stoeqExpression}.
	 * @param ctx the parse tree
	 */
	void exitStoeqExpression(FormParser.StoeqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#eqExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqExpression(FormParser.EqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#eqExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqExpression(FormParser.EqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#neqExpression}.
	 * @param ctx the parse tree
	 */
	void enterNeqExpression(FormParser.NeqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#neqExpression}.
	 * @param ctx the parse tree
	 */
	void exitNeqExpression(FormParser.NeqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpression(FormParser.ArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpression(FormParser.ArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#mulExpression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpression(FormParser.MulExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#mulExpression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpression(FormParser.MulExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#addExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(FormParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#addExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(FormParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#minExpression}.
	 * @param ctx the parse tree
	 */
	void enterMinExpression(FormParser.MinExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#minExpression}.
	 * @param ctx the parse tree
	 */
	void exitMinExpression(FormParser.MinExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#divExpression}.
	 * @param ctx the parse tree
	 */
	void enterDivExpression(FormParser.DivExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#divExpression}.
	 * @param ctx the parse tree
	 */
	void exitDivExpression(FormParser.DivExpressionContext ctx);
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
	 * Enter a parse tree produced by {@link FormParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOperator(FormParser.BooleanOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOperator(FormParser.BooleanOperatorContext ctx);
}