/** Grammar for questionnaire form */
grammar FormQL;

@parser::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}

@lexer::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}

/** Parser rules */

questionnaire : form; // (top) questionnaire

form : FORM IDENTIFIER content EOF; // form

content : CURLY_BRACE_L (ifStatement | question) CURLY_BRACE_R; // content

question : IDENTIFIER COLON STR type; //question2

ifStatement : IF BRACE_L expr BRACE_R content*; //statement

expr : type
     | NOT expr // not
     | expr ( GRT | LST | GRTE | LSTE | EQT | NEQT) expr //equality and relational
     | expr ( AND | OR) expr // conditional
     | expr (ADD | SUB | DIV | MUL | REM) expr // arithmetic
     ;

type : STR | INT | BOOL | MONEY | IDENTIFIER;

/** Lexer rules (tokens)*/

//COMMENT
//    : '/*' .* '*/' {$channel=HIDDEN;}
//    ;
WHITESPACE : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

FORM : ('form' | 'Form');
IF : ('if' | 'If');
COLON : ':';

// seperators
CURLY_BRACE_L : '{';
CURLY_BRACE_R: '}';
BRACE_L : '(';
BRACE_R : ')';

// operators
NOT : '!';
ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
REM : '%';
EQT : '==';
NEQT : '!=';
GRT : '>';
LST : '<';
GRTE : '>=';
LSTE : '<=';
AND : '&&';
OR : '||';

ESC_SEQ : '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\');

// literals (type)
STR : '“' .*? '”';
INT : ('0'..'9')+;
IDENTIFIER:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
BOOL : ('true' | 'false');
MONEY : (INT + '.' + INT) | INT;
