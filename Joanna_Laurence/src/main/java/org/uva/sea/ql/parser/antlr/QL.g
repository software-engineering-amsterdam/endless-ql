grammar QL;

@parser::header
{
    package org.uva.sea.ql.parser.antlr;
    import org.uva.sea.ql.parser.elements.*;
}

@lexer::header
{
    package org.uva.sea.ql.parser.antlr;
}

form returns [Form result]
    :   'form' IDENT '{' stms=statements '}' { $result = new Form($Ident.text, $stms.result);  }
    ;

statements returns [Statements result]
    @init  { Statements statements = new Statements(); }
    @after { $result = statements; }
    : (stm=statement { statements.addStatment($stm.result); })+
    ;

statement returns [Statement result]
    @init  { Statement statement = new Statement(); }
    @after { $result = statement; }
    : quest=question { statement.setQuestion($quest.result); }
    | cont=condition { statement.setCondition($cont.result); }
    ;


//TODO: .text is used to check if it is not null
question returns [Question result]
    : lab=label var=variable ':' t=type ('=' ex=expression)? {
        $result = new Question($lab.result, $var.result, $t.result,$ex.text == null ? null : $ex.result);
      }
    ;


//TODO: a condition can have nested conditions
//TODO: Ask if nested ifs are allowed
condition returns [Condition result]
    : 'if' '(' expression ')' statementBlock { $result = new Condition(); }
    ;

statementBlock returns [Statements result]
     @init  { Statements statements = new Statements(); }
     @after { $result = statements; }

     : '{' stms=statements '}' {$result = $stms.result; }
     | stm=statement { statements.addStatment($stm.result); }
     ;

label returns [String result]
    : STR { $result = $Str.text; }
    ;

variable returns [Var result]
    : IDENT { $result = new Var($Ident.text); }
    ;

type returns [Type result]
    : TYPES { $result = new Type($Types.text); }
    ;

expression returns [Expr result]
    : expr=orExpr {$result = $expr.result;}
    ;

bool returns [Expr result]
    : BOOLEAN_TRUE {$result = new Bool(true); }
    | BOOLEAN_FALSE {$result = new Bool(false); }
    ;

unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($x.result); }
    |  '-' x=unExpr { $result = new Neg($x.result); }
    |  '!' x=unExpr { $result = new Not($x.result); }
    |  p=primary    { $result = $p.result; }
    ;

primary returns [Expr result]
    : bool {$result = $bool.result; }
    | money { $result = $money.result; }
    | variable { $result = $variable.result; }
    | date { $result = $date.result; }
    | num {$result = $num.result;}
    | dec {$result = $dec.result; }
    | str {$result = $str.result; }
    | '(' expression ')' {$result = $expression.result;}
    ;

num returns [Expr result]
    : INT {$result = new Num(Integer.parseInt($Int.text));}
    ;

dec returns [Expr result]
    : DECIMAL {$result = new Dec(Double.parseDouble($Decimal.text));}
    ;

str returns [Expr result]
    : STR {$result = new Str($Str.text);}
    ;

money returns [Expr result]
    : c=('$' | '€') v=DECIMAL {
        $result = new Money($c.text, Double.parseDouble($v.text));
    }

    | c=('$' | '€') v=INT {
        $result = new Money($c.text, Double.parseDouble($v.text));
    };

mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr
    {
      if ($op.text.equals("*")) {
        $result = new Mul($result, $rhs.result);
      }
      if ($op.text.equals("/")) {
        $result = new Div($result, $rhs.result);
      }
    })*
    ;


addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    {
      if ($op.text.equals("+")) {
        $result = new Add($result, $rhs.result);
      }
      if ($op.text.equals("-")) {
        $result = new Sub($result, $rhs.result);
      }
    })*
    ;

relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr
    {
      if ($op.text.equals("<")) {
        $result = new LThan($result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LEq($result, $rhs.result);
      }
      if ($op.text.equals(">")) {
        $result = new GThan($result, $rhs.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GEq($result, $rhs.result);
      }
      if ($op.text.equals("==")) {
        $result = new Eq($result, $rhs.result);
      }
      if ($op.text.equals("!=")) {
        $result = new NEq($result, $rhs.result);
      }
    })*
    ;

andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And($result, $rhs.result); } )*
    ;


orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new Or($result, $rhs.result); } )*
    ;

date returns [DateExpr result]
    : '@' day=INT month=INT year=INT '@' { $result = new DateExpr(Integer.parseInt($day.text),
                                                                     Integer.parseInt($month.text),
                                                                     Integer.parseInt($year.text)
                                                                     ); };

TYPES: ('money' | 'boolean' | 'string' | 'integer' | 'date' | 'decimal');

BOOLEAN_TRUE: ('true' | 'TRUE');

BOOLEAN_FALSE: ('false' | 'FALSE');

WS  :	(' ' | '\t' | '\n' | '\r') -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

IDENT:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT: ('0'..'9')+;

DECIMAL: ('0'..'9')+ '.' ('0'..'9')+;

STR: '"' .*? '"';
