grammar Test;
//r  : 'hello' ID ;         // match keyword hello followed by an identifier
//ID : [a-zA-Z]+ ;             // match lower-case identifiers
//WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines



ql :  'form' STRING '{' (question)+ '}' ;        //QL              :=		STRING [QUESTION]+
question : STRING ':'  LABEL TYPE  EXPRESSION?;
LABEL : '"' (STRING (' ')?)+ '"' ; // TODO: revise LABEL special chars
TYPE :  'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';
STRING : [a-zA-Z0-9?.!/s]+ ;
CAPITAL : [A-Z] STRING ;
LOWER: [a-z] STRING ;

            //QUESTION		:=		STRING  LABEL TYPE | STRING  LABEL TYPE EXPRESSION | CONDITION STRING  LABEL TYPE | CONDITION STRING  LABEL TYPE EXPRESSION
//STRING : [a-z] [a-zA-Z0-9]*;         //STRING			:= 		[a-z, A-Z]+
//LABEL : '"' [a-zA-Z0-9]+ '"' ;//LABEL			:=		str
//TYPE :  'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';    //TYPE			:=		bool | str | int | date | float | money
EXPRESSION : OBJECT ((BOOL OBJECT) | (COMPARISON OBJECT))+; //EXPRESSION		:=		(OBJECT [BOOL OBJECT | COMPARISON OBJECT]+)
BOOL : '&&' | '||' | '!';   //BOOL			:=		&& | || | !
COMPARISON : '<' | '>' | '>=' | '<=' | '!=' | '=='; //COMPARISON		:=		< | > | >= | <= | != | ==
OBJECT : [a-z] [a-zA-Z0-9]+;
condition : 'if (' OBJECT (BOOL OBJECT)+ ') {' (question)+ '}';
WS : [ \t\r\n]+ -> skip;
