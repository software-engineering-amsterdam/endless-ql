grammar QLGrammar;            
form: FORM ID block EOF;
block: BRACKETL NEWLINE* (statement NEWLINE*)* BRACKETR;
statement: (question | conditional | assignment);

question: STRING ID DOT type;
assignment: STRING ID DOT type ASSIGN PARL expression PARR;


expression: BOOL
            | STRING
            | INT
            | ID
            | PARL expression PARR
            | NOT expression
            | expression COMPARE expression
            | expression MATH_OPERATOR expression
            | expression AND expression
            | expression OR expression
            ;

conditional: if_conditional | elif_conditional | else_conditional;

if_conditional: IF_TOKEN PARL expression PARR block;
elif_conditional: ELIF_TOKEN PARL expression PARR block;
else_conditional: ELSE_TOKEN PARL expression PARR block;

COMPARE: '<'
        | '>'
        | '>='
        | '<='
        | '=='
        | '!='
        ;

MATH_OPERATOR: MUL | DIV | ADD | SUB;
AND: '&&';
OR: '||';

type: 'integer' | 'boolean' | 'string' | 'date' | 'money';

BOOL: 'true' | 'false';

FORM:   'form';
IF_TOKEN: 'if';
ELIF_TOKEN: 'elif';
ELSE_TOKEN: 'else';

MUL :   '*' ; 
DIV :   '/' ;
ADD :   '+' ;
SUB :   '-' ;
DOT :   ':';
BRACKETL: '{';
BRACKETR: '}';
PARL: '(';
PARR: ')';
ASSIGN: '=';
NOT: '!';

ID  :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;      // match identifiers
STRING : '"' (~('"' | '\\' | '\r' | '\n'))* '"';
INT :   [0-9]+ ;         // match integers
NEWLINE:'\r'? '\n' -> skip;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace