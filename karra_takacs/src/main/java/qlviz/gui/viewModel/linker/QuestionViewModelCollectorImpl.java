package qlviz.gui.viewModel.linker;

import qlviz.gui.viewModel.FormViewModel;
import qlviz.gui.viewModel.question.*;
import qlviz.model.Form;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewModelCollectorImpl implements QuestionViewModelCollector, QuestionViewModelVisitor {


    private List<BooleanQuestionViewModel> booleanAccumulator;
    private List<NumericQuestionViewModel> numericAccumulator;


    public List<NumericQuestionViewModel> collectNumericQuestionViewModels(FormViewModel form) {
        this.booleanAccumulator = new ArrayList<>();
        this.numericAccumulator = new ArrayList<>();
        for (QuestionViewModel question : form.getQuestions()) {
            question.accept(this);
        }
        return this.numericAccumulator;
    }

   public List<BooleanQuestionViewModel> collectBooleanQuestionViewModels(FormViewModel form) {
        this.booleanAccumulator = new ArrayList<>();
        this.numericAccumulator = new ArrayList<>();
        for (QuestionViewModel question : form.getQuestions()) {
            question.accept(this);
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
