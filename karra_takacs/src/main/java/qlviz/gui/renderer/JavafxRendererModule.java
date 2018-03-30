package qlviz.gui.renderer;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import javafx.stage.Stage;
import qlviz.gui.renderer.javafx.JavafxFormRenderer;
import qlviz.gui.renderer.javafx.JavafxQuestionRenderer;
import qlviz.gui.renderer.javafx.QuestionRendererFactory;

public class JavafxRendererModule extends AbstractModule {

    private final Stage stage;

    public JavafxRendererModule(Stage stage) {
        this.stage = stage;
    }

    @Override
    protected void configure() {
        bind(FormRenderer.class).to(JavafxFormRenderer.class);
        install(new FactoryModuleBuilder()
            .implement(QuestionRenderer.class, JavafxQuestionRenderer.class)
            .build(QuestionRendererFactory.class));
    }
}
