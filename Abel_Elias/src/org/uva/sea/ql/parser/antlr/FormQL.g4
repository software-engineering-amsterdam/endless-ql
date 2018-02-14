/** Grammar for questionnaire form */
grammar formQL;
options {backtrack=true; memoize=true; output=AST; ASTLabelType=CommonTree;}

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

form : FORM IDENTIFIER content* EOF; // form

content : CURLY_BRACE_L (ifStatement* | question+) CURLY_BRACE_R; // content

ifStatement : IF BRACE_L expr BRACE_R content; //statement

question : IDENTIFIER COLON STR; //question (TODO: add the answer type)

expr : type
     | NOT expr // not
     | expr ( GRT | LST | GRTE | LSTE | EQT | NEQT) expr //equality and relational
     | expr ( AND | OR) expr // conditional
     | expr (ADD | SUB | DIV | MUL | REM) // arithmetic
     ;

type : STR | INT | BOOL | MONEY | IDENTIFIER;

/** Lexer rules (tokens)*/

//COMMENT
//    : '/*' .* '*/' {$channel=HIDDEN;}
//    ;
WHITESPACE : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

FORM : ('form' | 'Form');
IF : 'if';
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

// literals (type)
INT : ('0'..'9')+;
STR : '"' .* '"';
IDENTIFIER:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
BOOL : ('true' | 'false');
MONEY : (INT + '.' + INT) | INT;