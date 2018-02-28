package qlviz.gui.viewModel.linker;

import qlviz.gui.viewModel.ConditionalBlockViewModel;
import qlviz.gui.viewModel.FormViewModel;
import qlviz.gui.viewModel.QuestionBlockViewModel;
import qlviz.gui.viewModel.question.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionViewModelCollectorImpl implements QuestionViewModelCollector, QuestionViewModelVisitor {


    private List<BooleanQuestionViewModel> booleanAccumulator;
    private List<NumericQuestionViewModel> numericAccumulator;

    private void collectNumericQuestionViewModels(QuestionBlockViewModel block)
    {
        for (QuestionViewModel question : block.getQuestions()) {
            question.accept(this);
        }

        for (ConditionalBlockViewModel conditionalBlock : block.getBlocks()) {
            for (QuestionBlockViewModel innerBlock : conditionalBlock.getQuestionBlocks()) {
                this.collectBooleanQuestionViewModels(innerBlock);
            }
        }
    }


    public List<NumericQuestionViewModel> collectNumericQuestionViewModels(FormViewModel form) {
        this.booleanAccumulator = new ArrayList<>();
        this.numericAccumulator = new ArrayList<>();
        for (QuestionBlockViewModel block : form.getQuestions()) {
            this.collectNumericQuestionViewModels(block);
        }
        return this.numericAccumulator;
    }

    private void collectBooleanQuestionViewModels(QuestionBlockViewModel block)
    {
        for (QuestionViewModel question : block.getQuestions()) {
            question.accept(this);
        }

        for (ConditionalBlockViewModel conditionalBlock : block.getBlocks()) {
            for (QuestionBlockViewModel innerBlock : conditionalBlock.getQuestionBlocks()) {
                this.collectBooleanQuestionViewModels(innerBlock);
            }
        }
    }

    public List<BooleanQuestionViewModel> collectBooleanQuestionViewModels(FormViewModel form) {
        this.numericAccumulator = new ArrayList<>();
        this.booleanAccumulator = new ArrayList<>();
        for (QuestionBlockViewModel block : form.getQuestions()) {
           this.collectBooleanQuestionViewModels(block);
        }
        return this.booleanAccumulator;
    }

    @Override
    public void visit(BooleanQuestionViewModel booleanQuestionViewModel) {
        this.booleanAccumulator.add(booleanQuestionViewModel);
    }

    @Override
    public void visit(DateQuestionViewModel dateQuestionViewModel) {

    }

    @Override
    public void visit(DecimalQuestionViewModel decimalQuestionViewModel) {
        this.numericAccumulator.add(decimalQuestionViewModel);
    }

    @Override
    public void visit(IntegerQuestionViewModel integerQuestionViewModel) {
        this.numericAccumulator.add(integerQuestionViewModel);
    }

    @Override
    public void visit(MoneyQuestionViewModel moneyQuestionViewModel) {
        this.numericAccumulator.add(moneyQuestionViewModel);
    }

    @Override
    public void visit(StringQuestionViewModel stringQuestionViewModel) {

    }

}
