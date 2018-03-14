package qlviz.gui.renderer.javafx;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qlviz.gui.renderer.QuestionRenderer;
import qlviz.gui.renderer.layout.QuestionLocator;
import qlviz.gui.viewModel.FormViewModel;
import qlviz.gui.viewModel.question.QuestionViewModel;
import qlviz.model.style.Page;
import qlviz.model.style.Stylesheet;

import java.util.function.Function;
import java.util.stream.Collectors;

public class StyledJavafxFormRenderer extends JavafxFormRenderer {

    private final Stylesheet stylesheet;
    private final QuestionLocator questionLocator;

    public StyledJavafxFormRenderer(
            Stage stage,
            Function<Pane, QuestionRenderer> questionRendererFactory,
            Stylesheet stylesheet,
            QuestionLocator questionLocator) {
        super(stage, questionRendererFactory);
        this.stylesheet = stylesheet;
        this.questionLocator = questionLocator;
    }

    private void render(TabPane target, Page page, FormViewModel form) {
        Tab tab = new Tab();
        tab.setText(page.getName());

        VBox container = new VBox();
        QuestionRenderer renderer = questionRendererFactory.apply(container);
        for (QuestionViewModel question : form.getQuestions()
                .stream()
                .filter(q -> this.questionLocator.getPage(q).map(p -> p.equals(page)).orElse(false))
                .collect(Collectors.toList()))
        {
            renderer.render(question);
        }
        tab.setContent(container);
        target.getTabs().add(tab);
    }

    @Override
    public void render(FormViewModel form) {
        stage.setTitle(form.getTitle());

        TabPane formFieldsContainer = new TabPane();

        for (Page page : stylesheet.getPages()) {
            this.render(formFieldsContainer, page, form);
        }

        Scene scene = new Scene(formFieldsContainer, 550, 250);
        stage.setScene(scene);

        stage.show();
    }
}
