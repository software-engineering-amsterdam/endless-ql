grammar QLS;

@parser::header
{
    package org.uva.sea.languages.qls.parser.antlr;

    import org.uva.sea.languages.qls.parser.elements.Page;
    import org.uva.sea.languages.qls.parser.elements.Parameter;
    import org.uva.sea.languages.qls.parser.elements.Stylesheet;

    import org.uva.sea.languages.qls.parser.elements.style.Color;
    import org.uva.sea.languages.qls.parser.elements.style.Font;
    import org.uva.sea.languages.qls.parser.elements.style.FontSize;
    import org.uva.sea.languages.qls.parser.elements.style.Widget;
    import org.uva.sea.languages.qls.parser.elements.style.Width;
   	import org.uva.sea.languages.qls.parser.elements.style.StyleSpecification;

    import org.uva.sea.languages.qls.parser.elements.specification.DefaultStyle;
    import org.uva.sea.languages.qls.parser.elements.specification.Question;
    import org.uva.sea.languages.qls.parser.elements.specification.Section;
   	import org.uva.sea.languages.qls.parser.elements.specification.Specification;

    String removeQuotes(String string){
        return string.substring(1, string.length()-1);
    }
}

@lexer::header
{
    package org.uva.sea.languages.qls.parser.antlr;
}


stylesheet returns [Stylesheet result]
    :   f='stylesheet' IDENT '{' pages '}' {
            $result = new Stylesheet($f, $IDENT.text, $pages.result);
        }
    ;

pages returns [List<Page> result]
    @init  { List<Page> pages = new ArrayList<>(); }
    @after { $result = pages; }
    : (page {
        pages.add($page.result);
    })*
    ;

page returns [Page result]
    :   p='page' IDENT '{' specifications '}' {
            $result = new Page($p, $IDENT.text, $specifications.result);
        }
    ;

specifications returns [List<Specification> result]
    @init  { List<Specification> specifications = new ArrayList<>(); }
    @after { $result = specifications; }
    : (specification {
        specifications.add($specification.result);
    })*
    ;

specification returns [Specification result]
    :   section { $result = $section.result; }
    |   defaultStyle { $result = $defaultStyle.result; }
    |   question { $result = $question.result; }
    ;

section returns [Section result]
    :   s='section' STR '{' specifications '}' {
            $result = new Section($s, removeQuotes($STR.text), $specifications.result);
        }
    |
        s='section' STR specification {
            List<Specification> specifications = new ArrayList<>();
            specifications.add($specification.result);
            $result = new Section($s, removeQuotes($STR.text), specifications);
        }
    ;

question returns [Question result]
    :  q='question' name=IDENT (widget)? {
           Widget widget = null;
           if($widget.text != null) {
                widget = $widget.result;
           }

           $result = new Question($q, $name.text, widget);
       }
    ;

widget returns [Widget result]
    :   w='widget' name=IDENT ('(' parameters ')')? {
        $result = new Widget($w, $name.text, $parameters.text != null ? $parameters.result : new ArrayList<>());
    };


parameters returns [List<Parameter> result]
    @init  { List<Parameter> parameters = new ArrayList<>(); }
    @after { $result = parameters; }
    : (parameter (',' parameters)? {
        parameters.add($parameter.result);

        if($parameters.text != null) {
            parameters.addAll($parameters.result);
        }
    })?
    ;

parameter returns [Parameter result]
    : p=STR {
        $result = new Parameter($p, $p.text);
    };

defaultStyle returns [DefaultStyle result]
    : d='default' type=IDENT styleSpecifications {
        $result = new DefaultStyle($d, $type.text, $styleSpecifications.result);
    };

styleSpecifications returns [List<StyleSpecification> result]
     @init  { List<StyleSpecification> specifications = new ArrayList<>(); }
     @after { $result = specifications; }
     : '{' (styleSpecification {
                specifications.add($styleSpecification.result);
           })* '}'
     | styleSpecification { specifications.add($styleSpecification.result); }
     ;

styleSpecification returns [StyleSpecification result]
    :  s='width' ':' NUM { $result = new Width($s, $NUM.text); }
     | s='font' ':' STR { $result = new Font($s, removeQuotes($STR.text)); }
     | s='fontsize' ':' NUM { $result = new FontSize($s, $NUM.text); }
     | s='color' ':' COLOR_CODE { $result = new Color($s, $COLOR_CODE.text); }
     | widget { $result = $widget.result; }
     ;



IDENT:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

NUM: ('0'..'9')+;

COLOR_CODE: '#'('a'..'f'|'A'..'F'|'0'..'9')+;

STR: '"' .*? '"';

WS  :	(' ' | '\t' | '\n' | '\r') -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

SINGLE_COMMENT : '//'  ~[\r\n]*  -> skip;