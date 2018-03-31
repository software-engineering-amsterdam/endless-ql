grammar QLS; 
OCB: '{';
CCB: '}';

OP: '(';
CP: ')';

STYLESHEET_LIT: 'stylesheet';
PAGE_LIT: 'page';
DEFAULT_LIT: 'default';
SECTION_LIT: 'section';
QUESTION_LIT: 'question';
WIDGET_LIT: 'widget';

WIDTH_LIT: 'width';
FONT_LIT: 'font';
FONTSIZE_LIT: 'fontsize';
COLOR_LIT: 'color';
BOOLEAN_LIT: 'false' | 'true';
POLAR_LIT: '"Yes"' | '"No"';


BOOL_TYPE: 'boolean';
INTEGER_TYPE: 'integer';
STRING_TYPE: 'string';

WIDGET_TYPE:
  'slider' |
  'spinbox' |
  'text' |
  'dropdown' |
  'radio' |
  'checkbox'
;

DD: ':';
HEX_LIT: '#';
COMMA_LIT: ',';

fragment DIGIT_LIT: '0'..'9';
INTEGER_LIT: DIGIT_LIT+;


IDENTIFIER: [A-Za-z_][A-Za-z_0-9]*;
STRING_LIT: '"' ( '\\' [btnfr"'\\] | ~[\r\n\\"] )* '"';

WS: [ \t\n]+ -> skip ;

root: rootHeader OCB rootBody CCB EOF;

rootHeader: STYLESHEET_LIT identifier;
rootBody: pages=page+;

page: pageHeader OCB pageBody CCB;
pageHeader: PAGE_LIT identifier;
pageBody: element+;

section:
  sectionHeader OCB element+ CCB |
  sectionHeader element;

title: STRING_LIT;
sectionHeader: SECTION_LIT title;
sectionBody: element+;

element:
  section |
  question |
  defaultDecl;

question: QUESTION_LIT identifier stylingDecl?;

defaultDecl: DEFAULT_LIT types stylingDecl;

stylingDecl:
  widgetStyling |
  advancedStyling
;

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
widgetStyling: WIDGET_LIT WIDGET_TYPE optionValues?;

optionValues: polarOptions | integerOptions | booleanOptions | stringOptions;

polarOptions: OP POLAR_LIT COMMA_LIT POLAR_LIT CP;
stringOptions: OP STRING_LIT (COMMA_LIT + STRING_LIT)* CP;
integerOptions: OP INTEGER_LIT (COMMA_LIT + INTEGER_LIT)* CP;
booleanOptions: OP BOOLEAN_LIT COMMA_LIT BOOLEAN_LIT CP;

hex_color: HEX_LIT hex_value;
hex_value: INTEGER_LIT;

types:
  BOOL_TYPE |
  INTEGER_TYPE |
  STRING_TYPE;

identifier: IDENTIFIER;
