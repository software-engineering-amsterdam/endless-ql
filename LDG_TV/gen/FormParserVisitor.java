// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/src/main/antlr4\FormParser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormParserParser#form_builder}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm_builder(FormParserParser.Form_builderContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormParserParser#form_data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm_data(FormParserParser.Form_dataContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormParserParser#question_structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_structure(FormParserParser.Question_structureContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormParserParser#if_structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_structure(FormParserParser.If_structureContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormParserParser#stat_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_block(FormParserParser.Stat_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormParserParser#question_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_identifier(FormParserParser.Question_identifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormParserParser#question_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_variable(FormParserParser.Question_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormParserParser#question_answer_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_answer_type(FormParserParser.Question_answer_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormParserParser#question_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_answer(FormParserParser.Question_answerContext ctx);
}