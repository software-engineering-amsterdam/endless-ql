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
import qlviz.model.style.Section;
import qlviz.model.style.Stylesheet;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StyledJavafxFormRenderer extends JavafxFormRenderer {

    private final Stylesheet stylesheet;
    private final QuestionLocator questionLocator;
    private final Function<Pane, StyledSectionRenderer> sectionRendererFactory;

    public StyledJavafxFormRenderer(
            Stage stage,
            Function<Pane, QuestionRenderer> questionRendererFactory,
            Stylesheet stylesheet,
            QuestionLocator questionLocator, Function<Pane, StyledSectionRenderer> sectionRenderedFactory) {
        super(stage, questionRendererFactory);
        this.stylesheet = stylesheet;
        this.questionLocator = questionLocator;
        this.sectionRendererFactory = sectionRenderedFactory;
    }

    private void render(TabPane target, Page page, FormViewModel form) {
        Tab tab = new Tab();
        tab.setText(page.getName());

        VBox tabContent = new VBox();

        for (Section section : page.getSections()) {
            VBox container = new VBox();
            List<QuestionViewModel> questionsInSection =
                    form.getQuestions()
                        .stream()
                        .filter(questionViewModel ->
                            this.questionLocator.getSection(questionViewModel).map(s -> s.equals(section)).orElse(false))
                        .collect(Collectors.toList());

            StyledSectionRenderer sectionRenderer = this.sectionRendererFactory.apply(container);
            sectionRenderer.render(questionsInSection, section);

            tabContent.getChildren().add(container);
        }

        tab.setContent(tabContent);
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
