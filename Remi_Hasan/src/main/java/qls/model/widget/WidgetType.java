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
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            switch(question.type){
                case INTEGER:
                    return visitor.visitWidgetTypeIntegerSlider(symbolTable, question, defaultStyles, 42, 42, 1);
                case DECIMAL:
                    return visitor.visitWidgetTypeDecimalSlider(symbolTable, question, defaultStyles, 42.0, 42.0, 42.0);
                case MONEY:
                    return visitor.visitWidgetTypeMoneySlider(symbolTable, question, defaultStyles, 42.0, 42.0, 42.0);
                default:
                    throw new UnsupportedOperationException("Unknown type for spinbox");
            }
        }
    },
    SPINBOX {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            // TODO cleanup switch?
            switch(question.type){
                case INTEGER:
                    return visitor.visitWidgetTypeIntegerSpinbox(symbolTable, question, defaultStyles);
                case DECIMAL:
                    return visitor.visitWidgetTypeDecimalSpinbox(symbolTable, question, defaultStyles);
                case MONEY:
                    return visitor.visitWidgetTypeMoneySpinbox(symbolTable, question, defaultStyles);
                default:
                    throw new UnsupportedOperationException("Unknown type for spinbox");
            }
        }
    },
    TEXTBOX {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            // TODO cleanup switch?
            switch(question.type){
                case INTEGER:
                    return visitor.visitWidgetTypeInteger(symbolTable, question, defaultStyles);
                case DECIMAL:
                    return visitor.visitWidgetTypeDecimal(symbolTable, question, defaultStyles);
                case MONEY:
                    return visitor.visitWidgetTypeMoney(symbolTable, question, defaultStyles);
                default:
                    throw new UnsupportedOperationException("Unknown type for textbox");
            }
        }
    },
    RADIO {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeBooleanRadio(symbolTable, question, defaultStyles, "replacethis", "replacethis");
        }
    }, CHECKBOX {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeBooleanCheckbox(symbolTable, question, defaultStyles);
//            return null;
        }
    }, DROPDOWN {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeBooleanDropdown(symbolTable, question, defaultStyles, "replacethis", "replacethis");
//            return null;
        }
    },
    INTEGER {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeInteger(symbolTable, question, defaultStyles);
        }
    }, DECIMAL {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeDecimal(symbolTable, question, defaultStyles);
        }
    }, MONEY {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeMoney(symbolTable, question, defaultStyles);
        }
    }, NUMBER{
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return null;
        }
    }
    , BOOLEAN {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeBoolean(symbolTable, question, defaultStyles);
        }
    }, STRING {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeString(symbolTable, question, defaultStyles);
        }
    }, DATE {
        @Override
        public Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles) {
            return visitor.visitWidgetTypeDate(symbolTable, question, defaultStyles);
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

    public abstract Node createWidget(WidgetVisitor<?> visitor, SymbolTable symbolTable, Question question, List<DefaultStyle> defaultStyles);
}
