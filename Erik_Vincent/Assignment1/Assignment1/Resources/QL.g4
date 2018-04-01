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
private static IValue NewUndefined(string type)
{
    switch (type)
    {
        case "boolean":
            return new QLBoolean();
            break;
        case "date":
            return new QLDate();
            break;
        case "decimal":
            return new QLDecimal();
            break;
        case "integer":
            return new QLInteger();
            break;
        case "money":
            return new QLMoney();
            break;
        case "string":
            return new QLString();
            break;
        default:
            throw new Exception();
            break;
    }
}
}

options { tokenVocab=QLLexer; }

form returns [QuestionForm result]
	: FORM ID statements EOF
		{$result = new QuestionForm(_localctx.Start.Line, $ID.text, $statements.result);}
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
		{$result = new NormalQuestion(_localctx.Start.Line, $ID.text, $string.result, $type.result, $value.result);}
	| string ID SEP type ASSIGN expression
		{$result = new ComputedQuestion(_localctx.Start.Line, $ID.text, $string.result, $type.result, $expression.result);}
	| string ID SEP type
		{$result = new NormalQuestion(_localctx.Start.Line, $ID.text, $string.result, $type.result, NewUndefined($type.text));}
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
		{$result = new IfStatement(_localctx.Start.Line, $expression.result, $thenStatements.result, $elseStatements.result);}
	| IF OPEN_BR expression CLOSE_BR statements
		{$result = new IfStatement(_localctx.Start.Line, $expression.result, $statements.result);}
	;
expression returns [IExpression result]
	: value
		{$result = $value.result;}
	| OPEN_BR expression CLOSE_BR
		{$result = $expression.result;}
	| NOT expression
		{$result = new Not(_localctx.Start.Line, $expression.result);}
	| left=expression ADD right=expression
		{$result = new Add(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression SUB right=expression
		{$result = new Subtract(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression MULT right=expression
		{$result = new Multiply(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression DIV right=expression
		{$result = new Divide(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression GTEQ right=expression
		{$result = new GreaterThanOrEqual(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression LTEQ right=expression
		{$result = new LessThanOrEqual(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression GT right=expression
		{$result = new GreaterThan(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression LT right=expression
		{$result = new LessThan(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression EQ right=expression
		{$result = new Equal(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression NEQ right=expression
		{$result = new NotEqual(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression AND right=expression
		{$result = new And(_localctx.Start.Line, $left.result, $right.result);}
	| left=expression OR right=expression
		{$result = new Or(_localctx.Start.Line, $left.result, $right.result);}
	| ID
		{$result = new Reference(_localctx.Start.Line, $ID.text);}
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
