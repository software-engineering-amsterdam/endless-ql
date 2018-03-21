lexer grammar QlsLexer;

WS: [ \t\n]+ -> skip ;

OCB: '{';
CCB: '}';

STYLESHEET_LIT: 'stylesheet';
PAGE_LIT: 'page';
SECTION_LIT: 'section';
QUESTION_LIT: 'question';
WIDGET_LIT: 'widget';
DEFAULT_LIT: 'default';

WIDGET_TYPE:
  'slider' |
  'spinbox' |
  'text' |
  'yesno-radio' |
  'yesno-dropdown' |
  'checkbox'
;

IDENTIFIER: [A-Za-z_][A-Za-z_0-9]*;
STRING_LIT: '"' ( '\\' [btnfr"'\\] | ~[\r\n\\"] )* '"';
