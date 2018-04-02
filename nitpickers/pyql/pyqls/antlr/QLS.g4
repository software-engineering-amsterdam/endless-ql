grammar QLS;

/*
 * Parser rules
 */

qlsObject           : 'QL:' filename styleSheet ;

styleSheet          : 'stylesheet' identifier '{' styleSheetBlock '}' ;

styleSheetBlock     : styleSheetStatement+ ;

styleSheetStatement : 'page' identifier '{' pageBlock '}' #styleSheetPageStatement
                    | defaultStatement                    #styleSheetDefaultStatement
                    ;

pageBlock           : pageStatement+ ;

pageStatement       : 'section' STRING 'question' identifier #singleStatementSection
                    | 'section' STRING '{' sectionBlock '}'  #multiStatementSection
                    | defaultStatement                       #defaultPageStatement
                    ;

sectionBlock        : statement+ ;

statement           : 'question' identifier #questionStatement
                    | questionStyle         #questionStyleStatement
                    | pageStatement         #sectionStatement
                    ;

questionStyle       : 'question' identifier widget ;

widget              : 'widget' widgetType;

widgetType          : 'radio(' STRING (',' STRING)* ')'    #radioWidget
                    | 'dropdown(' STRING (',' STRING)* ')' #dropdownWidget
                    | 'spinbox'                            #spinboxWidget
                    | 'checkbox'                           #checkboxWidget
                    | 'slider'                             #sliderWidget
                    | 'text'                               #textWidget
                    ;

defaultStatement    : 'default' questionType widget                 #simpleDefault
                    | 'default' questionType '{' defaultBody '}'    #defaultWithBody
                    ;

defaultBody         : styleRules* widget | widget styleRules* ;

styleRules          : width | font | fontsize | color ;

width               : 'width:' INT ;

font                : 'font:' STRING ;

fontsize            : 'fontsize:' INT ;

color               : 'color:' HEX_COLOR ;

questionType        : 'boolean' #booleanType
                    | 'string'  #stringType
                    | 'integer' #integerType
                    | 'decimal' #decimalType
                    | 'money'   #moneyType
                    ;

identifier          : IDENTIFIER ;

filename            : STRING ;

/*
 * Lexer rules
 */

WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT      : '/*' .*? '*/' -> channel(HIDDEN);

LINE_COMMENT : '//'.*? ~[\r\n]* -> channel(HIDDEN);

STRING       : '"' .*? '"';

INT          : [0-9]+;

IDENTIFIER   : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

HEX_COLOR    : '#'[0-9A-F][0-9A-F][0-9A-F][0-9A-F][0-9A-F][0-9A-F];