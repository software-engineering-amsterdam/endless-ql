package nl.uva.js.qlparser.models.qls.enums;

import nl.uva.js.qlparser.models.ql.enums.DataType;

import java.util.*;

import static nl.uva.js.qlparser.models.ql.enums.DataType.*;

public enum WidgetType {
    CHECKBOX,
    RADIO,
    DROPDOWN,
    TEXT,
    SLIDER,
    SPINBOX;

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
}
