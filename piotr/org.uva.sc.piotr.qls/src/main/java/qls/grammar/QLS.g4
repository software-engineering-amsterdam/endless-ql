grammar QLS ;

stylesheet
    : 'stylesheet' name=IDENTIFIER BEGIN pageDefinition+ END
    ;

pageDefinition
    : 'page' name=IDENTIFIER
    BEGIN ( blockElement )+ END
    ;

blockElement
    : questionDefinition
    | section
    | defaultDefinition
;

section
    : 'section' name=STRING (BEGIN blockElement+ END | questionDefinition)
    ;

questionDefinition
    : 'question' name=IDENTIFIER (widgetDefinition)?
    ;

defaultDefinition
    : 'default' type=dataType (widgetDefinition | BEGIN typeDefinitionProperty* END)
    ;

typeDefinitionProperty
    : 'width' ':' width=INTEGER
    | 'font' ':' font=STRING
    | 'fontsize' ':' fontSize=INTEGER
    | 'color' ':' color=HEX_VALUE
    | widgetDefinition
    ;

dataType
    : TYPE_BOOLEAN
    | TYPE_STRING
    | TYPE_INTEGER
    | TYPE_DECIMAL
    | TYPE_MONEY
    | TYPE_DATE
    ;

widgetDefinition
    : 'widget' widget
    ;

widget
    : WIDGET_BOOL_CHECKBOX booleanParameters?
    | WIDGET_BOOL_DROPDOWN booleanParameters?
    | WIDGET_BOOL_RADIO booleanParameters?
    | WIDGET_INTEGER_SPINBOX integerParameters?
    | WIDGET_INTEGER_SLIDER integerParameters?
    | WIDGET_TEXT

    ;

booleanParameters
    : OPEN_PARENTHESIS trueValue=STRING ',' falseValue=STRING CLOSE_PARENTHESIS
    ;

integerParameters
    : OPEN_PARENTHESIS 'min' '=' min=INTEGER ',' 'max' '=' max=INTEGER ',' 'step' '=' step=INTEGER CLOSE_PARENTHESIS
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