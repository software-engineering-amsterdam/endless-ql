package qlviz.gui.renderer.javafx;

import javafx.scene.layout.Pane;
import qlviz.gui.renderer.ConditionalBlockRenderer;
import qlviz.gui.renderer.QuestionBlockRenderer;
import qlviz.gui.viewModel.ConditionalBlockViewModel;
import qlviz.gui.viewModel.QuestionBlockViewModel;
import qlviz.model.ConditionalBlock;

import java.util.function.BiFunction;
import java.util.function.Function;


public class JavafxConditionalBlockRenderer implements ConditionalBlockRenderer {

    private final Pane target;
    private final QuestionBlockRenderer questionBlockRenderer;

    public JavafxConditionalBlockRenderer(Pane target,
                                          BiFunction<Pane, ConditionalBlockRenderer, QuestionBlockRenderer> questionBlockRendererFactory) {
        this.target = target;
        this.questionBlockRenderer = questionBlockRendererFactory.apply(this.target, this);
    }

    @Override
    public void render(ConditionalBlockViewModel conditionalBlock) {
        for (QuestionBlockViewModel questionBlockViewModel : conditionalBlock.getQuestionBlocks()) {
            questionBlockRenderer.render(questionBlockViewModel);
        }
        this.target.visibleProperty().bindBidirectional(conditionalBlock.getCondition().valueProperty());
    }
}
