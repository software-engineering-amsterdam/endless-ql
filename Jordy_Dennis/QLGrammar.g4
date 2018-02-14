grammar QLGrammar;

/*parser*/         
form: FORM ID block EOF;
block: BRACKETL NEWLINE* (statement NEWLINE*)* BRACKETR;
statement: (question | conditional | assignment);

question: STRING ID DOT types;
assignment: STRING ID DOT types ASSIGN PARL expression PARR;


expression: BOOL
            | INT
            | ID
            | PARL expression PARR
            | NOT expression
            | expression COMPARE expression
            | expression MATH_OPERATOR expression
            | expression AND expression
            | expression OR expression
            ;

conditional: if_conditional | (if_conditional elif_conditional* else_conditional);
if_conditional: IF_TOKEN PARL expression PARR block;
elif_conditional: ELIF_TOKEN PARL expression PARR block;
else_conditional: ELSE_TOKEN block;


/*lexer*/
FORM:   'form';
IF_TOKEN: 'if';
ELIF_TOKEN: 'elif';
ELSE_TOKEN: 'else';

MATH_OPERATOR: MUL | DIV | ADD | SUB;
MUL :   '*' ; 
DIV :   '/' ;
ADD :   '+' ;
SUB :   '-' ;
DOT :   ':';

COMPARE: '<'
        | '>'
        | '>='
        | '<='
        | '=='
        | '!='
        ;

types: 'integer' | 'boolean' | 'string' | 'date' | 'money';

BOOL: 'true' | 'false';
INT :   [0-9]+ ;         // match integers
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