parser grammar QlParser;

options { tokenVocab=QlLexer;}

form: FORM ID block EOF;
block: BRACKETL NEWLINE* (statement NEWLINE*)* BRACKETR;
statement: (question | assignment | conditional);

question: STRING ID COL types;
assignment: STRING ID COL types ASSIGN PARL expression PARR;

expression: BOOL
            | INT
            | ID
            | PARL expression PARR
            | NOT expression
            | expression BOOL_OPERATOR expression
            | expression MATH_OPERATOR expression
            | expression AND expression
            | expression OR expression
            ;

conditional: if_conditional | (if_conditional else_conditional);
if_conditional: IF_TOKEN PARL expression PARR block;
else_conditional: ELSE_TOKEN block;

types: BOOL | INT;
