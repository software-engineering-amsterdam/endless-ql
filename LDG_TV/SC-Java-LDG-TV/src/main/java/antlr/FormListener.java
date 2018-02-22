package antlr;// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/antlr4\Form.g4 by ANTLR 4.7
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
}