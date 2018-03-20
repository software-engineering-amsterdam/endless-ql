parser grammar QlParser;

options { tokenVocab=QlLexer; }

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

conditional: ifStmt;

ifStmt: IF OB expr CB OCB stmt* CCB;

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
