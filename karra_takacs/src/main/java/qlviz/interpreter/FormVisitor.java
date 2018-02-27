/**
 * 
 */
package qlviz.interpreter;

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
        return new Form(
          ctx.IDENTIFIER().getText(),
          ctx.questionBlock()
                  .stream()
                  .map(this.questionBlockVisitor::visitQuestionBlock)
                  .collect(Collectors.toList())
        );
    }
}
