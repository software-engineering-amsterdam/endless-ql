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
    :   'form' Ident '{' stms=statements '}' { $result = new Form($Ident.text, $stms.result);  }
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
    : Str { $result = $Str.text; }
    ;

variable returns [Var result]
    : Ident { $result = new Var($Ident.text); }
    ;

type returns [Type result]
    : Types { $result = new Type($Types.text); }
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
    | m=money { $result = $m.result; }
    | var=variable { $result = $var.result; }
    | d=date { $result = $d.result; }
    | Int {$result = new Num(Integer.parseInt($Int.text));}
    | Decimal {$result = new Dec(Double.parseDouble($Decimal.text)); }
    | Str {$result = new Str($Str.text);}
    | '(' ex=expression ')' {$result = $ex.result;}
    ;

money returns [Expr result]
    : c=('$' | '€') v=Decimal {
        $result = new Money($c.text, Double.parseDouble($v.text));
    }

    | c=('$' | '€') v=Int {
        $result = new Money($c.text, Double.parseDouble($v.text));
    };

mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr
    {
      if ($op.text.equals("*")) {
        $result = new Mul($result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
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
        $result = new LT($result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LEq($result, $rhs.result);
      }
      if ($op.text.equals(">")) {
        $result = new GT($result, $rhs.result);
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
    : '@' day=Int month=Int year=Int '@' { $result = new DateExpr(Integer.parseInt($day.text),
                                                                     Integer.parseInt($month.text),
                                                                     Integer.parseInt($year.text)
                                                                     ); };
Types: ('money' | 'boolean' | 'string' | 'integer' | 'date' | 'decimal');

BOOLEAN_TRUE: ('true' | 'TRUE');

BOOLEAN_FALSE: ('false' | 'FALSE');

WS  :	(' ' | '\t' | '\n' | '\r') -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Decimal: ('0'..'9')+ '.' ('0'..'9')+;

Str: '"' .*? '"';
