package qlviz.gui.renderer.javafx;

import javafx.scene.layout.Pane;
import qlviz.gui.renderer.QuestionBlockRenderer;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.gui.viewModel.ConditionalBlockViewModel;
import qlviz.gui.viewModel.QuestionBlockViewModel;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.ConditionalBlock;
import qlviz.model.question.Question;
import qlviz.model.QuestionBlock;

import java.util.function.Function;

public class JavafxQuestionBlockRenderer implements QuestionBlockRenderer {

    private final QuestionRenderer questionRenderer;

    public JavafxQuestionBlockRenderer(Pane target, Function<Pane, QuestionRenderer> questionRendererFactory) {
        this.questionRenderer = questionRendererFactory.apply(target);
    }

    @Override
    public void render(QuestionBlockViewModel questionBlock) {
        for (QuestionViewModel question : questionBlock.getQuestions()) {
            questionRenderer.render(question);
        }
        for (ConditionalBlockViewModel block : questionBlock.getBlocks()) {
            if (block.getCondition().evaluate()) {
                for (QuestionBlockViewModel innerBlock : block.getQuestionBlocks()) {
                    this.render(innerBlock);
                }
            }
        }
    }
}
