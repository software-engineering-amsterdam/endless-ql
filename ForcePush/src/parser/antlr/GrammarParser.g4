parser grammar GrammarParser;

options { tokenVocab=Grammar; }

//TESTING RULES
a : b INT;
b   : ID ID;

//RULES
variable:VAR
        |NOT VAR;

expression: variable arithmetic=(PLUS|MINUS|ASTERISK|DIVISION) variable
          | variable boolean=(AND|OR) variable
          | variable comparison=(LESS|HIGHER|EQUALHIGHER|EQUALLESS|DIFF|ISEQUAL) variable;

question:variable ASSIGN LABEL type=(BOOL|STR|DATE|DECIMAL|MONEY)
        |question EQUAL LPAREN* expression RPAREN*;

conditional: operator=(IF|ELSE|IFELSE) LPAREN (variable|expression) RPAREN LCURLYBRAKET (question|conditional)+ RCURLYBRAKET;

form: FORM variable LCURLYBRAKET (question|conditional)* RCURLYBRAKET;
