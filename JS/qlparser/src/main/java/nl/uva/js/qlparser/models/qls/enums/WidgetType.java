package nl.uva.js.qlparser.models.qls.enums;

import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.expressions.data.Variable;
import nl.uva.js.qlparser.models.qls.style.WidgetStyle;
import nl.uva.js.qlparser.ui.components.form.ComponentBuilder;

import java.awt.*;
import java.util.*;

import static nl.uva.js.qlparser.models.ql.enums.DataType.*;

public enum WidgetType {
    CHECKBOX {
        @Override
        public Component createWidget(Variable variable, WidgetStyle widgetStyle) {
            return ComponentBuilder.buildCheckBox(variable);
        }
    },
    RADIO {
        @Override
        public Component createWidget(Variable variable, WidgetStyle widgetStyle) {
            return ComponentBuilder.buildRadioButtons(variable);
        }
    },
    DROPDOWN {
        @Override
        public Component createWidget(Variable variable, WidgetStyle widgetStyle) {
            return ComponentBuilder.buildDropdown(variable);
        }
    },
    TEXT {
        @Override
        public Component createWidget(Variable variable, WidgetStyle widgetStyle) {
            return ComponentBuilder.buildTextField(variable);
        }
    },
    SLIDER {
        @Override
        public Component createWidget(Variable variable, WidgetStyle widgetStyle) {
            return ComponentBuilder.buildSlider(variable, widgetStyle);
        }
    },
    SPINBOX {
        @Override
        public Component createWidget(Variable variable, WidgetStyle widgetStyle) {
            return ComponentBuilder.buildSpinbox(variable, widgetStyle);
        }
    };

    public static Map<DataType, Set<WidgetType>> mapDataTypeToWidget;
    static {
        mapDataTypeToWidget = new HashMap<>();

        HashSet<WidgetType> booleanWidgets = new HashSet<>(Arrays.asList(CHECKBOX, RADIO, DROPDOWN));
        HashSet<WidgetType> integerWidgets = new HashSet<>(Arrays.asList(TEXT, SLIDER, SPINBOX));
        HashSet<WidgetType> dateWidgets = new HashSet<>(Collections.singletonList(TEXT));
        HashSet<WidgetType> decimalWidgets = new HashSet<>(Collections.singletonList(TEXT));
        HashSet<WidgetType> moneyWidgets = new HashSet<>(Collections.singletonList(TEXT));
        HashSet<WidgetType> stringWidgets = new HashSet<>(Collections.singletonList(TEXT));

        mapDataTypeToWidget.put(BOOLEAN, booleanWidgets);
        mapDataTypeToWidget.put(INTEGER, integerWidgets);
        mapDataTypeToWidget.put(DATE, dateWidgets);
        mapDataTypeToWidget.put(DECIMAL, decimalWidgets);
        mapDataTypeToWidget.put(MONEY, moneyWidgets);
        mapDataTypeToWidget.put(STRING, stringWidgets);
    }

    public abstract Component createWidget(Variable variable, WidgetStyle widgetStyle);
}
