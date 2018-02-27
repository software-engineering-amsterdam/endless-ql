/**
 * 
 */
package qlviz.interpreter;

import org.antlr.v4.runtime.tree.TerminalNode;
import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.QLVisitor;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;

import java.util.stream.Collectors;

/**
 * @author deepa
 *
 */
public class FormVisitor extends QLBaseVisitor<Form> {

    private final QLVisitor<QuestionBlock> questionBlockVisitor;

    public FormVisitor(QLVisitor<QuestionBlock> questionBlockVisitor) {

        this.questionBlockVisitor = questionBlockVisitor;
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        TerminalNode identifier = ctx.IDENTIFIER();
        String name = "";
        if (identifier != null) {
            name = identifier.getText();
        }
        return new Form(
          name,
          ctx.questionBlock()
                  .stream()
                  .map(this.questionBlockVisitor::visitQuestionBlock)
                  .collect(Collectors.toList())
        );
    }
}
