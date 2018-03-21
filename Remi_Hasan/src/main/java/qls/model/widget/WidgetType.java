package qls.model.widget;

import gui.WidgetVisitor;
import javafx.scene.Node;
import ql.analysis.SymbolTable;
import ql.model.Question;
import ql.model.expression.ReturnType;
import qls.model.DefaultStyle;

import java.util.List;

public enum WidgetType{

    // TODO: implement text, dropdown and slider
    SLIDER {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            switch(question.type){
                case INTEGER:
                    return visitor.visitWidgetTypeIntegerSlider(symbolTable, question, qlsQuestion, defaultStyles, 0, 42, 1);
                case DECIMAL:
                    return visitor.visitWidgetTypeDecimalSlider(symbolTable, question, qlsQuestion, defaultStyles, 0.0, 42.0, 1.0);
                case MONEY:
                    return visitor.visitWidgetTypeMoneySlider(symbolTable, question, qlsQuestion, defaultStyles, 0.0, 42.0, 1.0);
                default:
                    throw new UnsupportedOperationException("Unknown type for spinbox");
            }
        }
    },
    SPINBOX {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            // TODO cleanup switch?
            switch(question.type){
                case INTEGER:
                    return visitor.visitWidgetTypeIntegerSpinbox(symbolTable, question, qlsQuestion, defaultStyles);
                case DECIMAL:
                    return visitor.visitWidgetTypeDecimalSpinbox(symbolTable, question, qlsQuestion, defaultStyles);
                case MONEY:
                    return visitor.visitWidgetTypeMoneySpinbox(symbolTable, question, qlsQuestion, defaultStyles);
                default:
                    throw new UnsupportedOperationException("Unknown type for spinbox");
            }
        }
    },
    TEXTBOX {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            // TODO cleanup switch?
            switch(question.type){
                case INTEGER:
                    return visitor.visitWidgetTypeInteger(symbolTable, question, qlsQuestion, defaultStyles);
                case DECIMAL:
                    return visitor.visitWidgetTypeDecimal(symbolTable, question, qlsQuestion, defaultStyles);
                case MONEY:
                    return visitor.visitWidgetTypeMoney(symbolTable, question, qlsQuestion, defaultStyles);
                default:
                    throw new UnsupportedOperationException("Unknown type for textbox");
            }
        }
    },
    RADIO {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeBooleanRadio(symbolTable, question, qlsQuestion, defaultStyles, "replacethis", "replacethis");
        }
    }, CHECKBOX {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeBooleanCheckbox(symbolTable, question, qlsQuestion, defaultStyles);
//            return null;
        }
    }, DROPDOWN {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeBooleanDropdown(symbolTable, question, qlsQuestion, defaultStyles, "replacethis", "replacethis");
//            return null;
        }
    },
    INTEGER {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeInteger(symbolTable, question, qlsQuestion, defaultStyles);
        }
    }, DECIMAL {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeDecimal(symbolTable, question, qlsQuestion, defaultStyles);
        }
    }, MONEY {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeMoney(symbolTable, question, qlsQuestion, defaultStyles);
        }
    }, NUMBER{
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return null;
        }
    }
    , BOOLEAN {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeBoolean(symbolTable, question, qlsQuestion, defaultStyles);
        }
    }, STRING {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeString(symbolTable, question, qlsQuestion, defaultStyles);
        }
    }, DATE {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeDate(symbolTable, question, qlsQuestion, defaultStyles);
        }
    };

    public boolean isCompatible(ReturnType returnType) {
        switch (returnType) {
            case BOOLEAN:
                return this == CHECKBOX || this == DROPDOWN || this == RADIO;
            case STRING:
                return this == TEXTBOX;
            case INTEGER:
            case NUMBER:
            case DECIMAL:
            case MONEY:
                return this == SLIDER || this == SPINBOX || this == TEXTBOX;
            case DATE:
                // Date widget cannot be set, should always be date picker
                return false;
        }

        return false;
    }

    public abstract Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, qls.model.Question qlsQuestion, List<DefaultStyle> defaultStyles);
}
