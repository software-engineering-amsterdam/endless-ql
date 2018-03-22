package gui;

import gui.widgets.WidgetInterface;
import ql.analysis.SymbolTable;
import ql.model.Question;
import qls.model.DefaultStyle;

import java.util.List;

public interface WidgetVisitor<T> {

    WidgetInterface visitWidgetTypeBoolean(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
    WidgetInterface visitWidgetTypeInteger(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
    WidgetInterface visitWidgetTypeString(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
    WidgetInterface visitWidgetTypeDecimal(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
    WidgetInterface visitWidgetTypeMoney(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
    WidgetInterface visitWidgetTypeDate(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);

    WidgetInterface visitWidgetTypeIntegerSpinBox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
    WidgetInterface visitWidgetTypeDecimalSpinBox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
    WidgetInterface visitWidgetTypeMoneySpinBox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);

    WidgetInterface visitWidgetTypeIntegerSlider(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, int min, int max, int step);
    WidgetInterface visitWidgetTypeDecimalSlider(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, double min, double max, double step);
    WidgetInterface visitWidgetTypeMoneySlider(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, double min, double max, double step);

    WidgetInterface visitWidgetTypeBooleanRadio(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, String falseLabel, String trueLabel);
    WidgetInterface visitWidgetTypeBooleanCheckbox(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
    WidgetInterface visitWidgetTypeBooleanDropdown(SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles, String falseLabel, String trueLabel);
}
