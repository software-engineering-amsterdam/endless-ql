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
expression          : LPAREN expression RPAREN #parensExpression
                    | left=expression arithmetic right=expression #binaryExpression
                    | left=expression logical right=expression #binaryExpression
                    | left=expression comparison right=expression #binaryExpression
                    | variable #valueExpression;


//Question types
questionFormat      : LABEL variable ASSIGN type;

questionAssignValue : questionFormat EQUAL LPAREN* expression RPAREN*;

conditionalConstr   : conditional LPAREN (variable|expression) RPAREN LBRACE questionTypes+ RBRACE;

questionMultiAns    : LABEL variable ASSIGN MULTIPLEANSWER LPAREN (variable) (COMMA variable)+ RPAREN;


//Class structure
formStructure       : FORM variable LBRACE questionTypes* RBRACE;
