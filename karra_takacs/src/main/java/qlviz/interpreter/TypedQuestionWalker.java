package qlviz.interpreter;

import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.question.NumericQuestion;
import qlviz.model.QuestionBlock;
import qlviz.model.question.*;

import java.util.ArrayList;
import java.util.List;

public class TypedQuestionWalker implements TypedQuestionCollector, VoidQuestionVisitor {

    private List<BooleanQuestion> booleanAccumulator;
    private List<NumericQuestion> numericAccumulator;

    private void collectNumericQuestions(QuestionBlock block)
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
    public List<NumericQuestion> collectNumericQuestions(Form form) {
        this.booleanAccumulator = new ArrayList<>();
        this.numericAccumulator = new ArrayList<>();
        for (QuestionBlock block : form.getQuestions()) {
            this.collectNumericQuestions(block);
        }
        return this.numericAccumulator;
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
        this.numericAccumulator = new ArrayList<>();
        this.booleanAccumulator = new ArrayList<>();
        for (QuestionBlock block : form.getQuestions()) {
           this.collectBooleanQuestions(block);
        }
        return this.booleanAccumulator;
    }

    @Override
    public void visit(BooleanQuestion booleanQuestion) {
        this.booleanAccumulator.add(booleanQuestion);
    }

    @Override
    public void visit(DateQuestion dateQuestion) {

    }

    @Override
    public void visit(DecimalQuestion decimalQuestion) {
        this.numericAccumulator.add(decimalQuestion);
    }

    @Override
    public void visit(IntegerQuestion integerQuestion) {
        this.numericAccumulator.add(integerQuestion);
    }

    @Override
    public void visit(MoneyQuestion moneyQuestion) {
        this.numericAccumulator.add(moneyQuestion);
    }

    @Override
    public void visit(StringQuestion stringQuestion) {

    }
}
