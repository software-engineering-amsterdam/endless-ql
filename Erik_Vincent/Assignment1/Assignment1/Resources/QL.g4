parser grammar QL;

options { tokenVocab=QLLexer; }

file
	: form* EOF
	;
form
	: FORM ID content
	;
content
	: OPEN_CB (question | statement)* CLOSE_CB;
questionAssign
	: questionBool (ASSIGN expression)
	| questionMoney (ASSIGN expression)
	;
question
	: questionAssign
	| questionBool
	| questionMoney
	;
questionBool
	: LABEL ID SEP BOOLEAN
	;
questionMoney
	: LABEL ID SEP MONEY
	;
statement
	: ifstatement
	;
ifstatement
	: IF OPEN_BR expression CLOSE_BR content (ELSE content)?
	;
expression
	: value
	| OPEN_BR expression CLOSE_BR
	| expression AND expression
	| expression OR expression
	| expression SUB expression
	| operatorU expression
	| id
	;
value
	: bool
	| integer
	| decimal
	;
bool
	: TRUE
	| FALSE
	;
integer
	: INTEGER
	;
decimal
	: DECIMAL
	;
id
	: ID
	;
operatorU
	: NOT
	;