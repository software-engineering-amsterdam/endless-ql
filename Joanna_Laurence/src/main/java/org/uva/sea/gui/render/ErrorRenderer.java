package org.uva.sea.gui.render;

public class ErrorRenderer implements Renderable<String> {

    private ViewRenderer render;

    public ErrorRenderer(ViewRenderer javafxRendererVisitor) {
        this.render = javafxRendererVisitor;
    }

    @Override
    public void render(String error) {
        render.displayError(error);
    }
}
