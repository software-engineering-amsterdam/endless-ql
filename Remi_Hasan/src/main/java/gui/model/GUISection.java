package gui.model;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.evaluation.SymbolTable;
import qls.model.DefaultStyle;
import qls.model.QuestionReference;
import qls.model.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUISection extends VBox{

    private final String identifier;
    private final List<GUIQuestion> guiQuestions;
    private final List<DefaultStyle> defaultStyles;
    private final List<QuestionReference> questionReferences;
    private final List<Section> sections;

    GUISection(String identifier, List<GUIQuestion> guiQuestions, List<DefaultStyle> defaultStyles, List<DefaultStyle> outerScopeDefaultStyles, List<QuestionReference> questionReferences, List<Section> sections){
        this.identifier = identifier;
        this.guiQuestions = guiQuestions;
        this.defaultStyles = new ArrayList<>();
        this.defaultStyles.addAll(outerScopeDefaultStyles);
        this.defaultStyles.addAll(defaultStyles);
        this.questionReferences = questionReferences;
        this.sections = sections;
    }

    public Parent render(SymbolTable symbolTable){
        VBox vBox = new VBox();
        Label pageLabel = new Label("Section " + identifier);
        vBox.getChildren().add(pageLabel);

//        // Render all sections
//        for(Section section : sections){
//            // Combine local styles with broader scope styles
//            GUISection guiSection = new GUISection(section.identifier, guiQuestions, defaultStyles, section.getDefaultStyles(), section.getQuestionReferences(), section.getSections());
//            vBox.getChildren().add(guiSection.render(symbolTable));
//        }

        for(QuestionReference questionReference : questionReferences){
            Optional<GUIQuestion> guiQuestion = guiQuestions.stream().filter(x -> x.identifier.equals(questionReference.name)).findFirst();
            GUIQuestionWithStyling questionWithStyling = new GUIQuestionWithStyling(guiQuestion.get(), defaultStyles);
            vBox.getChildren().add(questionWithStyling.render(symbolTable));
        }

        vBox.setPadding(new Insets(0, 0, 0, 20));

        return vBox;
    }
}
