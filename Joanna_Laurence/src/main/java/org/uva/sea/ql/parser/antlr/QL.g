grammar QL;

@parser::header
{
    package org.uva.sea.ql.parser.antlr;
    import org.uva.sea.ql.parser.elements.*;
    import org.uva.sea.ql.parser.elements.expressions.*;
    import org.uva.sea.ql.parser.elements.types.*;
}

@lexer::header
{
    package org.uva.sea.ql.parser.antlr;
}

form returns [Form result]
    :   f='form' IDENT '{' stms=statements '}' {
        $result = new Form($IDENT.text, $stms.result);
        $result.setLocation($f.getLine(),$f.getStartIndex());}
    ;

statements returns [Statements result]
    @init  { Statements statements = new Statements(); }
    @after { $result = statements; }
    : (stm=statement {
        statements.addStatement($stm.result);
        statements.setLocation($stm.start.getLine(),$stm.start.getStartIndex());
    })*
    ;

statement returns [ASTNode result]
    : quest=question { $result = $quest.result; }
    | cont=condition { $result = $cont.result; }
    ;

//TODO: .text is used to check if it is not null
question returns [Question result]
    : lab=label var=variable ':' t=type ('=' ex=expression)? {
        $result = new Question($lab.result, $var.result, $t.result,$ex.text == null ? null : $ex.result);
        $result.setLocation($lab.start.getLine(), $lab.start.getCharPositionInLine());
    };

label returns [String result]
    : STR {
        $result = $STR.text;
    };

variable returns [Var result]
    : IDENT {
        $result = new Var($IDENT.text);
        $result.setLocation($IDENT.getLine(), $IDENT.getCharPositionInLine());
    };

type returns [Type result]
    : TYPES {
        $result = new Type($TYPES.text);
        $result.setLocation($TYPES.getLine(), $TYPES.getCharPositionInLine());
    };

condition returns [Condition result]
    : i='if' '(' expr=expression ')' q=questionBlock {
        $result = new Condition($expr.result, $q.result);
        $result.setLocation($i.getLine(), $i.getCharPositionInLine());
    };

questionBlock returns [List<Question> result]
    @init  { ArrayList<Question> questions = new ArrayList<Question>(); }
    @after { $result = questions; }
    : '{' stms=questions '}' {questions.addAll($stms.result); }
    | stm=question {questions.add($stm.result);}
    ;

//To suport lists than only can contain questions
questions returns [List<Question> result]
    @init  { ArrayList<Question> questions = new ArrayList<Question>(); }
    @after { $result = questions; }
    : (stm=question {questions.add($stm.result);})*
    ;

expression returns [ASTNode result]
    : expr=orExpr {
        $result = $expr.result;
    };

orExpr returns [ASTNode result]
    : lhs=andExpr { $result = $lhs.result; } ( or='||' rhs=andExpr {
        $result = new Or($result, $rhs.result);
        $result.setLocation($or.getLine(), $or.getCharPositionInLine());
       })*
    ;

andExpr returns [ASTNode result]
    :   lhs=relExpr { $result=$lhs.result; } ( and='&&' rhs=relExpr {
        $result = new And($result, $rhs.result);
        $result.setLocation($and.getLine(), $and.getCharPositionInLine());
    } )*
    ;

relExpr returns [ASTNode result]
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
      $result.setLocation($op.getLine(), $op.getCharPositionInLine());
    })*
    ;

addExpr returns [ASTNode result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    {
      if ($op.text.equals("+")) {
        $result = new Add($result, $rhs.result);
      }
      if ($op.text.equals("-")) {
        $result = new Sub($result, $rhs.result);
      }
      $result.setLocation($op.getLine(), $op.getCharPositionInLine());
    })*
    ;

mulExpr returns [ASTNode result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr
    {
      if ($op.text.equals("*")) {
        $result = new Mul($result, $rhs.result);
      }
      if ($op.text.equals("/")) {
        $result = new Div($result, $rhs.result);
      }
      $result.setLocation($op.getLine(), $op.getCharPositionInLine());
    })*
    ;

unExpr returns [ASTNode result]
    :  plus='+' x=unExpr {
        $result = new Pos($x.result);
        $result.setLocation($plus.getLine(), $plus.getCharPositionInLine());
    }
    |  minus='-' x=unExpr {
        $result = new Neg($x.result);
        $result.setLocation($minus.getLine(), $minus.getCharPositionInLine());
    }
    |  exl='!' x=unExpr {
        $result = new Not($x.result);
        $result.setLocation($exl.getLine(), $exl.getCharPositionInLine());
    }
    |  p=primary    { $result = $p.result; }
    ;

primary returns [ASTNode result]
    : bool {$result = $bool.result; }
    | money { $result = $money.result; }
    | variable { $result = $variable.result; }
    | date { $result = $date.result; }
    | num {$result = $num.result;}
    | dec {$result = $dec.result; }
    | str {$result = $str.result; }
    | '(' expression ')' {$result = $expression.result;}
    ;

bool returns [ASTNode result]
    : BOOLEAN_TRUE {
        $result = new Bool(true);
        $result.setLocation($BOOLEAN_TRUE.getLine(), $BOOLEAN_TRUE.getCharPositionInLine());
    }
    | BOOLEAN_FALSE {
        $result = new Bool(false);
        $result.setLocation($BOOLEAN_FALSE.getLine(), $BOOLEAN_FALSE.getCharPositionInLine());
    };

num returns [ASTNode result]
    : INT {
        $result = new Int(Integer.parseInt($INT.text));
        $result.setLocation($INT.getLine(), $INT.getCharPositionInLine());
    };

dec returns [ASTNode result]
    : DECIMAL {
        $result = new Dec(Double.parseDouble($DECIMAL.text));
        $result.setLocation($DECIMAL.getLine(), $DECIMAL.getCharPositionInLine());
    };

str returns [ASTNode result]
    : STR {
        $result = new Str($STR.text);
        $result.setLocation($STR.getLine(), $STR.getCharPositionInLine());
    };

money returns [ASTNode result]
    : c=('$' | '€') v=DECIMAL {
        $result = new Money($c.text, Double.parseDouble($v.text));
        $result.setLocation($v.getLine(), $v.getCharPositionInLine());
    }

    | c=('$' | '€') v=INT {
        $result = new Money($c.text, Double.parseDouble($v.text));
        $result.setLocation($v.getLine(), $v.getCharPositionInLine());
    };

date returns [DateExpr result]
    : '@' day=INT month=INT year=INT '@' {
        $result = new DateExpr(Integer.parseInt($day.text), Integer.parseInt($month.text), Integer.parseInt($year.text));
        $result.setLocation($day.getLine(), $day.getCharPositionInLine());
    };

TYPES: ('money' | 'boolean' | 'string' | 'integer' | 'date' | 'decimal');

BOOLEAN_TRUE: ('true' | 'TRUE');

BOOLEAN_FALSE: ('false' | 'FALSE');

WS  :	(' ' | '\t' | '\n' | '\r') -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

IDENT:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT: ('0'..'9')+;

DECIMAL: ('0'..'9')+ '.' ('0'..'9')+;

STR: '"' .*? '"';
