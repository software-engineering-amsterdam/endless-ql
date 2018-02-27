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

/* ifStmt: ifHeader OCB ifBody CCB; */
ifStmt: IF OB expr CB OCB stmt+ CCB;
/* ifHeader: IF OB exprs CB; */
/* ifBody: stmt+; */

expr:
  OB expr CB #BracketExpression |
  lhs=expr binOp rhs=expr #binaryExpression |
  op=unOp expr #unaryExpression |
  IDENTIFIER #identifierExpression |
  NUMBER #numberExpression
;

binOp: ADD | SUB | MUL | DIV;
unOp: EM;

varDecl: IDENTIFIER DD typeDecl;
valAssign: EQ OB expr CB;
typeDecl: (BOOL | MONEY);
