grammar Test;
//r  : 'hello' ID ;         // match keyword hello followed by an identifier
//ID : [a-zA-Z]+ ;             // match lower-case identifiers
//WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines



ql : 'form' STRING '{' (question)+ '}' ;        //QL              :=		STRING [QUESTION]+
question : STRING ':' LABEL TYPE expression?;
LABEL : '"' (STRING (' ')?)+ '"' ; // TODO: revise LABEL special chars
TYPE :  'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';
expression : (STRING | ('('expression (BOOL | COMPARISON | ARITHMETICS) expression')'));
BOOL : '&&' | '||' | '!';   //BOOL			:=		&& | || | !
COMPARISON : '<' | '>' | '>=' | '<=' | '!=' | '=='; //COMPARISON		:=		< | > | >= | <= | != | ==
ARITHMETICS : '+' | '-' | '/' | '*' ;
STRING : [a-zA-Z0-9?.!]+ ;



CAPITAL : [A-Z] STRING ;
LOWER: [a-z] STRING ;
condition : 'if' '(' STRING (BOOL STRING)+ ')' '{' (question)+ '}';
WS : [ \t\r\n]+ -> skip;

            //QUESTION		:=		STRING  LABEL TYPE | STRING  LABEL TYPE EXPRESSION | CONDITION STRING  LABEL TYPE | CONDITION STRING  LABEL TYPE EXPRESSION
//STRING : [a-z] [a-zA-Z0-9]*;         //STRING			:= 		[a-z, A-Z]+
//LABEL : '"' [a-zA-Z0-9]+ '"' ;//LABEL			:=		str
//TYPE :  'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';    //TYPE			:=		bool | str | int | date | float | money