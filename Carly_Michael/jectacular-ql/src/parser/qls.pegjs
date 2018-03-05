// pegjs parser definition
stylesheet      = ws comment* ws "stylesheet" ws "\"" name:text "\"" ws "{" ws
                  pages: page*
                  ws comment* ws
                  "}" ws {
                    return new Stylesheet(name, pages, location());
                  }

page            = ws comment* ws "page" ws "\"" name:text "\"" ws "{" ws
                  sections: section*
                  d: default
                  ws comment* ws
                  "}" ws {
                    return new Page(name, sections, location(), d);
                  } / ws comment* ws "page" ws "\"" name:identifier "\"" ws "{" ws
                  sections: section*
                  ws comment* ws
                  "}" ws {
                    return new Page(name, sections, location());
                  }

section         = ws comment* ws "section" ws "\"" name:text "\"" ws "{" ws
                  questions: question*
                  sections: section*
                  d: default
                  ws comment* ws
                  "}" ws {
                    return new Section(name, sections, questions, location(), d);
                  } / ws comment* ws "section" ws "\"" name:text "\"" ws "{" ws
                  questions: question*
                  sections: section*
                  ws comment* ws
                  "}" ws {
                    return new Section(name, sections, questions, location());
                  } / ws comment* ws "section" ws "\"" name:identifier "\"" ws questions:question {
                    return new Section(name, [], questions, location());
                  }

question        = ws comment* ws "question" ws name:identifier ws type:defaultWidget {
                    return new Question(name, type, location());
                  } /
                  ws comment* ws "question" ws name:identifier {
                    return new Question(name, new Widget(WidgetType.NONE, []), location());
                  }

default         = ws comment* ws "default" ws type:type ws "{" ws
                  styles:style*
                  widget: defaultWidget
                  ws comment* ws
                  "}" {
                    return new Default(type, widget, styles, location());
                  } / ws comment* ws "default" ws type:type ws widget:defaultWidget {
                    return new Default(type, widget, [], location());
                  }

defaultWidget   = ws comment* ws "widget" ws type:widget {
                    return type;
                  }

style           = ws comment* ws name:identifier ":" ws value:number {
                    return new Style(name, value, location());
                  } / ws comment* ws name:identifier ":" ws value:hex {
                    return new Style(name, value, location());
                  } / ws comment* ws name:identifier ":" ws "\"" value:ascii "\"" {
                    return new Style(name, value, location());
                  }

text            = (ws word ws)+ {return text();}

type            = booleanType /
                  stringType /
                  integerType /
                  dateType /
                  decimalType

widget          = radioWidgetType /
                  checkboxWidgetType /
                  spinboxWidgetType

// low-level

ws "whitespace" = [ \t\n\r]* { return; }
identifier 		  = [a-zA-Z0-9]+ {return text();}
number          = [0-9]+
hex             = "#" r:([0-9][0-9]) g:([0-9][0-9]) b:([0-9][0-9]) {return "#" + r + g + b;}
expression 		  = [a-zA-Z0-9 +\-\/*><=]+ {return text();}
word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text();}
ascii           = [a-zA-Z]+ {return text();}
comment         = "//" (!lineTerminator .)*
lineTerminator  = "\n" / "\r\n" / "\r" / "\u2028" / "\u2029"

booleanType     = "boolean" { return QuestionType.BOOLEAN; }
stringType      = "string" { return QuestionType.STRING; }
integerType     = "integer" { return QuestionType.INT; }
dateType        = "date" { return QuestionType.DATE; }
decimalType     = "decimal" { return QuestionType.DECIMAL; }

radioWidgetType     = "radio" ws "(\"" yesValue:identifier "\"," ws "\"" noValue:identifier "\")" { return new Widget(WidgetType.RADIO, [yesValue, noValue]); }
checkboxWidgetType  = "checkbox" { return new Widget(WidgetType.RADIO, []); }
spinboxWidgetType   = "spinbox" { return new Widget(WidgetType.RADIO, []); }
