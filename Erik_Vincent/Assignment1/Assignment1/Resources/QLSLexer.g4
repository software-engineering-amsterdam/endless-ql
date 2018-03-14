lexer grammar QLSLexer;

STYLESHEET
	: 'stylesheet'
	;
PAGE
	: 'page'
	;
SECTION
	: 'section'
	;
QUESTION
	: 'question'
	;
WIDGET
	: 'widget'
	;
RADIO
	: 'radio'
	;
CHECKBOX
	: 'checkbox'
	;
SLIDER
	: 'slider'
	;
SPINBOX
	: 'spinbox'
	;
TEXTBOX
	: 'textbox'
	;
DROPDOWN
	: 'dropdown'
	;
DEFAULT
	: 'default'
	;
WIDTH
	: 'width'
	;
FONT
	: 'font'
	;
FONTSIZE
	: 'fontsize'
	;
COLOR
	: 'color'
	;
HEXCOLORCODE
	: '#' HEXCHAR HEXCHAR HEXCHAR HEXCHAR HEXCHAR HEXCHAR
	;
fragment HEXCHAR
	: ([a-f] | [0-9])
	;
OPEN_BR
	: '('
	;
CLOSE_BR
	: ')'
	;
OPEN_CB
	: '{'
	;
CLOSE_CB
	: '}'
	;
SEP
	: ':'
	;
COMMA
	: ','
	;
BOOLEAN_TYPE
	: 'boolean'
	;
DATE_TYPE
	: 'date'
	;
DECIMAL_TYPE
	: 'decimal'
	;
INTEGER_TYPE
	: 'integer'
	;
STRING_TYPE
	: 'string'
	;
MONEY_TYPE
	: 'money'
	;
NUMBER
	: [0-9]+
	;
ID
	: [a-zA-Z0-9]+
	;
STRING
	: '"' (~'"' | '""')* '"'
	;
COMMENT
	: '//' ~'\n'*? '\n' -> skip
	;
WHITESPACE
	: [ \n\t\r]+ -> skip
	;