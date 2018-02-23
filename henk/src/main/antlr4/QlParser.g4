parser grammar QlParser;

options { tokenVocab=QlLexer; }

root: formHeader OCB formBody CCB;
formHeader: form IDENTIFIER;
form: FORM;

formBody: stmts;
stmts: stmt+;
stmt: question | conditional;

question: label varDecl typeDecl;
label: STRINGLIT;

conditional: ifStmt;

ifStmt: ifHeader OCB ifBody CCB;
ifHeader: IF OB exprs CB;
ifBody: stmts;

exprs: expr;

expr:
  expr operation expr |
  IDENTIFIER |
  NUMBER
;

operation: (ADD | SUB | MUL | DIV);

varDecl: IDENTIFIER DD;
typeDecl: (BOOL | MONEY) (EQ OB exprs CB)?;
