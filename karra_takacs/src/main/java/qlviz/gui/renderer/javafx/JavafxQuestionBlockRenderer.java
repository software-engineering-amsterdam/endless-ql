package qlviz.gui.renderer.javafx;

import javafx.scene.layout.Pane;
import qlviz.gui.renderer.QuestionBlockRenderer;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.model.ConditionalBlock;
import qlviz.model.Question;
import qlviz.model.QuestionBlock;

import java.util.function.Function;

public class JavafxQuestionBlockRenderer implements QuestionBlockRenderer {

    private final QuestionRenderer questionRenderer;

    public JavafxQuestionBlockRenderer(Pane target, Function<Pane, QuestionRenderer> questionRendererFactory) {
        this.questionRenderer = questionRendererFactory.apply(target);
    }

    @Override
    public void render(QuestionBlock questionBlock) {
        for (Question question : questionBlock.getQuestions()) {
            questionRenderer.render(question);
        }
        for (ConditionalBlock block : questionBlock.getBlocks()) {
            if (block.getCondition().evaluate()) {
                for (QuestionBlock innerBlock : block.getQuestionBlocks()) {
                    this.render(innerBlock);
                }
            }
        }
    }
}
