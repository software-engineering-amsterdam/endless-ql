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

statement : question calculatedValue    # calculatedQuestion
          | question                    # inputQuestion
          | conditionalStatement        # ifElseStatement
          ;		

question: IDENTIFIER ':' TEXT questionType 
        | TEXT IDENTIFIER  ':' questionType 
        ;

calculatedValue: ASSIGN BEGINGROUP mathExpression ENDGROUP;

conditionalStatement: IFKEYWORD BEGINGROUP expression ENDGROUP 
                      BEGINSCOPE consequentStatement ENDSCOPE 
                      (ELSEKEYWORD BEGINSCOPE alternativeStatement ENDSCOPE)?;

consequentStatement: statement*;

alternativeStatement: statement*;

questionType: chosenType=(BOOLTYPE     
            | STRINGTYPE   
            | INTTYPE      
            | DATETYPE     
            | DECIMALTYPE)
			;

expression : leftIdentifier=IDENTIFIER 
				relationalOperator 
		     rightIdentifier=IDENTIFIER                   #typeCheckExpression
           | booleanExpression                            #otherExpressions
		   ;

booleanExpression : IDENTIFIER                             #booleanQuestionIdentifier
	              | booleanValue                           #booleanLiteral
                  | BEGINGROUP booleanExpression ENDGROUP  #booleanExpressionGroup
		          | NEGATE booleanExpression               #negationExpression
				  | leftExpression=booleanExpression
					   operator=booleanOperator
				    rightExpression=booleanExpression      #andOrStatement
				  | leftExpression=booleanExpression 
                       operator=equalityOperator 
      	            rightExpression=booleanExpression      #booleanComparison
				  | relationalExpression                   #relativeExpression
                  ;

relationalExpression: leftText=(TEXT | IDENTIFIER) 
				         operator=equalityOperator 
					  rightText=(TEXT | IDENTIFIER)             #textComparison
		            | leftDate=(DATE | IDENTIFIER)
				         operator=relationalOperator 
					  rightDate=(DATE | IDENTIFIER)             #dateComparison 
		            | leftExpression=mathExpression 
				         operator=relationalOperator 
					  rightExpression=mathExpression            #mathComparison
		            ;

mathExpression : IDENTIFIER                              #numberVariableName
               | mathValue                               #numberLiteral
			   | BEGINGROUP mathExpression ENDGROUP      #mathExpressionGroup
		       | leftExpression=mathExpression 
      	           operator=(MULTIPLY | DIVIDE) 
      		     rightExpression=mathExpression          #multiplyDivideExpression
      	       | leftExpression=mathExpression 
      	           operator=(ADD | MINUS) 
      		     rightExpression=mathExpression          #addSubtractExpression
			   ;

relationalOperator: chosenOperator=(ISEQUAL 
                  | ISNOTEQUAL 
                  | ISGREATERTHAN 
                  | ISGREATERTHANOREQUAL 
				  | ISLESSTHAN 
				  | ISLESSTHANOREQUAL)
                  ;

equalityOperator: chosenOperator=(ISEQUAL | ISNOTEQUAL);

booleanOperator: chosenOperator=(AND | OR);

booleanValue: TRUE 
            | FALSE
            ;

mathValue: INTEGER 
         | DECIMAL
         ;

//ToDo: rename relational
conditionValue: INTEGER 
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