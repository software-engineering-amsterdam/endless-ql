parser grammar GrammarParser;

options { tokenVocab=GrammarLexer; }

//TESTING RULES, DO NOT UNCOMMENT
//{System.out.println("variable = "+$variable.text+"| Assign = "+$ASSIGN.text+"| Label = "+$LABEL.text+"| Type = "+$type.text);}

//RULES

compileUnit     :formStructure EOF;
mathUnit        :expression EOF;

//Variables and operators
variable        :VAR
                |DEC
                |NUM
                |NOT VAR;


logical         :(AND|OR);
arithmetic      :(MULTIPLY|DIVIDE);
type            :(BOOL|STR|DATE|DECIMAL|MONEY);
comparison      :(LESS|GREATER|EQUALGREATER|EQUALLESS|NOTEQUAL|ISEQUAL);

//Shortcuts
questionTypes       : (questionFormat|conditionalIf|questionAssignValue|questionMultiAns);

//Mathematical expressions
expression          : LPAREN expression RPAREN #parensExpression
                    | left=expression op=(PLUS|MINUS) right=expression #binaryExpression
                    | left=expression logical right=expression #logicalExpression
                    | left=expression comparison right=expression #comparisonExpression
                    | value=NUM #valueExpression;


//Question types
questionFormat      : LABEL variable ASSIGN type;

questionAssignValue : questionFormat EQUAL LPAREN* expression RPAREN*;

conditionalIf       : IF LPAREN (variable|expression) RPAREN LBRACE questionTypes+ RBRACE (conditionalElse|conditionalIfElse)*;

conditionalIfElse   : IFELSE LPAREN (variable|expression) RPAREN LBRACE questionTypes+ RBRACE (conditionalElse|conditionalElse)+;

conditionalElse     : ELSE LBRACE questionTypes+ RBRACE;

questionMultiAns    : LABEL variable ASSIGN MULTIPLEANSWER LPAREN (variable) (COMMA variable)+ RPAREN;


//Class structure
formStructure       : FORM variable LBRACE questionTypes* RBRACE;
