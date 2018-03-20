parser grammar QL;

@header {
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;
using Type = Assignment1.Model.QL.AST.Type;
}

@parser::members
{
    private static string UnEscapeQLString(string input) => input.Substring(1, input.Length - 2).Replace("\"\"", "\"");
}

options { tokenVocab=QLLexer; }

form returns [QuestionForm result]
	: FORM ID statements EOF
		{$result = new QuestionForm($ID.text, $statements.result);}
	;	
statements returns [List<Statement> result]
	@init {
	$result = new List<Statement>();
	}
	: OPEN_CB (statements
			{$result.AddRange($statements.result);}
		)* CLOSE_CB
	| question
		{$result.Add($question.result);}
	| ifStatement
		{$result.Add($ifStatement.result);}
	;
question returns [Question result]
	: string ID SEP type ASSIGN value
		{$result = new NormalQuestion($ID.text, $string.result, $type.result, $value.result);}
	| string ID SEP type ASSIGN expression
		{$result = new ComputedQuestion($ID.text, $string.result, $type.result, $expression.result);}
	| string ID SEP type
		{$result = new NormalQuestion($ID.text, $string.result, $type.result);}
	;
type returns [Type result]
	: BOOLEAN_TYPE
		{$result = Type.Boolean;}
	| DATE_TYPE
		{$result = Type.Date;}
	| DECIMAL_TYPE
		{$result = Type.Decimal;}
	| INTEGER_TYPE
		{$result = Type.Integer;}
	| MONEY_TYPE
		{$result = Type.Money;}
	| STRING_TYPE
		{$result = Type.String;}
	;
ifStatement returns [IfStatement result]
	: IF OPEN_BR expression CLOSE_BR thenStatements=statements ELSE elseStatements=statements
		{$result = new IfStatement($expression.result, $thenStatements.result, $elseStatements.result);}
	| IF OPEN_BR expression CLOSE_BR statements
		{$result = new IfStatement($expression.result, $statements.result);}
	;
expression returns [IExpression result]
	: value
		{$result = $value.result;}
	| OPEN_BR expression CLOSE_BR
		{$result = $expression.result;}
	| NOT expression
		{$result = new Not($expression.result);}
	| left=expression ADD right=expression
		{$result = new Add($left.result, $right.result);}
	| left=expression SUB right=expression
		{$result = new Subtract($left.result, $right.result);}
	| left=expression MULT right=expression
		{$result = new Multiply($left.result, $right.result);}
	| left=expression DIV right=expression
		{$result = new Divide($left.result, $right.result);}
	| left=expression GTEQ right=expression
		{$result = new GreaterThanOrEqual($left.result, $right.result);}
	| left=expression LTEQ right=expression
		{$result = new LessThanOrEqual($left.result, $right.result);}
	| left=expression GT right=expression
		{$result = new GreaterThan($left.result, $right.result);}
	| left=expression LT right=expression
		{$result = new LessThan($left.result, $right.result);}
	| left=expression EQ right=expression
		{$result = new Equal($left.result, $right.result);}
	| left=expression NEQ right=expression
		{$result = new NotEqual($left.result, $right.result);}
	| left=expression AND right=expression
		{$result = new And($left.result, $right.result);}
	| left=expression OR right=expression
		{$result = new Or($left.result, $right.result);}
	| ID
		{$result = new Reference($ID.text);}
	;
value returns [IValue result]
	: TRUE
		{$result = new QLBoolean(true);}
	| FALSE
		{$result = new QLBoolean(false);}
	| DATE
		{$result = new QLDate(DateTime.Parse($DATE.text));}
	| DECIMAL
		{$result = new QLDecimal(decimal.Parse($DECIMAL.text));}
	| INTEGER
		{$result = new QLInteger(int.Parse($INTEGER.text));}
	| string
		{$result = new QLString($string.result);}
	;
string returns [string result]
	: STRING
		{$result = UnEscapeQLString($STRING.text);}
	;
