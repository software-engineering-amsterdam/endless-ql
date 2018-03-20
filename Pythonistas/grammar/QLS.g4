grammar QLS;

// Parser
stylesheet: STYLESHEET ID page+ EOF;
page: PAGE ID BRACKETL (section* | (section* default)) BRACKETR;
section: SECTION STRING BRACKETL* (question+ ) BRACKETR*;
question: QUESTION ID (widget | default);

widget: WIDGET (checkbox | radio | spinbox);
default: DEFAULT type BRACKETL attributes BRACKETR;
type: (BOOLEAN | MONEY | ID);
attributes: width font fontsize color widget;

width: 'width' COL INT;
font: 'font' COL STRING;
fontsize: 'fontsize' COL INT;
color: 'color' COL HEX;

checkbox: CHECKBOX;
radio: RADIO;
spinbox: SPINBOX;

// Lexer
STYLESHEET: 'stylesheet';
PAGE: 'page';
SECTION: 'section';
QUESTION: 'question';
WIDGET: 'widget';
DEFAULT: 'default';

CHECKBOX: 'checkbox';
RADIO: 'radio("Yes", "No")';
SPINBOX: 'spinbox';

BOOLEAN: 'boolean';
MONEY: 'money';

INT    : [0-9]+;
ID     : [A-Za-z][A-Za-z0-9_]*;
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';
HEX: HASH INT;

BRACKETL : '{';
BRACKETR : '}';
COL      : ':';
HASH     : '#';

SPACE: [ \t]+ -> skip;
NEWLINE: '\r'? '\n' -> skip;