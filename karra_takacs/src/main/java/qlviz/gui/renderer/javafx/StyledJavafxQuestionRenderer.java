package qlviz.gui.renderer.javafx;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.gui.renderer.javafx.widgets.UIWidget;
import qlviz.gui.renderer.layout.QuestionNotFoundException;
import qlviz.gui.viewModel.question.*;
import qlviz.model.style.Widget;

public class StyledJavafxQuestionRenderer implements QuestionRenderer {

    private final Pane target;
    private final JavafxWidgetFactory javafxWidgetFactory;
    private final WidgetFinder widgetFinder;

    public StyledJavafxQuestionRenderer(Pane target, JavafxWidgetFactory javafxWidgetFactory, WidgetFinder widgetFinder) {
        this.target = target;
        this.javafxWidgetFactory = javafxWidgetFactory;
        this.widgetFinder = widgetFinder;
    }

    private void renderErrorBox() {
        // TODO
        // We should add an indicator to the UI that shows if there's an error rendering.
    }

    @Override
    public void render(QuestionViewModel question) {
        try {
            Widget widgetDefinition = this.widgetFinder.findWidgetForQuestion(question);
            UIWidget widget = this.javafxWidgetFactory.create(widgetDefinition);
            widget.bindToQuestion(question);

            VBox container = new VBox();

            Label label = new Label(question.getText());

            container.getChildren().add(label);
            container.getChildren().add(widget.getNode());

            container.visibleProperty().bindBidirectional(question.isEnabledProperty());
            target.getChildren().add(container);

        } catch (QuestionNotFoundException e) {
        } catch (WidgetNotFoundException e) {
        }
    }
}
