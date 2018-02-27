parser grammar QlParser;

options { tokenVocab=QlLexer; }

root: formHeader OCB formBody CCB;
formHeader: form IDENTIFIER;
form: FORM;

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
  IDENTIFIER #identifierExpression |
  NUMBER #numberExpression
;

/* binOp: ADD | SUB | MUL | DIV; */
binOp: arithmetic | logical;
unOp: NOT | SUB;

arithmetic: ADD | SUB | MUL | DIV;
logical: GT | LT | LTE | GTE | EQ | NEQ | CON | DIS; 

varDecl: IDENTIFIER DD typeDecl;
valAssign: ASS OB expr CB;
typeDecl: BOOL | MONEY;
