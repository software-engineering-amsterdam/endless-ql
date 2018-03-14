package gui;

import gui.widgets.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class DraftRenderer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox();
        pane.setSpacing(20);
        pane.setPadding(new Insets(20, 20, 20, 20));

        // Boolean radio widget
        RadioWidget radioWidget = new RadioWidget("boolean_radio", List.of("false", "true"));
        pane.getChildren().add(radioWidget.getUI());

        // Boolean dropdown widget
        DropdownWidget dropdownWidget = new DropdownWidget("boolean_dropdown", List.of("false", "true"));
        pane.getChildren().add(dropdownWidget.getUI());

        // Boolean checkbox widget
        CheckboxWidget checkboxWidget = new CheckboxWidget("boolean_checkbox");
        pane.getChildren().add(checkboxWidget.getUI());

        // Integer widget
        IntegerWidget integerWidget = new IntegerWidget("integer");
        pane.getChildren().add(integerWidget.getUI());

        // Double widget
        DoubleWidget doubleWidget = new DoubleWidget("double");
        pane.getChildren().add(doubleWidget.getUI());

        // String widget
        StringWidget stringWidget = new StringWidget("string");
        pane.getChildren().add(stringWidget.getUI());

        // Money widget
        MoneyWidget moneyWidget = new MoneyWidget("money");
        pane.getChildren().add(moneyWidget.getUI());

        // Date widget
        DateWidget dateWidget = new DateWidget("date");
        pane.getChildren().add(dateWidget.getUI());

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}