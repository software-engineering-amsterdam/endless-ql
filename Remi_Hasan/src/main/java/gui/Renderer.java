package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import ql.QLFormBuilder;
import ql.analysis.SymbolTable;
import ql.model.Form;
import ql.model.Question;
import qls.StyleSheetParser;
import qls.model.StyleSheet;

import java.io.File;
import java.io.FileInputStream;

public class Renderer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        File qlFile = new File(getClass().getResource("../java/example.ql").getFile());
        File qlsFile = new File(getClass().getResource("../java/example.qls").getFile());

        QLFormBuilder qlFormBuilder = new QLFormBuilder();
        Form qlForm = qlFormBuilder.buildForm(new FileInputStream(qlFile));
        SymbolTable symbolTable = qlFormBuilder.getSymbolTable();

        StyleSheet qlsStyleSheet = StyleSheetParser.parseStyleSheet(new FileInputStream(qlsFile));

        for(Question q : qlForm.questions) {
            System.out.println(q.name);
        }

        primaryStage.show();
    }
}
