package qlviz.gui.renderer.javafx;

import javafx.scene.layout.Pane;

public interface SectionRendererFactory {
    StyledSectionRenderer create(Pane pane);
}
