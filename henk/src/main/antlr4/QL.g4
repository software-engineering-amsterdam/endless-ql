grammar QL;

WS: [ \t\n]+ -> skip ;

BOOL_T: 'boolean';
MONEY_T: 'money';
INTEGER_T: 'integer';
STRING_T: 'string';

FORM: 'form';

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';

OCB: '{';
CCB: '}';

OB: '(';
CB: ')';

ASS: '=';
DD: ':';

GT: '>';
LT: '<';
LTE: '<=';
GTE: '>=';
EQ: '==';
NEQ: '!=';

CON: '&&';
DIS: '||';

NOT: '!';

IF: 'if';
ELSE: 'else';

fragment DIGIT: '0'..'9';
INTEGER: DIGIT+;
/* NUMBER: ('0'..'9')+; */

BOOLEAN: 'false' | 'true';

FLOAT: ('0' .. '9') + ('.' ('0' .. '9') +)?;
IDENTIFIER: [A-Za-z_][A-Za-z_0-9]*;

/* Escape double quoted in string in visitor */
STRINGLIT: '"' ( '\\' [btnfr"'\\] | ~[\r\n\\"] )* '"';

root: formHeader OCB formBody CCB;
formHeader: form identifier;
form: FORM;

integerLit: INTEGER;
booleanLit: BOOLEAN;
stringLit: STRINGLIT;

identifier: IDENTIFIER;

formBody: stmt+;
stmt:
  computation |
  question |
  conditional |
  varDecl
;

computation: label varDecl valAssign;
question: label varDecl;
label: STRINGLIT;

conditional: ifStmt (elseStmt)?;

ifStmt: IF OB expr CB OCB stmt* CCB;
elseStmt: ELSE OCB stmt* CCB;

expr:
  OB expr CB #bracketExpression |
  lhs=expr binOp rhs=expr #binaryExpression |
  op=unOp expr #unaryExpression |
  identifier #identifierExpression |
  integerLit #integerExpression |
  booleanLit #booleanExpression |
  stringLit #stringExpression 
;

binOp: arithmetic | logical | relational;
unOp: NOT | SUB;

arithmetic: ADD | SUB | MUL | DIV;
logical: CON | DIS; 
relational: GT | LT | LTE | GTE | EQ | NEQ;

varDecl: identifier DD typeDecl;
valAssign: ASS expr;
typeDecl: BOOL_T | MONEY_T | INTEGER_T | STRING_T;
