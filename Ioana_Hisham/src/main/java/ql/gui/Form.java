package ql.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ql.ASTBuilder;
import ql.ast.statements.Question;
import ql.evaluator.Evaluator;
import ql.gui.controls.ControlVisitor;
import ql.gui.controls.QLControl;
import ql.values.Value;

import java.io.IOException;
import java.io.InputStream;

public class Form extends Application {
    private final ControlVisitor controlVisitor = new ControlVisitor();
    private final Evaluator evaluator = new Evaluator();
    private static ql.ast.Form form;
    private GridPane formPane;
    private int row = 0;

    public static void main(String[] args) throws IOException {
        createForm();
        launch();
    }

    @Override
    public void start(Stage stage) {
        initializeForm(stage);
        stage.show();
    }

    public static void createForm() throws IOException {
        ASTBuilder astBuilder = new ASTBuilder();
        InputStream input = Form.class.getResourceAsStream("/form.ql");
        form = astBuilder.build(input);
        //todo add typechecker
        //todo print errors/warnings
        //visitor.visit(parseTree);
    }

    public void initializeForm(Stage stage) {
        this.formPane(stage);
        this.reset();
        evaluator.visit(form);

        for (Question question: evaluator.questions()) {
            this.add(controlVisitor.createQLControl(question), question);
        }
    }

    private GridPane formPane(Stage stage) {
        stage.setTitle(form.getIdentifier().toString());
        stage.setResizable(true);
        formPane = new GridPane();
        formPane.setVgap(5);
        formPane.setHgap(5);
        formPane.setAlignment(Pos.TOP_LEFT);
        stage.setScene(new Scene(formPane));
        return formPane;
    }

    private void reset() {
        row = 0;
        formPane.getChildren().clear();
    }

    private void add(QLControl qlControl, Question question) {
        if (evaluator.valueTable().exists(question.getIdentifier())) {
            qlControl.setValue(evaluator.valueTable().find(question.getIdentifier()));
        }
        formPane.add(qlControl.gridPane(), 0, row++);
    }
}
