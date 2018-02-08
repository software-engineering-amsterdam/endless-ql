grammar QL;

@parser::header
{
    package org.uva.sea.ql.parser.antlr;
    import org.uva.sea.ql.parser.parseObject.*;
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
    : stm=statement { statements.getStatementList().add($stm.result); }
    ;

statement returns [Statement result]
    @init  { Statement statement = new Statement(); }
    @after { $result = statement; }
    : quest=question { statement.setQuestion($quest.result); }
    | cond=condition { statement.setCondition($cond.result); }
    ;

question returns [Question result]
    : 'question' { $result = new Question(); }
    ;

condition returns [Condition result]
    : 'condition' { $result = new Condition(); }
    ;

WS  :	(' ' | '\t' | '\n' | '\r') -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .*? '"';
