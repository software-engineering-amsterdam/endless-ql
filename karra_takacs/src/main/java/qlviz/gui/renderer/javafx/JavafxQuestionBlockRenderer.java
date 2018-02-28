package qlviz.gui.renderer.javafx;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import qlviz.gui.renderer.ConditionalBlockRenderer;
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
    private final Function<Pane, ConditionalBlockRenderer> blockRendererFactory;
    private final Pane target;

    public JavafxQuestionBlockRenderer(Pane target,
                                       Function<Pane, QuestionRenderer> questionRendererFactory,
                                       Function<Pane, ConditionalBlockRenderer> blockRendererFactory) {
        this.blockRendererFactory = blockRendererFactory;
        this.questionRenderer = questionRendererFactory.apply(target);
        this.target = target;
    }

    @Override
    public void render(QuestionBlockViewModel questionBlock) {
        for (QuestionViewModel question : questionBlock.getQuestions()) {
            questionRenderer.render(question);
        }
        for (ConditionalBlockViewModel block : questionBlock.getBlocks()) {
            VBox subBlock = new VBox();
            ConditionalBlockRenderer renderer = blockRendererFactory.apply(subBlock);
            renderer.render(block);
            target.getChildren().add(subBlock);
        }
    }
}
