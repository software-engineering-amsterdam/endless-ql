package qlviz.gui.renderer.javafx;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.gui.viewModel.question.QuestionViewModel;

import java.util.List;
import java.util.function.Function;

public class StyledJavafxSectionRenderer implements SectionRenderer {

    private final Pane target;
    private final QuestionRenderer questionRenderer;

    public StyledJavafxSectionRenderer(
            Function<Pane, QuestionRenderer> questionRendererFactory,
            Pane target) {
        this.target = target;
        this.questionRenderer = questionRendererFactory.apply(target);
    }

    public void render(List<QuestionViewModel> questions) {
        for (QuestionViewModel question : questions)
        {
            questionRenderer.render(question);
        }
    }
}
