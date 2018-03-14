package gui;

import gui.widgets.DoubleWidget;
import gui.widgets.IntegerWidget;
import gui.widgets.MoneyWidget;
import gui.widgets.RadioWidget;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class DraftRenderer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox();
        pane.setSpacing(20);
        pane.setPadding(new Insets(20, 20, 20, 20));

        // Radio widget
        List<String> options = List.of("false", "true");
        RadioWidget radioWidget = new RadioWidget("dummyname", options);
        pane.getChildren().add(radioWidget.get());

        // Integer widget
        IntegerWidget integerWidget = new IntegerWidget("dummyname");
        pane.getChildren().add(integerWidget.get());

        // Double widget
        DoubleWidget doubleWidget = new DoubleWidget("dummyname");
        pane.getChildren().add(doubleWidget.get());

        // Money widget
        MoneyWidget moneyWidget = new MoneyWidget("dummyname");
        pane.getChildren().add(moneyWidget.get());

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}