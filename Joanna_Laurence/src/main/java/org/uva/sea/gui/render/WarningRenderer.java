package org.uva.sea.gui.render;

public class WarningRenderer implements Renderable<String> {

    private ViewRenderer render;

    public WarningRenderer(ViewRenderer javafxRendererVisitor) {
        this.render = javafxRendererVisitor;
    }

    @Override
    public void render(String warning) {
        render.displayWarning(warning);
    }
}
