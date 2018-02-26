package qlviz.interpreter;

import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.question.NumericQuestion;
import qlviz.model.QuestionBlock;
import qlviz.model.question.*;

import java.util.ArrayList;
import java.util.List;

public class TypedQuestionWalker implements TypedQuestionCollector, VoidQuestionVisitor {

    private List<BooleanQuestion> accumulator;

    @Override
    public List<NumericQuestion> collectNumericQuestions(Form form) {
        return null;
    }

    private void collectBooleanQuestions(QuestionBlock block)
    {
        for (Question question : block.getQuestions()) {
            question.accept(this);
        }

        for (ConditionalBlock conditionalBlock : block.getBlocks()) {
            for (QuestionBlock innerBlock : conditionalBlock.getQuestionBlocks()) {
                this.collectBooleanQuestions(innerBlock);
            }
        }
    }

    @Override
    public List<BooleanQuestion> collectBooleanQuestions(Form form) {
        this.accumulator = new ArrayList<>();
        for (QuestionBlock block : form.getQuestions()) {
           this.collectBooleanQuestions(block);
        }
        return this.accumulator;
    }

    @Override
    public void visit(BooleanQuestion booleanQuestion) {
        this.accumulator.add(booleanQuestion);
    }

    @Override
    public void visit(DateQuestion dateQuestion) {

    }

    @Override
    public void visit(DecimalQuestion decimalQuestion) {

    }

    @Override
    public void visit(IntegerQuestion integerQuestion) {

    }

    @Override
    public void visit(MoneyQuestion moneyQuestion) {

    }

    @Override
    public void visit(StringQuestion stringQuestion) {

    }
}
