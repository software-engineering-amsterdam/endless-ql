// Generated from C:/Users/Michael/Desktop/Software Construction/endless-ql/GunOli/src/main/antlr\QL.g4 by ANTLR 4.7
package antlrGen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(QLParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(QLParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(QLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(QLParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionType(QLParser.QuestionTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(QLParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedExpr(QLParser.NestedExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(QLParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpr(QLParser.ConstantExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(QLParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(QLParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#binaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOp(QLParser.BinaryOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerConstant(QLParser.IntegerConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decimalConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalConstant(QLParser.DecimalConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringConstant(QLParser.StringConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierConstant(QLParser.IdentifierConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneyConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneyConstant(QLParser.MoneyConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dateConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDateConstant(QLParser.DateConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(QLParser.TypeContext ctx);
}