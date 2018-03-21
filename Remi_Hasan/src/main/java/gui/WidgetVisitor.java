package gui;

import javafx.scene.Node;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.model.DefaultStyle;

import java.util.List;

public interface WidgetVisitor<T> {

    Node visitWidgetTypeBoolean(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);
    Node visitWidgetTypeInteger(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);
    Node visitWidgetTypeString(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);
    Node visitWidgetTypeDecimal(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);
    Node visitWidgetTypeMoney(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);
    Node visitWidgetTypeDate(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);

    Node visitWidgetTypeIntegerSpinbox(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);
    Node visitWidgetTypeDecimalSpinbox(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);
    Node visitWidgetTypeMoneySpinbox(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);

    Node visitWidgetTypeIntegerSlider(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles, int min, int max, int step);
    Node visitWidgetTypeDecimalSlider(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles, double min, double max, double step);
    Node visitWidgetTypeMoneySlider(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles, double min, double max, double step);

    Node visitWidgetTypeBooleanRadio(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles, String falseLabel, String trueLabel);
    Node visitWidgetTypeBooleanCheckbox(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);
    Node visitWidgetTypeBooleanDropdown(SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles, String falseLabel, String trueLabel);
}
