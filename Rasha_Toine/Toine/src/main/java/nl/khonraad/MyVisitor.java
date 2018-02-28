package nl.khonraad;

import java.util.HashMap;
import java.util.Map;

public class MyVisitor extends ExpressionLanguageBaseVisitor<Integer> {

	public Map<String, Integer> variables = new HashMap<String, Integer>();
	public Map<String,Question> questions = new HashMap<String,Question>();

	@Override
	public Integer visitBlock(ExpressionLanguageParser.BlockContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Integer visitForm(ExpressionLanguageParser.FormContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Integer visitLBL_Expression_AddSub_Expression(
			ExpressionLanguageParser.LBL_Expression_AddSub_ExpressionContext ctx) {
		int left = visit(ctx.expression(0));
		int right = visit(ctx.expression(1));

		if (ctx.operator.getType() == ExpressionLanguageParser.OPERATOR_ADD) {
			return left + right;
		}
		return left - right;
	}

	@Override
	public Integer visitLBL_Expression_MulDiv_Expression(
			ExpressionLanguageParser.LBL_Expression_MulDiv_ExpressionContext ctx) {
		int left = visit(ctx.expression(0));
		int right = visit(ctx.expression(1));
		if (ctx.operator.getType() == ExpressionLanguageParser.OPERATOR_MUL) {
			return left * right;
		}
		return left / right;
	}

	@Override
	public Integer visitLBL_Id_Expression(ExpressionLanguageParser.LBL_Id_ExpressionContext ctx) {

		String id = ctx.ID().getText();

		if (variables.containsKey(id)) {
			return variables.get(id);
		}
		return 0;

	}

	@Override
	public Integer visitLBL_Integer_Expression(ExpressionLanguageParser.LBL_Integer_ExpressionContext ctx) {
		return Integer.valueOf(ctx.INTEGER().getText());
	}

	@Override
	public Integer visitLBL_LParen_Expression_RParen(ExpressionLanguageParser.LBL_LParen_Expression_RParenContext ctx) {
		return visitChildren(ctx.expression());
	}

	@Override
	public Integer visitLBL_Min_Expression(ExpressionLanguageParser.LBL_Min_ExpressionContext ctx) {
		int value = visit(ctx.expression());
		return -value;
	}

	@Override
	public Integer visitLBL_Not_Expression(ExpressionLanguageParser.LBL_Not_ExpressionContext ctx) {
		int value = visit(ctx.expression());
		if (value != 0)
			return 0;
		return 1;

	}

	@Override
	public Integer visitLBL_Question(ExpressionLanguageParser.LBL_QuestionContext ctx) {

		Question question = new Question(ctx.variable.getText(), ctx.label.getText(), ctx.type.getText() );
		questions.put(ctx.variable.getText(), question);
		return visitChildren(ctx);
	}

	@Override
	public Integer visitLBL_ComputedQuestion(ExpressionLanguageParser.LBL_ComputedQuestionContext ctx)  {
		
		Question question = new Question(ctx.variable.getText(), ctx.label.getText(), ctx.type.getText() );
		question.setValue(visit(ctx.expression()).toString());
		questions.put(ctx.variable.getText(), question);
		return visitChildren(ctx);
	}

	@Override
	public Integer visitLBL_ConditionalBlock(ExpressionLanguageParser.LBL_ConditionalBlockContext ctx) {
		return visitChildren(ctx);
	}

}
