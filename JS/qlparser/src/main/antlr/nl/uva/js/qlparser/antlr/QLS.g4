grammar QLS;


// Lexical parts
// Grammar is whitespace insensative
WS: [ \n\t\r]+   -> skip;
LC: '//'~[\r\n]+ -> skip;

// Top level element indicator
STYLESHEET: 'stylesheet';

// Keywords
PAGE:     'page';
SECTION:  'section';
QUESTION: 'questions';
WIDGET:   'widget';
DEFAULT:  'default';
STYLE:    'style';

// Datatypes
DATE:       'date';
MONEY:      'money';
STRING:     'string';
DECIMAL:    'decimal';
BOOLEAN:    'boolean';
INTEGER:    'integer';

// Special characters
COLON:  ':';
ASSIGN: '=';
HASH:   '#';
LB:     '{';
RB:     '}';

// Widget types
CHECKBOX: 'checkbox';
RADIO:    'radio';    // Yes/No
DROPDOWN: 'dropdown'; // Yes/No
TEXT:     'text';     // For numbers and strings
SLIDER:   'slider';
SPINBOX:  'spinbox';  // For numbers

// Widget style properties
WIDGETCOLOR: 'widgetcolor';
FONTCOLOR:   'fontcolor';
FONTTYPE:    'fonttype';
FONTSTYLE:   'fontstyle';


// Question, page and widget names
NAME: [a-zA-Z][a-zA-Z0-9]*;

// String values for naming Pages and Sections
STRVAL: '"'~['\\\r\n]*?'"';

COLORVAL: '#'[a-zA-Z0-9]*;

INTVAL:[0-9]+;


// Token groupings

dataType
    : DATE
    | MONEY
    | STRING
    | DECIMAL
    | BOOLEAN
    | INTEGER
    ;

widgetType
    : CHECKBOX
    | RADIO
    | DROPDOWN
    | TEXT
    | SLIDER
    | SPINBOX
    ;

property
    : WIDGETCOLOR
    | FONTCOLOR
    | FONTTYPE
    | FONTSTYLE
    ;

value
    : STRVAL
    | INTVAL
    | COLORVAL
    ;


// Higher level parsing
// Entry point for QLS
stylesheet
    : STYLESHEET NAME styleBlock
    ;

styleBlock
    : LB expression* RB
    ;

expression
    : defaultStyle
    | page
    ;

page
    : PAGE NAME LB section* RB
    ;

section
    : SECTION STRVAL LB question* RB
    ;

question
    : QUESTION NAME (WIDGET widgetType widgetStyle?)?
    ;

widgetStyle
    : STYLE LB styleRule* RB
    ;

styleRule
    : property COLON value
    ;

defaultStyle
    : DEFAULT dataType WIDGET widgetType widgetStyle?
    ;