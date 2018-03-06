grammar QLS;

// Parser rules
parameter: STRING | NUMBER | COLOR;
parametrizedWidget: WIDGET_TYPE PAREN_OPEN (parameter','?)* parameter PAREN_CLOSE;
simpleWidget: WIDGET_TYPE;
widgetType: simpleWidget | parametrizedWidget;
question: QUESTION IDENTIFIER (WIDGET widgetType)?;
propertySetting: PROPERTY_KEY PROPERTY_SEPARATOR parameter;
defaultWidgetDeclaration: DEFAULT TYPE BRACKET_OPEN propertySetting* widgetType propertySetting* BRACKET_CLOSE;
section: SECTION STRING (BRACKET_OPEN (question | defaultWidgetDeclaration)* BRACKET_CLOSE | question);
page: PAGE IDENTIFIER BRACKET_OPEN section* BRACKET_CLOSE;
stylesheet: STYLESHEET IDENTIFIER BRACKET_OPEN page* BRACKET_CLOSE;

WIDGET_TYPE : 'spinbox'
            | 'checkbox'
            | 'text'
            | 'dropdown'
            | 'slider';

// Keywords
PROPERTY_KEY : 'width'
             | 'height'
             | 'font'
             | 'fontsize'
             | 'color'
             ;
DEFAULT: 'default';
SECTION: 'section';
QUESTION: 'question';
STYLESHEET: 'stylesheet';
PAGE: 'page';
WIDGET: 'widget';
TYPE : 'boolean'
     | 'money'
     | 'string'
     | 'integer'
     | 'date'
     | 'decimal'
     ;

// To skip New Lines, White spaces and comments
NEWLINE: ('\n' | '\r' | '\r\n') -> skip;
WHITESPACE : (' ' | '\n' | '\r' | '\t') -> skip;
COMMENT			: ('/*' .*? '*/') ->skip;
LINE_COMMENT	: '//' ~[\r\n]* ->skip;
BRACKET_OPEN : '{';
BRACKET_CLOSE: '}';
PAREN_OPEN: '(';
PAREN_CLOSE: ')';
PROPERTY_SEPARATOR: ':';

// Literals
IDENTIFIER	: [a-zA-Z0-1_]+;
NUMBER		: [0-9]+(.[0-9]+)?;
BOOLEAN		: 'true' | 'false';
STRING		: '"' .*? '"';
COLOR       : '#' [a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9];
