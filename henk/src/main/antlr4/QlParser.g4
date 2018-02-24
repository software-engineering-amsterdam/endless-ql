parser grammar QlParser;

options { tokenVocab=QlLexer; }

root: formHeader OCB formBody CCB;
formHeader: form IDENTIFIER;
form: FORM;

formBody: stmt+;
stmt: question | conditional;

question: label varDecl typeDecl;
label: STRINGLIT;

conditional: ifStmt;

ifStmt: ifHeader OCB ifBody CCB;
ifHeader: IF OB expr+ CB;
ifBody: stmt+;

expr:
  expr binOp expr |
  unOp expr |
  IDENTIFIER |
  NUMBER
;

binOp: (ADD | SUB | MUL | DIV);
unOp: EM;

varDecl: IDENTIFIER DD;
typeDecl: (BOOL | MONEY) (EQ OB expr CB)?;
