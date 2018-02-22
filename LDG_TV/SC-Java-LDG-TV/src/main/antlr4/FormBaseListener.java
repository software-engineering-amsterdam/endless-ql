// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/antlr4\Form.g4 by ANTLR 4.7

import nodes.FormNode;
import nodes.Question;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * This class provides an empty implementation of {@link FormListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
public class FormBaseListener implements FormListener {

	public FormNode formNode;
	public FormBaseListener(FormNode formNode){
		this.formNode = formNode;
	}
	public void enterForm_builder(FormParser.Form_builderContext ctx) {
		this.formNode.setFormIdentifier(ctx.CHARACTERS().getText());
	}
	public void exitForm_builder(FormParser.Form_builderContext ctx) { }

	public void enterForm_data(FormParser.Form_dataContext ctx) {
 		for (FormParser.Question_structureContext qsc : ctx.question_structure()){
			this.formNode.getFormData().addPlainQuestion(new Question(qsc.question_label().getText(), qsc.question_variable().getText(), qsc.question_answer_type().getText()));
		}

	}
	public void exitForm_data(FormParser.Form_dataContext ctx) { }

	public void enterIf_structure(FormParser.If_structureContext ctx) {
		this.formNode.getFormData().addQuestionVariableToConditionQuestions(ctx.statement_block_structure().question_variable().getText());
		for (FormParser.Question_structureContext qsc : ctx.question_structure()){
			this.formNode.getFormData().addConditionQuestion(ctx.statement_block_structure().question_variable().getText(), new Question(qsc.question_label().getText(), qsc.question_variable().getText(), qsc.question_answer_type().getText()));
		}
	}

	public void exitIf_structure(FormParser.If_structureContext ctx) { }

	public void enterQuestion_structure(FormParser.Question_structureContext ctx) {
	}
	public void exitQuestion_structure(FormParser.Question_structureContext ctx) {

	}
	public void enterStatement_block_structure(FormParser.Statement_block_structureContext ctx) { }
	public void exitStatement_block_structure(FormParser.Statement_block_structureContext ctx) { }

	public void enterQuestion_label(FormParser.Question_labelContext ctx) { }
	public void exitQuestion_label(FormParser.Question_labelContext ctx) { }

	public void enterQuestion_variable(FormParser.Question_variableContext ctx) { }
	public void exitQuestion_variable(FormParser.Question_variableContext ctx) { }

	public void enterQuestion_answer_type(FormParser.Question_answer_typeContext ctx) { }
	public void exitQuestion_answer_type(FormParser.Question_answer_typeContext ctx) { }

	public void enterQuestion_answer(FormParser.Question_answerContext ctx) { }
	public void exitQuestion_answer(FormParser.Question_answerContext ctx) { }

	public void enterEveryRule(ParserRuleContext ctx) { }
	public void exitEveryRule(ParserRuleContext ctx) { }

	public void visitTerminal(TerminalNode node) { }
	public void visitErrorNode(ErrorNode node) { }
}