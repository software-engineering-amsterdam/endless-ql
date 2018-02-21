// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/src/main/antlr4\FormParser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormParserParser}.
 */
public interface FormParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormParserParser#form_builder}.
	 * @param ctx the parse tree
	 */
	void enterForm_builder(FormParserParser.Form_builderContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParserParser#form_builder}.
	 * @param ctx the parse tree
	 */
	void exitForm_builder(FormParserParser.Form_builderContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParserParser#form_data}.
	 * @param ctx the parse tree
	 */
	void enterForm_data(FormParserParser.Form_dataContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParserParser#form_data}.
	 * @param ctx the parse tree
	 */
	void exitForm_data(FormParserParser.Form_dataContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParserParser#question_structure}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_structure(FormParserParser.Question_structureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParserParser#question_structure}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_structure(FormParserParser.Question_structureContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParserParser#if_structure}.
	 * @param ctx the parse tree
	 */
	void enterIf_structure(FormParserParser.If_structureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParserParser#if_structure}.
	 * @param ctx the parse tree
	 */
	void exitIf_structure(FormParserParser.If_structureContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParserParser#stat_block}.
	 * @param ctx the parse tree
	 */
	void enterStat_block(FormParserParser.Stat_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParserParser#stat_block}.
	 * @param ctx the parse tree
	 */
	void exitStat_block(FormParserParser.Stat_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParserParser#question_identifier}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_identifier(FormParserParser.Question_identifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParserParser#question_identifier}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_identifier(FormParserParser.Question_identifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParserParser#question_variable}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_variable(FormParserParser.Question_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParserParser#question_variable}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_variable(FormParserParser.Question_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParserParser#question_answer_type}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_answer_type(FormParserParser.Question_answer_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParserParser#question_answer_type}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_answer_type(FormParserParser.Question_answer_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParserParser#question_answer}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_answer(FormParserParser.Question_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParserParser#question_answer}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_answer(FormParserParser.Question_answerContext ctx);
}