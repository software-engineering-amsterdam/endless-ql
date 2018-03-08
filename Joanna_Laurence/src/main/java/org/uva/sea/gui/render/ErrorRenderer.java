package org.uva.sea.gui.render;

public class ErrorRenderer implements Renderable<String> {

    private JavafxRendererVisitor render;

    public ErrorRenderer(JavafxRendererVisitor javafxRendererVisitor) {
        this.render = javafxRendererVisitor;
    }

    @Override
    public void render(String error) {
        render.displayError(error);
    }
}
