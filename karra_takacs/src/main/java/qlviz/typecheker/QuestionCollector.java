package qlviz.typecheker;

import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.question.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionCollector {
    public static List<Question> collect(Form form) {
        List<Question> results = new ArrayList<>();
        for (QuestionBlock block : form.getQuestions()) {
            results.addAll(collect(block));
        }
        return results;
    }

    private static List<Question> collect(QuestionBlock block) {
        List<Question> result = new ArrayList<>();
        result.addAll(block.getQuestions());
        for (ConditionalBlock conditionalBlock : block.getBlocks()) {
           for (QuestionBlock questionBlock : conditionalBlock.getQuestionBlocks()) {
               result.addAll(collect(questionBlock));
           }
        }
        return result;
    }
}
