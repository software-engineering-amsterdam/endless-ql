/**
 * 
 */
package qlviz.interpreter;

import qlviz.QLBaseListener;
import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.QLVisitor;
import qlviz.model.Form;
import qlviz.model.Question;
import qlviz.model.QuestionType;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author deepa
 *
 */
public class FormVisitor extends QLBaseVisitor<Form> {

    private final QLVisitor<Question> questionVisitor;

    public FormVisitor(QLVisitor<Question> questionVisitor) {

        this.questionVisitor = questionVisitor;
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        return new Form(
          ctx.question()
                  .stream()
                  .map(this.questionVisitor::visitQuestion)
                  .collect(Collectors.toList())
        );
    }
}
