package org.uva.sea.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.model.QuestionModel;
import org.uva.sea.gui.components.GuiMessage;
import org.uva.sea.gui.components.Renderable;
import org.uva.sea.languages.BaseEvaluator;
import org.uva.sea.languages.QlEvaluator;
import org.uva.sea.languages.QlSEvaluator;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;


public class FormController implements Initializable, IGuiElementUpdateListener {

    private final QuestionModel formModel = new QuestionModel(this);

    private Renderer renderer = null;

    private final Collection<Renderable> componentsToDraw = new ArrayList<>();

    @FXML
    private VBox container;

    @FXML
    private TabPane tabPane;

    @FXML
    private VBox messages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.renderer = new Renderer(this.container, this.tabPane, this.messages);
    }

    private void addElementToDraw(Renderable element) {
        this.componentsToDraw.add(element);
    }

    private String getFileSelector(String title, String extension) {
        FileSelector fileSelector = new FileSelector(title, extension, "*." + extension);
        File selectedFile = fileSelector.getFile();
        if (selectedFile == null) {
            this.addElementToDraw(new GuiMessage("Warning: no file selected"));
            return null;
        }

        return selectedFile.getAbsolutePath();
    }

    @FXML
    public void loadQLFile(ActionEvent actionEvent) {
        this.componentsToDraw.clear();
        String qlFile = this.getFileSelector("Load QL file", "ql");
        if (qlFile != null) {
            BaseEvaluator evaluator = new QlEvaluator(qlFile);
            this.updateInterpreter(evaluator);
            this.collectComponentsToDraw();
        }
        this.drawComponents();
    }

    @FXML
    public void loadQLSFile(ActionEvent actionEvent) {
        this.componentsToDraw.clear();
        String qlFile = this.getFileSelector("Load QL file", "ql");
        String qlsFile = this.getFileSelector("Load QLS file", "qls");
        if ((qlFile != null) && (qlsFile != null)) {
            BaseEvaluator evaluator = new QlSEvaluator(qlFile, qlsFile);
            this.updateInterpreter(evaluator);
            this.collectComponentsToDraw();
        }
        this.drawComponents();
    }

    /**
     * Update the interpreter that is used
     * @param evaluator
     */
    private void updateInterpreter(BaseEvaluator evaluator) {
        this.formModel.setInterpreter(evaluator);
    }

    private void collectComponentsToDraw() {
        this.componentsToDraw.addAll(this.formModel.getQuestionRenders());
    }

    //TODO: Why not show warnigns and errors in a pop-up? Then we do not need all these function.
    /**
     * Redraw the GUI
     */
    private void drawComponents() {
        this.renderer.draw(this.componentsToDraw);
    }

    @FXML
    public void export(ActionEvent actionEvent) {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public void updateGuiVariable(String identifier, Value value) {
        this.formModel.setVariable(identifier, value);
        this.componentsToDraw.clear();
        this.collectComponentsToDraw();
        this.drawComponents();
    }
}
