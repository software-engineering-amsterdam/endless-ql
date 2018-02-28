package qlviz.gui.viewModel.linker;

import qlviz.gui.viewModel.ConditionalBlockViewModel;
import qlviz.gui.viewModel.FormViewModel;
import qlviz.gui.viewModel.QuestionBlockViewModel;
import qlviz.gui.viewModel.question.BooleanQuestionViewModel;
import qlviz.gui.viewModel.question.NumericQuestionViewModel;
import qlviz.interpreter.TypedQuestionCollector;
import qlviz.interpreter.linker.BooleanExpressionLinker;
import qlviz.interpreter.linker.NumericExpressionLinker;
import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.question.BooleanQuestion;
import qlviz.model.question.NumericQuestion;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class QuestionViewModelLinkerImpl implements QuestionViewModelLinker {

    private final QuestionViewModelCollector typedQuestionCollector;


    public QuestionViewModelLinkerImpl(QuestionViewModelCollector typedQuestionCollector) {
        this.typedQuestionCollector = typedQuestionCollector;
    }



    private void linkQuestionsInBlock(QuestionBlockViewModel questionBlock, BooleanExpressionViewModelLinker booleanExpressionLinker) {
        for (ConditionalBlockViewModel conditionalBlock : questionBlock.getBlocks()) {
            conditionalBlock.getCondition().accept(booleanExpressionLinker);
            for (QuestionBlockViewModel innerBlock : conditionalBlock.getQuestionBlocks()) {
                this.linkQuestionsInBlock(innerBlock, booleanExpressionLinker);
            }
        }
    }

    @Override
    public void linkQuestionStubs(FormViewModel form) {
        List<BooleanQuestionViewModel> booleanQuestions = this.typedQuestionCollector.collectBooleanQuestions(form);
        List<NumericQuestionViewModel> numericQuestions = this.typedQuestionCollector.collectNumericQuestions(form);

        NumericExpressionViewModelLinker numericLinker = new NumericExpressionViewModelLinker(
            numericQuestions
                        .stream()
                        .collect(Collectors.toMap(NumericQuestionViewModel::getName, Function.identity())));
        BooleanExpressionViewModelLinker booleanLinker = new BooleanExpressionViewModelLinker(
            booleanQuestions
                        .stream()
                        .collect(Collectors.toMap(BooleanQuestionViewModel::getName, Function.identity())),
            numericLinker);

        for (QuestionBlockViewModel block : form.getQuestions()) {
            this.linkQuestionsInBlock(block, booleanLinker);
        }

        for (NumericQuestionViewModel numericQuestion : numericQuestions) {
            if (numericQuestion.getValueExpression() != null) {
                numericQuestion.getValueExpression().accept(numericLinker);
            }
        }
    }
}
