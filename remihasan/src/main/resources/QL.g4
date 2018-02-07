// Define a grammar called QL
grammar QL;
r  : 'form' IDENTIFIER block EOF;
WS : [ \t\r\n]+ -> skip ;
block : '{' (condition | question)* '}';
condition : IF '(' expression ')' block;
question : IDENTIFIER ':' STRING ((questionvaluetype '=' expression) | questionvaluetype);

expression :
    INTEGER
    | IDENTIFIER
    | '(' expression ')'
    | expression operation expression;

// Operation types such as +, -, / and *
operation : SUMOPERATOR | SUBTRACTOPERTOR | MULTIPLYOPERATOR | DIVIDEOPERATOR | GT | GEQ | LT | LEQ;
SUMOPERATOR : '+';
SUBTRACTOPERTOR : '-';
MULTIPLYOPERATOR : '*';
DIVIDEOPERATOR : '/';
GT : '>';
GEQ: '>=';
LT : '<';
LEQ : '<=';





// Question answer value types
questionvaluetype : BOOLEANTYPE | MONEYTYPE;
BOOLEANTYPE : 'boolean';
MONEYTYPE : 'money';

//
IF : 'if';
INTEGER : [0-9]+;
STRING : '"' .*? '"';
IDENTIFIER : [a-zA-Z0-9]+ ;