grammar QL;

@parser::header
{
package org.uva.sea.languages.ql.parser.antlr;

import org.uva.sea.languages.ql.parser.elements.*;
import org.uva.sea.languages.ql.parser.elements.expressions.*;
import org.uva.sea.languages.ql.parser.elements.expressions.types.*;
import org.uva.sea.languages.ql.baseEvaluator.evaluate.valueTypes.MoneyType;

}

@lexer::header
{
package org.uva.sea.languages.ql.parser.antlr;
}

form returns [Form result]
    :   f='form' IDENT '{' stms=statements '}' {
            $result = new Form($f, $IDENT.text, $stms.result);
        }
    ;

statements returns [Statements result]
    @init  { Statements statements = new Statements(); }
    @after { $result = statements; }
    : (stm=statement {
        statements.addStatement($stm.result);
    })*
    ;

statement returns [Statement result]
    : quest=question { $result = $quest.result; }
    | cont=condition { $result = $cont.result; }
    ;

question returns [Question result]
    : lab=label var=variable ':' t=type ('=' ex=expression)? {
        $result = new Question($lab.start, $lab.result, $var.result, $t.result,$ex.text == null ? null : $ex.result);
    };

label returns [String result]
    : STR {
        $result = $STR.text;
    };

variable returns [Variable result]
    : IDENT {
        $result = new Variable($IDENT, $IDENT.text);
    };

type returns [Type result]
    : TYPES {
        $result = new Type($TYPES, $TYPES.text);
    };

condition returns [IfStatement result]
    : i='if' '(' expr=expression ')' thenBlock=block (e='else' elseBlock=block)? {
        Statements elseBlock = $elseBlock.text != null ? $elseBlock.result : null;
        $result = new IfStatement($i, $expr.result, $thenBlock.result, elseBlock);
    };

block returns [Statements result]
    @init  { Statements statements = new Statements(); }
    @after { $result = statements; }
    : '{' stms=statements '}' {statements = $stms.result; }
    ;

expression returns [Expression result]
    : expr=orExpr {
        $result = $expr.result;
    };

orExpr returns [Expression result]
    : leftHandSide=andExpr { $result = $leftHandSide.result; } ( or='||' rightHandSide=andExpr {
        $result = new Or($or, $result, $rightHandSide.result);
       })*
    ;

andExpr returns [Expression result]
    :   leftHandSide=relExpr { $result=$leftHandSide.result; } ( and='&&' rightHandSide=relExpr {
        $result = new And($and, $result, $rightHandSide.result);
    } )*
    ;

relExpr returns [Expression result]
    :   leftHandSide=addExpr { $result=$leftHandSide.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rightHandSide=addExpr
    {
      if ($op.text.equals("<")) {
        $result = new LessThan($op, $result, $rightHandSide.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LessOrEqual($op, $result, $rightHandSide.result);
      }
      if ($op.text.equals(">")) {
        $result = new GreaterThan($op, $result, $rightHandSide.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GreaterOrEqual($op, $result, $rightHandSide.result);
      }
      if ($op.text.equals("==")) {
        $result = new Equal($op, $result, $rightHandSide.result);
      }
      if ($op.text.equals("!=")) {
        $result = new NotEqual($op, $result, $rightHandSide.result);
      }
    })*
    ;

addExpr returns [Expression result]
    :   leftHandSide=mulExpr { $result=$leftHandSide.result; } ( op=('+' | '-') rightHandSide=mulExpr
    {
      if ($op.text.equals("+")) {
        $result = new Addition($op, $result, $rightHandSide.result);
      }
      if ($op.text.equals("-")) {
        $result = new Subtraction($op, $result, $rightHandSide.result);
      }
    })*
    ;

mulExpr returns [Expression result]
    :   leftHandSide=unExpr { $result=$leftHandSide.result; } ( op=( '*' | '/' ) rightHandSide=unExpr
    {
      if ($op.text.equals("*")) {
        $result = new Multiplication($op, $result, $rightHandSide.result);
      }
      if ($op.text.equals("/")) {
        $result = new Division($op, $result, $rightHandSide.result);
      }
    })*
    ;

unExpr returns [Expression result]
    :  plus='+' x=unExpr {
        $result = new Positive($plus, $x.result);
    }
    |  minus='-' x=unExpr {
        $result = new Negative($minus, $x.result);
    }
    |  exl='!' x=unExpr {
        $result = new Not($exl, $x.result);
    }
    |  p=primary    { $result = $p.result; }
    ;

primary returns [Expression result]
    : bool {$result = $bool.result; }
    | money { $result = $money.result; }
    | variable { $result = $variable.result; }
    | date { $result = $date.result; }
    | num {$result = $num.result;}
    | dec {$result = $dec.result; }
    | str {$result = $str.result; }
    | '(' expression ')' {$result = $expression.result;}
    ;

bool returns [Expression result]
    : BOOLEAN_TRUE {
        $result = new Bool($BOOLEAN_TRUE, true);
    }
    | BOOLEAN_FALSE {
        $result = new Bool($BOOLEAN_FALSE, false);
    };

num returns [Expression result]
    : INT {
        $result = new Int($INT, $INT.text);
    };

dec returns [Expression result]
    : DECIMAL {
        $result = new Decimal($DECIMAL, $DECIMAL.text);
    };

str returns [Expression result]
    : STR {
        $result = new Str($STR, $STR.text);
    };

money returns [Expression result]
    : c=('$' | '€') v=DECIMAL {
        $result = new Money($v, MoneyType.fromString($c.text), $v.text);
    }

    | c=('$' | '€') v=INT {
        $result = new Money($v, MoneyType.fromString($c.text), $v.text);
    };

date returns [DateExpr result]
    : '@' day=INT month=INT year=INT '@' {
        $result = new DateExpr($day, $day.text, $month.text, $year.text);
    };

TYPES: ('money_dollar' | 'money_euro' | 'boolean' | 'string' | 'integer' | 'date' | 'decimal');

BOOLEAN_TRUE: ('true' | 'TRUE');

BOOLEAN_FALSE: ('false' | 'FALSE');

WS  :	(' ' | '\t' | '\n' | '\r') -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

SINGLE_COMMENT : '//'  ~[\r\n]*  -> skip;

IDENT:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT: ('0'..'9')+;

DECIMAL: ('0'..'9')+ '.' ('0'..'9')+;

STR: '"' .*? '"';
