grammar QLS;

@parser::header {

}

@parser::members {
	
}

/** ^^^^^^^^^^^^^^^^^^^^
 * Parser Rules
 */
literal // atom
	: INTEGER
	| BOOLEAN
	| STRING
	| DECIMAL
	| MONEY
	| DATE
   ;

questionType
	: 'money'
	| 'integer'
	| 'boolean'
	| 'string'
	| 'date'
	| 'decimal'
  ;

style
	: 'color' ':' STRING
	| 'fontSize' ':' INTEGER
	| 'font' ':' STRING
	| 'width' ':' INTEGER
	| 'height' ':' INTEGER
   ;

styles
	: (style)*
   ;

widgetOptions
	: STRING (',' STRING )*
   ;
 
defaultType
    : 'default' questionType '{' styles ('widget' widgetType)? styles '}' 
   ;
   
widgetType
    : 'text'
    | 'radioBtn' '(' widgetOptions ')' 'default' STRING
    | 'checkbox' '(' widgetOptions ')' 'default' STRING
    | 'dropdown' '(' widgetOptions ')' 'default' STRING
    | 'slider' '(' widgetOptions ')' 'default' STRING
    | 'spinbox' '(' widgetOptions ')' 'default' STRING
    ; 
 
question
    : 'question' + IDENT +  ('widget' widgetType ('{' styles '}')?)
   ;

rule_
    : question
    | defaultType
   ;


section
    : 'section' STRING  '{' rule_ '}'
   ;

page
    : 'page' IDENT '{' section '}'
   ;
      
stylesheet
	: ('Stylesheet'|'stylesheet') IDENT '{' (page)+ '}'
   ;    



    
/** ^^^^^^^^^^^^^^^^^^^^
 * Lexer Rules
 */
WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);
SingleComment: '//' ~[\r\n]+ -> channel(HIDDEN);
BlockComment : '/*' .*? '*/' -> channel(HIDDEN);
IDENT        : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
DIGIT        : ('0'..'9');
INTEGER      : DIGIT+;
STRING       : '"' .*? '"';
BOOLEAN      : ('true'|'false');
TwoDigits    : ('0'..'9')('0'..'9');
QuadDigits   : ('0'..'9')('0'..'9')('0'..'9')('0'..'9');
MONEY        : DIGIT+ '.' TwoDigits;
DECIMAL      : DIGIT+ '.' DIGIT+;
DATE	     : TwoDigits'-'TwoDigits'-'QuadDigits;

