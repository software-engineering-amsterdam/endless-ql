package org.uva.sea.gui.render2;

import org.uva.sea.gui.renderer.JavafxRendererVisitor;

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
