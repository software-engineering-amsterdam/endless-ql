grammar Test;
//r  : 'hello' ID ;         // match keyword hello followed by an identifier
//ID : [a-zA-Z]+ ;             // match lower-case identifiers
//WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines



ql : '{ ' NAME '  ' QUESTION ' }' ;        //QL              :=		NAME [QUESTION]+
NAME : [a-z] [a-zA-Z0-9]*;
QUESTION: NAME ' ' NAME ;

//QUESTION : (NAME  LABEL TYPE) | (NAME  LABEL TYPE EXPRESSION) | (CONDITION NAME  LABEL TYPE) | (CONDITION NAME  LABEL TYPE EXPRESSION);            //QUESTION		:=		NAME  LABEL TYPE | NAME  LABEL TYPE EXPRESSION | CONDITION NAME  LABEL TYPE | CONDITION NAME  LABEL TYPE EXPRESSION
//NAME : [a-z] [a-zA-Z0-9]*;         //NAME			:= 		[a-z, A-Z]+
//LABEL : '"' [a-zA-Z0-9]+ '"' ;//LABEL			:=		str
//TYPE :  'boolean' | 'string' | 'integer' | 'date' | 'decimal' | 'money';    //TYPE			:=		bool | str | int | date | float | money
//EXPRESSION : OBJECT ((BOOL OBJECT) | (COMPARISON OBJECT))+; //EXPRESSION		:=		(OBJECT [BOOL OBJECT | COMPARISON OBJECT]+)
//BOOL : '&&' | '||' | '!';   //BOOL			:=		&& | || | !
//COMPARISON : '<' | '>' | '>=' | '<=' | '!=' | '=='; //COMPARISON		:=		< | > | >= | <= | != | ==
////OBJECT : [a-z] [a-zA-Z0-9]+;
//CONDITION : 'if (' OBJECT (BOOL OBJECT)+ ')';
//WS : [ \t\r\n]+ -> skip ;