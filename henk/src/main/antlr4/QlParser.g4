parser grammar QlParser;

options { tokenVocab=QlLexer; }

root: formHeader OCB formBody CCB;
formHeader: form identifier;
form: FORM;

integer: INTEGER;
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
  integer #integerExpression |
  identifier #identifierExpression
;

binOp: arithmetic | logical | relational;
unOp: NOT | SUB;

arithmetic: ADD | SUB | MUL | DIV;
logical: CON | DIS; 
relational: GT | LT | LTE | GTE | EQ | NEQ;

varDecl: identifier DD typeDecl;
valAssign: ASS expr;
typeDecl: BOOL_T | MONEY_T | INTEGER_T | STRING_T;
