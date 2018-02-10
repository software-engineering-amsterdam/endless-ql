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

//TODO: question can be computed
// expression
question returns [Question result]
    : lab=label var=variable ':' t=type { $result = new Question($lab.result, $var.result, $t.result); }
    ;

//TODO: a condition can have nested conditions
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

variable returns [String result]
    : Ident { $result = $Ident.text; }
    ;

type returns [String result]
    : Ident { $result = $Ident.text; }
    ;

expression returns [Expr result]
    : expr=orExpr {$result = $expr.result;}
    | bool {$result = $bool.result; }
    | var=Ident { $result = new Var($var.text); }
    ;

bool returns [Expr result]
    : BOOLEAN_TRUE {$result = new Bool(true); }
    | BOOLEAN_FALSE {$result = new Bool(false); }
    ;

unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($x.result); }
    |  '-' x=unExpr { $result = new Neg($x.result); }
    |  '!' x=unExpr { $result = new Not($x.result); }
    ;

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


BOOLEAN_TRUE: ('true' | 'TRUE');

BOOLEAN_FALSE: ('false' | 'FALSE');

WS  :	(' ' | '\t' | '\n' | '\r') -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .*? '"';
