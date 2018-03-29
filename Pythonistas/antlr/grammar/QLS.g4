grammar QLS;

// Parser
stylesheet : STYLESHEET ID page+ EOF;
page       : PAGE ID BRAL section+ (default)? BRAR;
section    : SECTION STRING ((question+ | question+ section question*) (default)? |
                        BRAL (question+ | question+ section question*) (default)? BRAR);
question   : QUESTION ID (attributes)?;

default    : DEFAULT type attributes;
attributes : BRAL (width | font | fontsize | color | widget)+ BRAR |
                  (width | font | fontsize | color | widget);
type       : (BOOLEAN | MONEY | ID);

widget   : WIDGET (checkbox | radio | spinbox);
width    : 'width' COL INT;
font     : 'font' COL STRING;
fontsize : 'fontsize' COL INT;
color    : 'color' COL HEX;

checkbox : CHECKBOX;
radio    : RADIO choices;
spinbox  : SPINBOX;

choices : PARL (STRING COM)* STRING PARR;

// Lexer
STYLESHEET : 'stylesheet';
PAGE       : 'page';
SECTION    : 'section';
QUESTION   : 'question';
WIDGET     : 'widget';
DEFAULT    : 'default';

CHECKBOX : 'checkbox';
RADIO    : 'radio';
SPINBOX  : 'spinbox';

BOOLEAN : 'boolean';
MONEY   : 'money';

HEX    : '#' (~('"' | '\\' | '\r' | '\n'))* {if len(self.text) > 7: self.type = self.OTHER};
INT    : [0-9]+;
ID     : [A-Za-z][A-Za-z0-9_]*;
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';

BRAL : '{';
BRAR : '}';
PARL : '(';
PARR : ')';
COL  : ':';
COM  : ',';

SPACE   : [ \t]+ -> skip;
NEWLINE : '\r'? '\n' -> skip;
OTHER: .;