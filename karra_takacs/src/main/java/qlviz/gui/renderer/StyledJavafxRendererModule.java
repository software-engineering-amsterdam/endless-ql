package qlviz.gui.renderer;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import javafx.stage.Stage;
import qlviz.gui.renderer.javafx.*;
import qlviz.gui.renderer.layout.NaiveQuestionLocator;
import qlviz.gui.renderer.layout.QuestionLocator;
import qlviz.model.style.Stylesheet;

import java.util.List;

public class StyledJavafxRendererModule extends AbstractModule {

    private final Stylesheet stylesheet;
    private final Stage stage;

    public StyledJavafxRendererModule(Stylesheet stylesheet, Stage stage) {

        this.stylesheet = stylesheet;
        this.stage = stage;
    }

    @Override
    protected void configure() {
        bind(WidgetFinder.class).toInstance(new ChainedWidgetFinder(List.of(
					new StylesheetWidgetFinder(new NaiveQuestionLocator(stylesheet)),
					new DefaultWidgetProvider()
			)));
        install(new FactoryModuleBuilder()
            .implement(QuestionRenderer.class, StyledJavafxQuestionRenderer.class)
            .build(QuestionRendererFactory.class));
        install(new FactoryModuleBuilder()
            .implement(StyledSectionRenderer.class, StyledJavafxSectionRenderer.class)
            .build(SectionRendererFactory.class));
        bind(FormRenderer.class).to(StyledJavafxFormRenderer.class);
        bind(Stage.class).toInstance(stage);
        bind(Stylesheet.class).toInstance(stylesheet);
        bind(QuestionLocator.class).to(NaiveQuestionLocator.class);
    }
}

