grammar QLS;

@parser::header
{
    package org.uva.sea.qls.parser.antlr;

    import org.uva.sea.qls.parser.elements.Page;
    import org.uva.sea.qls.parser.elements.Parameter;
    import org.uva.sea.qls.parser.elements.Stylesheet;

    import org.uva.sea.qls.parser.elements.style.Color;
    import org.uva.sea.qls.parser.elements.style.Font;
    import org.uva.sea.qls.parser.elements.style.FontSize;
    import org.uva.sea.qls.parser.elements.style.Widget;
    import org.uva.sea.qls.parser.elements.style.Width;
   	import org.uva.sea.qls.parser.elements.style.StyleSpecification;

    import org.uva.sea.qls.parser.elements.specification.DefaultStyle;
    import org.uva.sea.qls.parser.elements.specification.Question;
    import org.uva.sea.qls.parser.elements.specification.Section;
   	import org.uva.sea.qls.parser.elements.specification.Specification;

}

@lexer::header
{
    package org.uva.sea.qls.parser.antlr;
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
    :   s='section' IDENT '{' specifications '}' {
            $result = new Section($s, $IDENT.text, $specifications.result);
        }
    |
        s='section' IDENT specification {
            List<Specification> specifications = new ArrayList<>();
            specifications.add($specification.result);
            $result = new Section($s, $IDENT.text, specifications);
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
    :   w='widget' name=IDENT parameters {
        $result = new Widget($w, $name.text, $parameters.result);
    };


parameters returns [List<Parameter> result]
    @init  { List<Parameter> parameters = new ArrayList<>(); }
    @after { $result = parameters; }
    : parameter (',' parameters)? {
        parameters.add($parameter.result);
        parameters.addAll($parameters.result);
    }
    ;

parameter returns [Parameter result]
    : p='"' IDENT '"' {
        $result = new Parameter($p, $IDENT.text);
    };

defaultStyle returns [DefaultStyle result]
    : d= '{' styleSpecifications '}' {
        $result = new DefaultStyle($d, $styleSpecifications.result);
    };

styleSpecifications returns [List<StyleSpecification> result]
     @init  { List<StyleSpecification> specifications = new ArrayList<>(); }
     @after { $result = specifications; }
     : s='width' ':' NUM { specifications.add(new Width($s, $NUM.text)); }
     | s='font' ':' '"' IDENT '"' { specifications.add(new Font($s, $IDENT.text)); }
     | s='fontsize' ':' '"' IDENT '"' { specifications.add(new FontSize($s, $IDENT.text)); }
     | s='color' ':' '#' '"' IDENT '"' { specifications.add(new Color($s, $IDENT.text)); }
     | widget { specifications.add($widget.result); }
     ;


IDENT:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

NUM: [0-9]+;


