grammar QLGrammar;

/*parser*/         
form: FORM ID block EOF;
block: BRACKETL NEWLINE* (statement NEWLINE*)* BRACKETR;
statement: (question | conditional | assignment);

question: STRING ID DOT types;
assignment: STRING ID DOT types ASSIGN PARL expression PARR;

expression: literal
            | PARL left = expression PARR
            | unaryexp
            | left=expression COMPARE right=expression
            | left=expression MATH_OPERATOR_PRIO right=expression
            | left=expression MATH_OPERATOR right=expression
            | left=expression AND right=expression
            | left=expression OR right=expression
            ;

literal: INT | BOOL | ID | STRING | FLOAT;
unaryexp: NOT expression;

conditional: if_conditional | (if_conditional elif_conditional* else_conditional?);
if_conditional: IF_TOKEN PARL expression PARR block;
elif_conditional: ELIF_TOKEN PARL expression PARR block;
else_conditional: ELSE_TOKEN block;

MATH_OPERATOR_PRIO: MUL | DIV;
MATH_OPERATOR: ADD | SUB;
MUL :   '*' ; 
DIV :   '/' ;
ADD :   '+' ;
SUB :   '-' ;
DOT:    ':';

/*lexer*/
FORM:   'form';
IF_TOKEN: 'if';
ELIF_TOKEN: 'elif';
ELSE_TOKEN: 'else';


COMPARE: '<'
        | '>'
        | '>='
        | '<='
        | '=='
        | '!='
        ;

types: 'integer' | 'int' | 'boolean' | 'bool' | 'string' | 'str' | 'date' | 'money';

BOOL: 'true' | 'false';
INT :   [0-9]+ ;         // match integers
FLOAT: [0-9]+.[0-9]+;   // match floats
ID  :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;      // match identifiers
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';

BRACKETL: '{';
BRACKETR: '}';
PARL: '(';
PARR: ')';
ASSIGN: '=';
NOT: '!';
AND: '&&';
OR: '||';

NEWLINE:'\r'? '\n' -> skip;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace