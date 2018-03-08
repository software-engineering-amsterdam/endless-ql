package org.uva.sea.gui.render;

public class WarningRenderer implements Renderable<String> {

    private JavafxRendererVisitor render;

    public WarningRenderer(JavafxRendererVisitor javafxRendererVisitor) {
        this.render = javafxRendererVisitor;
    }

    @Override
    public void render(String warning) {
        render.displayWarning(warning);
    }
}
