package qlviz.gui.renderer.javafx;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.Section;

import java.util.List;
import java.util.function.Function;

public class StyledJavafxSectionRenderer implements StyledSectionRenderer {

    private final Pane target;
    private final QuestionRenderer questionRenderer;

    @Inject
    public StyledJavafxSectionRenderer(
            QuestionRendererFactory questionRendererFactory,
            @Assisted Pane target) {
        this.target = target;
        this.questionRenderer = questionRendererFactory.create(target);
    }

    public void render(List<QuestionViewModel> questions, Section section) {
        Text header = new Text(section.getName());
        header.setFont(new Font(24));
        target.getChildren().add(header);
        for (QuestionViewModel question : questions)
        {
            questionRenderer.render(question);
        }
    }
}
