package qlviz.gui.renderer;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import qlviz.typecheker.AnalysisResult;

import java.util.List;

public class JavafxErrorRenderer implements ErrorRenderer {

    private final Stage target;

    public JavafxErrorRenderer(Stage target) {
        this.target = target;
    }

    @Override
    public void render(List<AnalysisResult> analysisResults) {
        HBox pane = new HBox();
        Scene scene = new Scene(pane);
        ListView<AnalysisResult> resultListView = new ListView<>();
        resultListView.setItems(FXCollections.observableArrayList(analysisResults));
        pane.getChildren().add(resultListView);
        target.setScene(scene);
        target.show();
    }
}
