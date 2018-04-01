parser grammar GrammarParser;

options { tokenVocab=GrammarLexer; }


//RULES

compileUnit     :formStructure EOF;
mathUnit        :expression;

//Variables and operators
variable        :VAR
                |DEC
                |NUM
                |NOT VAR;


type            :(BOOL|STR|DATE|DECIMAL|MONEY|VAR+);

//Shortcuts
questionTypes       : (questionFormat|conditionalIf|questionAssignValue);
ifCondition         : (variable|expression);
nextCondition       : (conditionalElse|conditionalIfElse);

//Mathematical expressionnodes
expression        : LPAREN expression RPAREN                                                                            #parenthesisExpression
                  | NOT    expression                                                                                   #unaryExpression
                  | left=expression     op=(MULTIPLY|DIVIDE)    right=expression                                        #infixExpression
                  | left=expression     op=(PLUS|MINUS)         right=expression                                        #infixExpression
                  | left=expression     log=(AND|OR)            right=expression                                        #logicalExpression
                  | left=expression     comp=(LESS|GREATER|EQUALGREATER|EQUALLESS|NOTEQUAL|ISEQUAL) right=expression    #comparisonExpression
                  | value=(NUM|VAR|DEC)                                                                                 #numberExpression;


//Question types
questionFormat      : LABEL variable ASSIGN  type;

questionAssignValue : questionFormat EQUAL LPAREN* expression RPAREN*;

conditionalIf       : IF LPAREN ifCondition RPAREN LBRACE questionTypes+ RBRACE nextCondition*;

conditionalIfElse   : IFELSE LPAREN ifCondition RPAREN LBRACE questionTypes+ RBRACE nextCondition*;

conditionalElse     : ELSE LBRACE questionTypes+ RBRACE;

//questionMultiAns    : LABEL variable ASSIGN MULTIPLEANSWER LPAREN (variable) (COMMA variable)+ RPAREN;


//Class structure
formStructure       : FORM variable LBRACE questionTypes* RBRACE;

