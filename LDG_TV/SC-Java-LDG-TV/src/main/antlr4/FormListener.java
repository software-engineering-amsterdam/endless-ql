// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/antlr4\Form.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormParser}.
 */
public interface FormListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormParser#form_builder}.
	 * @param ctx the parse tree
	 */
	void enterForm_builder(FormParser.Form_builderContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#form_builder}.
	 * @param ctx the parse tree
	 */
	void exitForm_builder(FormParser.Form_builderContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#form_data}.
	 * @param ctx the parse tree
	 */
	void enterForm_data(FormParser.Form_dataContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#form_data}.
	 * @param ctx the parse tree
	 */
	void exitForm_data(FormParser.Form_dataContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#question_structure}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_structure(FormParser.Question_structureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#question_structure}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_structure(FormParser.Question_structureContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#if_structure}.
	 * @param ctx the parse tree
	 */
	void enterIf_structure(FormParser.If_structureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#if_structure}.
	 * @param ctx the parse tree
	 */
	void exitIf_structure(FormParser.If_structureContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#statement_block_structure}.
	 * @param ctx the parse tree
	 */
	void enterStatement_block_structure(FormParser.Statement_block_structureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#statement_block_structure}.
	 * @param ctx the parse tree
	 */
	void exitStatement_block_structure(FormParser.Statement_block_structureContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#question_label}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_label(FormParser.Question_labelContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#question_label}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_label(FormParser.Question_labelContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#question_variable}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_variable(FormParser.Question_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#question_variable}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_variable(FormParser.Question_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#question_answer_type}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_answer_type(FormParser.Question_answer_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#question_answer_type}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_answer_type(FormParser.Question_answer_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormParser#question_answer}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_answer(FormParser.Question_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormParser#question_answer}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_answer(FormParser.Question_answerContext ctx);
}