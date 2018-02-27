package qlviz.interpreter.linker;

import qlviz.interpreter.TypedQuestionCollector;
import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.question.NumericQuestion;
import qlviz.model.QuestionBlock;
import qlviz.model.question.BooleanQuestion;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class QuestionLinkerImpl implements QuestionLinker {

    private final TypedQuestionCollector typedQuestionCollector;


    public QuestionLinkerImpl(TypedQuestionCollector typedQuestionCollector) {
        this.typedQuestionCollector = typedQuestionCollector;
    }



    private void linkQuestionsInBlock(QuestionBlock questionBlock, BooleanExpressionLinker booleanExpressionLinker) {
        for (ConditionalBlock conditionalBlock : questionBlock.getBlocks()) {
            conditionalBlock.getCondition().accept(booleanExpressionLinker);
            for (QuestionBlock innerBlock : conditionalBlock.getQuestionBlocks()) {
                this.linkQuestionsInBlock(innerBlock, booleanExpressionLinker);
            }
        }
    }

    @Override
    public void linkQuestionStubs(Form form) {
        List<BooleanQuestion> booleanQuestions = this.typedQuestionCollector.collectBooleanQuestions(form);
        List<NumericQuestion> numericQuestions = this.typedQuestionCollector.collectNumericQuestions(form);

        BooleanExpressionLinker booleanLinker = new BooleanExpressionLinker(
                booleanQuestions
                        .stream()
                        .collect(Collectors.toMap(BooleanQuestion::getName, Function.identity())));
        NumericExpressionLinker numericLinker = new NumericExpressionLinker(
                numericQuestions
                        .stream()
                        .collect(Collectors.toMap(NumericQuestion::getName, Function.identity())));

        for (NumericQuestion numericQuestion : numericQuestions) {
            numericQuestion.accept(numericLinker);
        }
        for (QuestionBlock block : form.getQuestions()) {
            this.linkQuestionsInBlock(block, booleanLinker);
        }
    }
}
