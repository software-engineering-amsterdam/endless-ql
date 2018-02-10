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

expression returns [String result]
    : Ident { $result = $Ident.text; }
    ;

WS  :	(' ' | '\t' | '\n' | '\r') -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .*? '"';
