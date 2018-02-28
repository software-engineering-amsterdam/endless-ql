grammar QL;
//ToDo: Turkish Test
//ToDo: Escaped Characters
//ToDo: Clean - make sure no redundancy / orphans
//ToDo: Make sure the names are representative of what they do
//ToDo: Check for dangling else problem

//ToDo: ?multiple forms?
//ToDo: ?string concatination?
//ToDo: ?calculated string questionText?
//ToDo: ?Add money format? : CurrencyUnit [0-9]+('.')?([0-9][0-9] | ([0-9][0-9][0-9]))? yen= 3decimal, what about bitcoin?)
//ToDo: ?Should I add Exponents (^)?
//ToDo: ?Should I add unicode formfeed to whitespace (u+000C)?
//ToDo: ?Do I want increment decrement operators ('++','--')?

questionnaire:  ENTRYPOINT IDENTIFIER BEGINSCOPE content=statement* ENDSCOPE; 

statement : question (calculatedValue)?
          | conditional
          ;		

question: IDENTIFIER ':' TEXT questiontype 
        | TEXT IDENTIFIER  ':' questiontype 
        ;

calculatedValue: ASSIGN BEGINGROUP mathexpression ENDGROUP;

conditional: IFKEYWORD BEGINGROUP condition ENDGROUP BEGINSCOPE consequent=statement* ENDSCOPE 
             (ELSEKEYWORD BEGINSCOPE alternative=statement* ENDSCOPE)?;

questiontype: qtype=(BOOLTYPE     
            | STRINGTYPE   
            | INTTYPE      
            | DATETYPE     
            | DECIMALTYPE)
			;

condition : IDENTIFIER                         #questionId
	      | TEXT                               #textLiteral
          | conditionvalue                     #relativeLiteral
          | booleanvalue                       #booleanLiteral
          | BEGINGROUP condition ENDGROUP      #expressionGroup
		  | NEGATE condition                   #negationExpression
          | leftExpression=condition 
              relationaloperator 
      	    rightExpression=condition          #relativeExpression
		  | mathexpression                     #calcualationExpression
          ;

mathexpression : IDENTIFIER                              #numberId
               | mathvalue                               #numberLiteral
			   | BEGINGROUP mathexpression ENDGROUP      #mathexpressionGroup
		       | leftExpression=mathexpression 
      	           operator=(MULTIPLY | DIVIDE) 
      		     rightExpression=mathexpression          #multiplyDivideExpression
      	       | leftExpression=mathexpression 
      	           operator=(ADD | MINUS) 
      		     rightExpression=mathexpression          #addSubtractExpression
			   ;

relationaloperator: ISEQUAL 
                  | ISNOTEQUAL 
                  | ISGREATERTHAN 
                  | ISGREATERTHANOREQUAL 
				  | ISLESSTHAN 
				  | ISLESSTHANOREQUAL 
				  | booleanoperator
                  ;

booleanoperator: AND 
               | OR
               ;

booleanvalue: TRUE 
            | FALSE
            ;

mathvalue: INTEGER 
         | DECIMAL
         ;

conditionvalue: INTEGER 
              | DECIMAL 
			  | DATE
              ;

ENTRYPOINT: 'form';
BEGINSCOPE: '{';
ENDSCOPE: '}';
IFKEYWORD: 'if';
ELSEKEYWORD: 'else';
BEGINGROUP: '(';
ENDGROUP: ')';

BOOLTYPE: 'boolean';
STRINGTYPE: 'string';
INTTYPE: 'integer';
DATETYPE: 'date';
DECIMALTYPE: 'decimal';

ASSIGN: '=';
ISNOTEQUAL: '!=';
ISEQUAL: '==';
ISGREATERTHAN: '>';
ISGREATERTHANOREQUAL: '>=';
ISLESSTHAN: '<';
ISLESSTHANOREQUAL: '<=';
NEGATE: '!';
ADD: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';

AND: '&&';
OR: '||';

TRUE: ('true'| 'True'| 'TRUE');
FALSE: ('false' | 'False' | 'FALSE');

DATE: [0-9]?[0-9]'/'[0-9]?[0-9]'/'([0-9][0-9])?([0-9][0-9]);
DECIMAL: '-'?[0-9]+ '.' [0-9]+;
INTEGER: '-'?[0-9]+;

TEXT: '"' (~'"')* '"';
IDENTIFIER: [a-zA-Z] [a-zA-Z0-9_]* ;

NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip ;
LINECOMMENT: '//' ~[\r\n]* -> skip;
BLOCKCOMMENT: '/*' .*? '*/' -> skip;