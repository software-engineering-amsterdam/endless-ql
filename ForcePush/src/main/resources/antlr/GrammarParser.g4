parser grammar GrammarParser;

options { tokenVocab=GrammarLexer; }

//TESTING RULES, DO NOT UNCOMMENT
//{System.out.println("variable = "+$variable.text+"| Assign = "+$ASSIGN.text+"| Label = "+$LABEL.text+"| Type = "+$type.text);}

//RULES

compileUnit     :formStructure EOF;
mathUnit        :expression;

//Variables and operators
variable        :VAR
                |DEC
                |NUM
                |NOT VAR;


type            :(BOOL|STR|DATE|DECIMAL|MONEY);

//Shortcuts
questionTypes       : (questionFormat|conditionalIf|questionAssignValue);

//Mathematical expressions
expression        : LPAREN expression RPAREN                                                                            #parenthesisExpression
                  | op=(PLUS|MINUS)     expression                                                                      #unaryExpression
                  | left=expression     op=(MULTIPLY|DIVIDE)    right=expression                                        #infixExpression
                  | left=expression     op=(PLUS|MINUS)         right=expression                                        #infixExpression
                  | left=expression     log=(AND|OR)            right=expression                                        #logicalExpression
                  | left=expression     comp=(LESS|GREATER|EQUALGREATER|EQUALLESS|NOTEQUAL|ISEQUAL) right=expression    #comparisonExpression
                  | value=(NUM|VAR|DEC)                                                                                 #numberExpression;


//Question types
questionFormat      : LABEL variable ASSIGN  type;

questionAssignValue : questionFormat EQUAL LPAREN* expression RPAREN*;

conditionalIf       : IF LPAREN (variable|expression) RPAREN LBRACE questionTypes+ RBRACE (conditionalElse|conditionalIfElse)*;

conditionalIfElse   : IFELSE LPAREN (variable|expression) RPAREN LBRACE questionTypes+ RBRACE (conditionalElse|conditionalElse)+;

conditionalElse     : ELSE LBRACE questionTypes+ RBRACE;

//questionMultiAns    : LABEL variable ASSIGN MULTIPLEANSWER LPAREN (variable) (COMMA variable)+ RPAREN;


//Class structure
formStructure       : FORM variable LBRACE questionTypes* RBRACE;









/*type            :(BOOL|STR|DATE|DECIMAL|MONEY);

  //Shortcuts
  questionTypes       : (questionFormat|conditionalIf|questionAssignValue|questionMultiAns);

  //Mathematical expressions
  expression          : LPAREN expression RPAREN                                              #parensExpression
                      | left=expression op=(PLUS|MINUS) right=expression                      #infixExpression
                      | left=expression op=(MULTIPLY|DIVIDE) right=expression                 #infixExpression
                      | left=expression log=(AND|OR) right=expression                         #logicalExpression
                      | left=expression comp=(LESS|GREATER|EQUALGREATER|EQUALLESS|NOTEQUAL|ISEQUAL) right=expression           #comparisonExpression
                      | value=NUM #numberExpression; */