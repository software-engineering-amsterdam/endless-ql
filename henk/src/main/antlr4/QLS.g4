grammar QLS; 
OCB: '{';
CCB: '}';

OP: '(';
CP: ')';

STYLESHEET_LIT: 'stylesheet';
PAGE_LIT: 'page';
SECTION_LIT: 'section';
QUESTION_LIT: 'question';
WIDGET_LIT: 'widget';
DEFAULT_LIT: 'default';

WIDTH_LIT: 'width';
FONT_LIT: 'font';
FONTSIZE_LIT: 'fontsize';
COLOR_LIT: 'color';

BOOL_TYPE: 'boolean';
MONEY_TYPE: 'money';
INTEGER_TYPE: 'integer';
STRING_TYPE: 'string';

WIDGET_TYPE:
  'slider' |
  'spinbox' |
  'text' |
  'yesno-radio' |
  'yesno-dropdown' |
  'radio' |
  'checkbox'
;

DD: ':';
HEX_LIT: '#';
DECIMAL_LIT: ',';

fragment DIGIT_LIT: '0'..'9';
INTEGER_LIT: DIGIT_LIT+;


IDENTIFIER: [A-Za-z_][A-Za-z_0-9]*;
STRING_LIT: '"' ( '\\' [btnfr"'\\] | ~[\r\n\\"] )* '"';

WS: [ \t\n]+ -> skip ;

root: styleheader OCB stylebody CCB EOF;

styleheader: STYLESHEET_LIT identifier;
stylebody: page+;

page: pageheader OCB pagebody CCB;
pageheader: PAGE_LIT identifier;
pagebody: section+;

section:
  sectionheader OCB sectionbody CCB |
  sectionheader question;

sectionheader: SECTION_LIT STRING_LIT;
sectionbody: statement+;

statement:
  section |
  question |
  defaultDecl;

question: QUESTION_LIT identifier widgetStyling?;

defaultDecl: DEFAULT_LIT types stylingDecl;

stylingDecl: widgetStyling | advancedStyling;

advancedStyling: OCB stylingConfiguration+ CCB;

stylingConfiguration:
  widthStyling |
  fontStyling |
  fontSizeStyling |
  colorStyling |
  widgetStyling;

widthStyling: WIDTH_LIT DD INTEGER_LIT;
fontStyling: FONT_LIT DD STRING_LIT;
fontSizeStyling: FONTSIZE_LIT DD INTEGER_LIT;
colorStyling: COLOR_LIT DD hex_color;
widgetStyling: WIDGET_LIT WIDGET_TYPE dropdown?;

dropdown: OP STRING_LIT + (DECIMAL_LIT + STRING_LIT)* CP;

hex_color: HEX_LIT hex_value;
hex_value: INTEGER_LIT;

types:
  BOOL_TYPE |
  MONEY_TYPE |
  INTEGER_TYPE |
  STRING_TYPE;

identifier: IDENTIFIER;
