package org.uva.sea.gui.render;

public class ErrorRenderer implements Renderable<String> {

    private final ViewRenderer render;

    public ErrorRenderer(ViewRenderer javafxRendererVisitor) {
        this.render = javafxRendererVisitor;
    }

    @Override
    public void render(String error) {
        this.render.displayError(error);
    }
}
