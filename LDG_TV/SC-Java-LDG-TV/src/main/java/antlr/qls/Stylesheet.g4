grammar Stylesheet;
/*
 * Parser Rules
 */
stylesheetBuilder  : 'stylesheet' CHARACTERS CURLY_BRACKET_OPEN stylesheetData CURLY_BRACKET_CLOSE;
stylesheetData : (pageNodeStructure)+;

pageNodeStructure: 'page' identifier CURLY_BRACKET_OPEN (sectionNodeStructure)+ CURLY_BRACKET_CLOSE;
sectionNodeStructure:
    'section'
    label
    CURLY_BRACKET_OPEN?
    (questionStructure)+
    CURLY_BRACKET_CLOSE?
    ((sectionNodeStructure)+)?
;

identifier: CHARACTERS;
label: STRING;
value: (CHARACTERS | STRING | NUMBERS | (SPECIAL_CHAR NUMBERS));
questionStructure: 'question' identifier uiElement?;

uiElement: uiIdentifier uiType;
uiIdentifier: 'widget' | 'default';
uiType: checkbox | radio | spinbox | money;

checkbox: 'checkbox';
radio: 'radio' (radioOptions)+;
radioOptions: BRACKET_OPEN  label ',' label BRACKET_CLOSE;
spinbox: 'spinbox';
money: 'money' CURLY_BRACKET_OPEN (option)+ CURLY_BRACKET_CLOSE;
option: identifier ':' value (uiElement)?;

/*
 * Lexer Rules
 */

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
NUMBERS : ('0' .. '9')+  ;

STRING: '"' ((CHARACTERS | ' ')+) '"';

CURLY_BRACKET_OPEN : '{';
CURLY_BRACKET_CLOSE : '}';
BRACKET_OPEN : '(';
BRACKET_CLOSE : ')';

WHITESPACE : [ \r\n\t] + -> skip;
NEWLINE : ('\r'? '\n' | '\r')+ -> skip;

CHARACTERS : (LOWERCASE | UPPERCASE)+;
SPECIAL_CHAR: ('#');

