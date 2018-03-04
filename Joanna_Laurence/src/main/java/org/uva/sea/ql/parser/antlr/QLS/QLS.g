grammar QLS;

@parser::header
{
    package org.uva.sea.ql.parser.antlr.qls;

}

@lexer::header
{
    package org.uva.sea.ql.parser.antlr.qls;
}


stylesheet returns [Stylesheet result]
    :   f='stylesheet' IDENT '{' pages '}' {
            $result = new Stylesheet($f, $IDENT.text, $pages.result);
        }
    ;

pages returns [Pages result]
    @init  { Pages pages = new Pages(); }
    @after { $result = pages; }
    : (page {
        pages.addPage($page.result);
    })*
    ;

page returns [Page result]
    :   p='page' IDENT '{' sections '}' {
            $result = new Page($p, $IDENT.text, $sections.result);
        }
    ;

sections returns [Sections result]
    @init  { Sections sections = new Sections(); }
    @after { $result = sections; }
    : (section {
        sections.addSection($section.result);
    })*
    ;

section returns [Section result]
    :   s='section' IDENT '{' questions '}' {
            $result = new Section($s, $IDENT.text, $questions.result);
        }
    |
        s='section' IDENT question {
            Questions questions = new Questions();
            questions->addQuestion($question.result);
            $result = new Section($s, $IDENT.text, questions);
        }
    ;

questions returns [Questions result]
    @init  { Questions questions = new Questions(); }
    @after { $result = questions; }
    : (question {
        questions.addQuestion($question.result);
    })*
    ;


IDENT:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;


