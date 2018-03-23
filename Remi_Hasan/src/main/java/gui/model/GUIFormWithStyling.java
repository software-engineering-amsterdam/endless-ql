//package gui.model;
//
//import javafx.scene.Parent;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.VBox;
//import ql.analysis.SymbolTable;
//import qls.model.StyleSheet;
//
//import java.util.List;
//
//public class GUIFormWithStyling extends ScrollPane implements GUIInterface {
//
//    public GUIFormWithStyling(String identifier, List<GUIInterface> children) {
//        this.managedProperty().bind(this.visibleProperty());
//    }
//
//    @Override
//    public void update(SymbolTable symbolTable) {
//
//    }
//
//    @Override
//    public void update(StyleSheet styleSheet) {
//
//    }
//
//    @Override
//    public Parent render() {
//        return null;
//    }
//
//
////    @Override
////    public Parent render(SymbolTable symbolTable) {
//////        Parent parent = super.render(symbolTable);
////
////        VBox vBox = new VBox();
////        // Style questions
////        for (Page page : stylesheet.getPages()) {
////            GUIPage guiPage = new GUIPage(page.identifier, guiQuestions, page.getSections(), page.getDefaultStyles());
////            vBox.getChildren().add(guiPage.render(symbolTable));
////        }
////
////        return vBox;
////    }
//}
