grammar QLSGrammar;

stylesheet: STYLESHEET ID (BRACKETL page* BRACKETR | page) EOF;
page: PAGE ID (BRACKETL (section|default_style)* BRACKETR | (section|default_style));
section: SECTION STRING (BRACKETL (section|question|default_style)* BRACKETR | (section|question|default_style));
question: QUESTION (ID | ID widget);
widget: WIDGET CHECKBOX|
        WIDGET RADIO PARL STRING COMMA STRING PARR |
        WIDGET SPINBOX |
          FONT COLOM STRING |
        WIDTH COLOM INT |
        FONTSIZE COLOM INT |
        COLOR COLOM HEXCOLOR
        ;
default_style: DEFAULT types (BRACKETL widget* BRACKETR | widget);


STYLESHEET: 'stylesheet';
PAGE:       'page';
SECTION:    'section';
QUESTION:   'question';
WIDGET:     'widget';
FONT:       'font';
WIDTH:      'width';
FONTSIZE:   'fontsize';
COLOR:      'color';
DEFAULT:    'default';
CHECKBOX:   'checkbox';
SPINBOX:    'spinbox';
RADIO:      'radio';


types: 'integer' | 'int' | 'boolean' | 'bool' | 'string' | 'str' | 'money' | 'float';
BOOL: 'true' | 'false';
INT :   [0-9]+ ;         // match integers
FLOAT: [0-9]+.[0-9]+;   // match floats
ID  :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;      // match identifiers
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';
HEXCOLOR: HASH ([0-9] | 'A'..'F') ([0-9] | 'A'..'F') ([0-9] | 'A'..'F') ([0-9] | 'A'..'F') ([0-9] | 'A'..'F') ([0-9] | 'A'..'F');

BRACKETL: '{';
BRACKETR: '}';
PARL: '(';
PARR: ')';
COMMA: ',';
COLOM: ':';
HASH: '#';

NEWLINE:'\r'? '\n' -> skip;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace