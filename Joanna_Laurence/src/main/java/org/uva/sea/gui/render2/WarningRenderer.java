package org.uva.sea.gui.render2;

import org.uva.sea.gui.renderer.JavafxRendererVisitor;

import java.util.List;

public class WarningRenderer implements Renderable<List<String>> {

    private JavafxRendererVisitor render;

    public WarningRenderer(JavafxRendererVisitor javafxRendererVisitor) {
        this.render = javafxRendererVisitor;
    }

    @Override
    public void render(List<String> data) {
        render.displayWarnings(data);
    }
}
