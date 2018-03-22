package qlviz.gui.viewModel.linker;

import qlviz.gui.viewModel.FormViewModel;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModel;
import qlviz.gui.viewModel.question.BooleanQuestionViewModel;
import qlviz.gui.viewModel.question.NumericQuestionViewModel;
import qlviz.gui.viewModel.question.QuestionViewModel;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class QuestionViewModelLinkerImpl implements QuestionViewModelLinker {

    private final QuestionViewModelCollector typedQuestionCollector;


    public QuestionViewModelLinkerImpl(QuestionViewModelCollector typedQuestionCollector) {
        this.typedQuestionCollector = typedQuestionCollector;
    }



    @Override
    public void linkQuestionStubs(FormViewModel form) {
        List<BooleanQuestionViewModel> booleanQuestions = this.typedQuestionCollector.collectBooleanQuestionViewModels(form);
        List<NumericQuestionViewModel> numericQuestions = this.typedQuestionCollector.collectNumericQuestionViewModels(form);

        NumericExpressionViewModelLinker numericLinker = new NumericExpressionViewModelLinker(
            numericQuestions
                        .stream()
                        .collect(Collectors.toMap(NumericQuestionViewModel::getName, Function.identity())));
        BooleanExpressionViewModelLinker booleanLinker = new BooleanExpressionViewModelLinker(
            booleanQuestions
                        .stream()
                        .collect(Collectors.toMap(BooleanQuestionViewModel::getName, Function.identity())),
            numericLinker);

        for (QuestionViewModel question : form.getQuestions()) {
            for (BooleanExpressionViewModel expression : question.getEnabledConditions()) {
                expression.accept(booleanLinker);
            }
        }

        for (NumericQuestionViewModel numericQuestion : numericQuestions) {
            if (numericQuestion.getValueExpression() != null) {
                numericQuestion.getValueExpression().accept(numericLinker);
            }
        }
    }
}
