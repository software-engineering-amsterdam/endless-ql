//package gui.model;
//
//import gui.widgets.GUIWidget;
//import gui.widgets.WidgetFactory;
//import javafx.beans.value.ChangeListener;
//import javafx.scene.control.Label;
//import ql.analysis.SymbolTable;
//import ql.evaluation.ExpressionEvaluator;
//import ql.model.expression.Expression;
//import ql.model.expression.ReturnType;
//
//public class GUIQuestion implements GUIInterface{
//    public final String identifier;
//    public final String label;
//    public final ReturnType type;
//    private final Expression condition;
//    private final boolean computed;
//    public final Expression computedAnswer;
//
//    public GUIQuestion(String identifier, String label, ReturnType type, Expression condition, boolean computed, Expression computedAnswer) {
//        this.identifier = identifier; this.computed = computed;
//        this.label = label;
//        this.type = type;
//        this.condition = condition;
//        this.computed = computed;
//        this.computedAnswer = computedAnswer;
//
//        Label guiLabel = new Label(this.label);
//        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(this.type);
//    }
//
//    public GUILabelWithWidget render(SymbolTable symbolTable, changeListener allWidgetsListener) {
//        Label guiLabel = new Label(this.label);
//        GUIWidget guiWidget = WidgetFactory.getDefaultWidget(this.type);
//
//        // Event handling
//        if(!this.computed) {
//            guiWidget.setChangeListener(observable -> {
//                symbolTable.setExpression(this.identifier, guiWidget.getExpressionValue());
//
//                // Notify GUIForm that an input value has changed, so it can update all fields
//                allWidgetsListener.invalidated(observable);
//            });
//        }
//
//        GUILabelWithWidget labelWidget = new GUILabelWithWidget(guiLabel, guiWidget);
//        labelWidget.setDisable(this.isComputed());
//        return labelWidget;
//    }
//
//    public boolean isVisible(SymbolTable symbolTable) {
//        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);
//        return expressionEvaluator.visit(this.condition).getBooleanValue();
//    }
//
//    public boolean isComputed() {
//        return computed;
//    }
//}
