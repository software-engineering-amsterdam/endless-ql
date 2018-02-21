parser grammar GrammarParser;

options { tokenVocab=GrammarLexer; }

//TESTING RULES, DO NOT UNCOMMENT
//a : b INT ;
//b : VAR VAR;
//r : VAR {System.out.println("If "+$VAR.text+" prints this works.");} ;
//{System.out.println("variable = "+$variable.text+"| Assign = "+$ASSIGN.text+"| Label = "+$LABEL.text+"| Type = "+$type.text);}

//RULES
variable    :VAR
            |NUM
            |NOT VAR;


decisions   :(AND|OR);
operator    :(IF|ELSE|IFELSE);
type        :(BOOL|STR|DATE|DECIMAL|MONEY);
arithmetic  :(PLUS|MINUS|MULTIPLY|DIVIDE);
comparison  :(LESS|GREATER|EQUALGREATER|EQUALLESS|NOTEQUAL|ISEQUAL);


expression      : variable arithmetic variable
                | variable decisions variable
                | variable comparison variable;

questionFormat  : LABEL variable ASSIGN type
                | questionFormat EQUAL LPAREN* expression RPAREN*;

conditional     : operator LPAREN (variable|expression) RPAREN LCURLYBRACKET (questionFormat|conditional)+ RCURLYBRACKET;

formStructure   : FORM variable LCURLYBRACKET (questionFormat|conditional)* RCURLYBRACKET;
