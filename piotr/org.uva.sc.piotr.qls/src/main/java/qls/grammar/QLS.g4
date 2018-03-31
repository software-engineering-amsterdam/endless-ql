grammar QLS ;

stylesheet
    : 'stylesheet' name=IDENTIFIER BEGIN stylesheetElement* END
    ;

stylesheetElement
    : pageDefition | defaultTypeDefinition
;

pageDefition
    : 'page' title=IDENTIFIER BEGIN pageElement+ END
    ;

pageElement
    : questionDefinition
    | section
    | defaultTypeDefinition
    ;

section
    : 'section' title=STRING (BEGIN sectionElement+ END | questionDefinition)
    ;

sectionElement
    : questionDefinition
    | section
    | defaultTypeDefinition
    ;


questionDefinition
    : 'question' name=IDENTIFIER (widgetDefinition)?
    ;

defaultTypeDefinition
    : 'default' type=dataType (widgetDefinition | dataTypeDefinionBlock)
    ;

dataTypeDefinionBlock
    : BEGIN typeDefinitionProperty* END
    ;

typeDefinitionProperty
    : 'width' ':' width=INTEGER
    | 'font' ':' font=STRING
    | 'fontsize' ':' fontSize=INTEGER
    | 'color' ':' color=HEX_VALUE
    | widgetDefinition
    ;

dataType
    : TYPE_BOOLEAN          #TypeDeclarationBoolean
    | TYPE_STRING           #TypeDeclarationString
    | TYPE_INTEGER          #TypeDeclarationInteger
    | TYPE_DECIMAL          #TypeDeclarationDecimal
    | TYPE_MONEY            #TypeDeclarationMoney
    | TYPE_DATE             #TypeDeclarationDate
    ;

widgetDefinition
    : 'widget' widget
    ;

widget
    : WIDGET_BOOL_CHECKBOX booleanParameters?           #WidgetCheckboxDefinition
    | WIDGET_BOOL_DROPDOWN booleanParameters?           #WidgetDropdownDefinition
    | WIDGET_BOOL_RADIO booleanParameters?              #WidgetRadioDefinition
    | WIDGET_INTEGER_SPINBOX integerParameters?         #WidgetSpinboxDefinition
    | WIDGET_INTEGER_SLIDER integerParameters?          #WidgetSliderDefinition
    | WIDGET_TEXT                                       #WidgetTextDefinition

    ;

booleanParameters
    : OPEN_PARENTHESIS trueValue=STRING ',' falseValue=STRING CLOSE_PARENTHESIS
    ;

integerParameters
    : OPEN_PARENTHESIS 'min' '=' INTEGER ',' 'max' '=' INTEGER ',' 'step' '=' INTEGER CLOSE_PARENTHESIS
    ;

// widget keywords
WIDGET_INTEGER_SPINBOX:     'spinbox';
WIDGET_INTEGER_SLIDER:      'slider';
WIDGET_TEXT:                'text';                    // for text, decimals and money
WIDGET_BOOL_CHECKBOX:       'checkbox';
WIDGET_BOOL_RADIO:          'radio';
WIDGET_BOOL_DROPDOWN:       'dropdown';

// type keywords
TYPE_BOOLEAN    : 'boolean';
TYPE_STRING     : 'string';
TYPE_INTEGER    : 'integer';
TYPE_DECIMAL    : 'decimal';
TYPE_MONEY      : 'money';
TYPE_DATE       : 'date';


IDENTIFIER:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

BEGIN   : '{';
END     : '}';
OPEN_PARENTHESIS: '(';
CLOSE_PARENTHESIS : ')';

STRING: '"' .*? '"';
INTEGER: ('0'..'9')+;
HEX_VALUE: '#'(HEX_DIGIT)(HEX_DIGIT)(HEX_DIGIT)(HEX_DIGIT)(HEX_DIGIT)(HEX_DIGIT);
HEX_DIGIT: '0'..'9'|'a'..'f'|'A'..'F';

WS  :	(' ' | '\t' | '\n' | '\r')  -> skip;
COMMENT : '/*' .*? '*/'  -> skip;