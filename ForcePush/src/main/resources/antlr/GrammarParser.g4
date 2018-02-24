parser grammar GrammarParser;

options { tokenVocab=GrammarLexer; }

//TESTING RULES, DO NOT UNCOMMENT
//{System.out.println("variable = "+$variable.text+"| Assign = "+$ASSIGN.text+"| Label = "+$LABEL.text+"| Type = "+$type.text);}

//RULES

//Variables and operators
variable        :VAR
                |NUM
                |NOT VAR;


logical         :(AND|OR);
conditional     :(IF|ELSE|IFELSE);
arithmetic      :(PLUS|MINUS|MULTIPLY|DIVIDE);
type            :(BOOL|STR|DATE|DECIMAL|MONEY);
comparison      :(LESS|GREATER|EQUALGREATER|EQUALLESS|NOTEQUAL|ISEQUAL);

//Shortcuts
questionTypes       : (questionFormat|conditionalConstr|questionAssignValue|questionMultiAns);

//Mathematical expressions
expression          : variable arithmetic variable
                    | variable logical variable
                    | variable comparison variable;


//Question types
questionFormat      : LABEL variable ASSIGN type;

questionAssignValue : questionFormat EQUAL LPAREN* expression RPAREN*;

conditionalConstr   : conditional LPAREN (variable|expression) RPAREN LBRACE questionTypes+ RBRACE;

questionMultiAns    : LABEL variable ASSIGN MULTIPLEANSWER LPAREN (variable) (COMMA variable)+ RPAREN;


//Class structure
formStructure       : FORM variable LBRACE questionTypes* RBRACE;
