// pegjs parser definition
stylesheet      = ws comment* ws "stylesheet" ws "\"" name:text "\"" ws "{" ws
                  pages: page*
                  ws comment* ws
                  "}" ws {
                    return new astQls.Stylesheet(name, pages, location());
                  }

page            = ws comment* ws "page" ws "\"" name:text "\"" ws "{" ws
                  sections: section*
                  d: default
                  ws comment* ws
                  "}" ws {
                    return new astQls.Page(name, sections, location(), d);
                  } / ws comment* ws "page" ws "\"" name:identifier "\"" ws "{" ws
                  sections: section*
                  ws comment* ws
                  "}" ws {
                    return new astQls.Page(name, sections, location());
                  }

section         = ws comment* ws "section" ws "\"" name:text "\"" ws "{" ws
                  questions: question*
                  sections: section*
                  d: default
                  ws comment* ws
                  "}" ws {
                    return new astQls.Section(name, sections, questions, location(), d);
                  } / ws comment* ws "section" ws "\"" name:text "\"" ws "{" ws
                  questions: question*
                  sections: section*
                  ws comment* ws
                  "}" ws {
                    return new astQls.Section(name, sections, questions, location());
                  } / ws comment* ws "section" ws "\"" name:identifier "\"" ws question:question {
                    return new astQls.Section(name, [], [question], location());
                  }

question        = ws comment* ws "question" ws name:identifier ws type:defaultWidget {
                    return new astQls.Question(name, type, location());
                  } /
                  ws comment* ws "question" ws name:identifier {
                    return new astQls.Question(name, new astQls.Widget(astQls.WidgetType.NONE, []), location());
                  }

default         = ws comment* ws "default" ws type:type ws "{" ws
                  styles:style*
                  widget: defaultWidget
                  ws comment* ws
                  "}" {
                    return new astQls.Default(type, widget, styles, location());
                  } / ws comment* ws "default" ws type:type ws widget:defaultWidget {
                    return new astQls.Default(type, widget, [], location());
                  }

defaultWidget   = ws comment* ws "widget" ws type:widget {
                    return type;
                  }

style           = ws comment* ws name:identifier ":" ws value:number {
                    return new astQls.Style(name, value, location());
                  } / ws comment* ws name:identifier ":" ws value:hex {
                    return new astQls.Style(name, value, location());
                  } / ws comment* ws name:identifier ":" ws "\"" value:ascii "\"" {
                    return new astQls.Style(name, value, location());
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

ws "whitespace" = [ \t\n\r]* {return;}
identifier 		  = [a-zA-Z0-9\-]+ {return text();}
number          = val:([0-9]+) {return new astQls.NumberValue(parseInt(text(), 10));}
hex             = "#" r:([0-9][0-9]) g:([0-9][0-9]) b:([0-9][0-9]) {
                    const parsedRed = parseInt(r[0] + r[1], 10);
                    const parsedGreen = parseInt(g[0] + g[1], 10);
                    const parsedBlue = parseInt(b[0] + b[1], 10);
                    return new astQls.RgbValue(parsedRed, parsedGreen, parsedBlue);
                  }
expression 		  = [a-zA-Z0-9 +\-\/*><=]+ {return text();}
word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text();}
ascii           = [a-zA-Z]+ {return new astQls.StringValue(text());}
comment         = "//" (!lineTerminator .)*
lineTerminator  = "\n" / "\r\n" / "\r" / "\u2028" / "\u2029"

booleanType     = "boolean" { return astQl.QuestionType.BOOLEAN; }
stringType      = "string" { return astQl.QuestionType.STRING; }
integerType     = "integer" { return astQl.QuestionType.INT; }
dateType        = "date" { return astQl.QuestionType.DATE; }
decimalType     = "decimal" { return astQl.QuestionType.DECIMAL; }

radioWidgetType     = "radio" ws "(\"" yesValue:identifier "\"," ws "\"" noValue:identifier "\")" { return new astQls.Widget(astQls.WidgetType.RADIO, [yesValue, noValue]); }
checkboxWidgetType  = "checkbox" { return new astQls.Widget(astQls.WidgetType.RADIO, []); }
spinboxWidgetType   = "spinbox" { return new astQls.Widget(astQls.WidgetType.RADIO, []); }
