parser grammar QL;

@header {
using Assignment1;
}

options { tokenVocab=QLLexer; }

file returns [List<QuestionForm> result]
	@init {
	$result = new List<QuestionForm>();
	}
	: (form
		{$result.Add($form.result);}
		)* EOF
	;
form returns [QuestionForm result]
	: FORM ID content
		{$result = new QuestionForm($ID.text, $content.result);}
	;	
content returns [List<Content> result]
	@init {
	$result = new List<Content>();
	}
	: OPEN_CB (question
		{$result.Add($question.result);}
		| ifstatement
		{$result.Add($ifstatement.result);}
		)* CLOSE_CB
	;
question returns [Question result]
	: questionAssign
		{$result = $questionAssign.result;}
	| questionNorm
		{$result = $questionNorm.result;}
	;
questionAssign returns [Question result]
	: questionNorm ASSIGN value
		{$result = $questionNorm.result;
		 $result.Value = $value.result;}
	| questionNorm ASSIGN expression
		{$result = $questionNorm.result;
		 $result.Computed = true;
		 $result.Expression = $expression.result;}
	;
questionNorm returns [Question result]
	: STRING ID SEP BOOLEAN
		{$result = new QuestionBool($ID.text, $STRING.text);}
	| STRING ID SEP MONEY
		{$result = new QuestionMoney($ID.text, $STRING.text);}
	;
ifstatement returns [IfStatement result]
	: IF OPEN_BR expression CLOSE_BR content1=content ELSE content2=content
		{$result = new IfElseStatement($expression.result, $content1.result, $content2.result);}
	| IF OPEN_BR expression CLOSE_BR content
		{$result = new IfStatement($expression.result, $content.result);}
	;
expression returns [Expression result]
	: value
		{$result = new ExpressionValue($value.result);}
	| OPEN_BR expression CLOSE_BR
		{$result = $expression.result;}
	| NOT expression
		{$result = new ExpressionNot($expression.result);}
	| left=expression ADD right=expression
		{$result = new ExpressionAdd($left.result, $right.result);}
	| left=expression SUB right=expression
		{$result = new ExpressionSub($left.result, $right.result);}
	| left=expression MULT right=expression
		{$result = new ExpressionMult($left.result, $right.result);}
	| left=expression DIV right=expression
		{$result = new ExpressionDiv($left.result, $right.result);}
	| left=expression GTEQ right=expression
		{$result = new ExpressionGreaterEqual($left.result, $right.result);}
	| left=expression LTEQ right=expression
		{$result = new ExpressionLessEqual($left.result, $right.result);}
	| left=expression GT right=expression
		{$result = new ExpressionGreater($left.result, $right.result);}
	| left=expression LT right=expression
		{$result = new ExpressionLess($left.result, $right.result);}
	| left=expression EQ right=expression
		{$result = new ExpressionEqual($left.result, $right.result);}
	| left=expression NEQ right=expression
		{$result = new ExpressionNotEqual($left.result, $right.result);}
	| left=expression AND right=expression
		{$result = new ExpressionAnd($left.result, $right.result);}
	| left=expression OR right=expression
		{$result = new ExpressionOr($left.result, $right.result);}
	| expressionId
		{$result = $expressionId.result;}
	;
expressionId returns [ExpressionId result]
	: ID
		{$result = new ExpressionId($ID.text);}
	;
value returns [dynamic result]
	: TRUE    {$result = true;}
	| FALSE   {$result = false;}
	| INTEGER {$result = int.Parse($INTEGER.text);}
	| DECIMAL {$result = decimal.Parse($DECIMAL.text);}
	| STRING  {$result = $STRING.text;}
	| DATE    {$result = DateTime.Parse($DATE.text);}
	;
