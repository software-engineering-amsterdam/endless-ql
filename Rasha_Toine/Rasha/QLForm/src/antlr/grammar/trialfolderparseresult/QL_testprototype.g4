grammar QL_testprototype;
options {  language=Java; }

@parser::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}

@lexer::header
{
	package antlr.grammar;
	import antlr.grammar.*;
}

@parser::members {
    private <T extends AstNode> T add(ParserRuleContext context, T node){
        node.setLocation(new CodeReference(context));
        return (T) node;
    }
}

/*^^^^^^^^^^^^^^^^^^^^*
	Parser Rules
*^^^^^^^^^^^^^^^^^^^^*/
literal returns [Literal result] // atom
   : MONEY { $result = add($ctx, new MoneyLiteral(BigDecimal.valueOf($MONEY.text))); }
   | INTEGER { $result =add($ctx, new IntegerLiteral(Integer.valueOf($INTEGER.text))); }
   | BOOLEAN { $result = add($ctx, new BooleanLiteral(Boolean.valueOf($BOOLEAN.text))); }
   | STRING { $result = add($ctx, new StringLiteral(String.valueOf($STRING))); }
   | DATE { $result = add($ctx, new DateLiteral(Date.valueOf($DATE.text))); }
   | DECIMAL { $result = add($ctx, new DecimalLiteral(Double.valueOf($DECIMAL))); }
  // | IDENT { $result = add($ctx, new Identifier($IDENT.text)); }
  ;

identifier returns [Identifier result]
   : IDENT { $result = add($ctx, new Identifier($IDENT.text)); }
  ;
   

questionType returns [Type result]
   : 'money' { $result = add($ctx, new MoneyType());}
   | 'integer' { $result = add($ctx, new IntegerType());}
   | 'boolean' { $result = add($ctx, new BooleanType());}
   | 'string' { $result = add($ctx, new StringType());}
   | 'date' { $result = add($ctx, new DateType());}
   | 'decimal' { $result = add($ctx, new DecimalType());}
  ; 

expr returns [Expression result]
  : identifier { $result = add($ctx, new IdentityExpression($identifier.result)); }
  | literal  { $result = add($ctx, new LiteralExpression($literal.result)); }
  | '!' expr { $result = add($ctx, new Not($expr.result)); }
  | '(' expr ')' { $result = $expr.result; }
  | '+' expr { $result = add($ctx, new Pos($expr.result)); }
  | '-' expr { $result = add($ctx, new Neg($expr.result)); }
  | left=expr ('&&') right=expr {$result =  add($ctx, new And($left.result, $right.result));}
  | left=expr ('||') right=expr {$result =  add($ctx, new Or($left.result, $right.result));}
  | left=expr ('==') right=expr {$result =  add($ctx, new Eq($left.result, $right.result));}
  | left=expr ('!=') right=expr {$result =  add($ctx, new NEq($left.result, $right.result));}
  | left=expr ('*') right=expr {$result =  add($ctx, new Mul($left.result, $right.result));}
  | left=expr (|'/') right=expr {$result =  add($ctx, new Div($left.result, $right.result));}
  | left=expr ('+') right=expr {$result =  add($ctx, new Add($left.result, $right.result));}
  | left=expr ('-') right=expr {$result =  add($ctx, new Sub($left.result, $right.result));}
  | left=expr ('>') right=expr {$result =  add($ctx, new GT($left.result, $right.result));}
  | left=expr ('>=') right=expr {$result =  add($ctx, new GEq($left.result, $right.result));}
  | left=expr ('<') right=expr {$result =  add($ctx, new LT($left.result, $right.result));}
  | left=expr ('<=') right=expr {$result =  add($ctx, new LEq($left.result, $right.result));}
 ;

statement returns [Statement result]
  : question { $result= add($ctx, $question.result); }
  | ifElseStatement { $result= add($ctx, $ifElseStatement.result); }
 ;

question  returns [Question result]
  : identifier ':' STRING questionType  // question to be answered
    {$result = add($ctx, new NormalQuestion($identifier.result, $STRING.text, $questionType.result));}
  | identifier ':' STRING questionType '(' expr ')' // question to be computed
    {$result = add($ctx, new ComputedQuestion($identifier.result, $STRING.text, $questionType.result, $expr.result));}
 ;
  
ifElseStatement returns [Statement result]//if or if-else-statement
   : 'if' '(' expr ')' ifBody = block 
      { $result = add($ctx, new IfThenStatement($expr.result, $ifBody.result));}
   | 'if' '(' expr ')' ifBody = block 'else' elseBody = block 
      { $result = add($ctx, new IfThenElseStatement($expr.result, $ifBody.result, $elseBody.result ));}
  ;
 
block  returns [Block result] // block of multiple statements
    locals [List<Statement> statements = new ArrayList<>();]
    @after{ $result = add($ctx, new Block(statements));}
    :'{' + (statement { $ctx.statements.add($statement.result); })+ '}'
 ;
 

form returns [Form result] // form
   : ('Form'|'form') identifier block { $result = add($ctx, new Form($identifier.result, $block.result)); }
  ;

// TODO check whether it is relevant to QL
//questionnaire: ('Questionnaire'|'questionnaire') identifier '{' form* '}';

/*^^^^^^^^^^^^^^^^^^^^^^^^*
	Lexer Rules - Tokens
*^^^^^^^^^^^^^^^^^^^^^^^^*/   
WHITESPACE   : (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);
SingleComment: '//' ~[\r\n]+ -> channel(HIDDEN);
BlockComment : '/*' .*? '*/' -> channel(HIDDEN);
IDENT        : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
DIGIT        : ('0'..'9');
INTEGER      : DIGIT+;
STRING       : '"' .*? '"';
BOOLEAN      : ('true'|'false');
TwoDigits    : ('0'..'9')('0'..'9');
QuadDigits   : ('0'..'9')('0'..'9')('0'..'9')('0'..'9');
MONEY        : DIGIT+ '.' TwoDigits;
DECIMAL      : DIGIT+ '.' DIGIT+;
DATE	     : TwoDigits'-'TwoDigits'-'QuadDigits;
