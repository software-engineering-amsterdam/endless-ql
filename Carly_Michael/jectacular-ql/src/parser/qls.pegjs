// pegjs parser definition
stylesheet      = ws "stylesheet" ws "\"" name:identifier "\"" ws "{" ws
                  pages: page*
                  ws comment* ws
                "}" ws {
                  return new Stylesheet(name, pages, location());
                }

page            = ws comment* ws "page" ws "\"" name:identifier "\"" ws "{" ws
                  sections: section*
                  ws comment* ws
                "}" ws {
                  return new Page(name, sections, location());
                }

section         = ws comment* ws "section" ws "\"" name:identifier "\"" ws "{" ws
                  questions: question*
                  ws comment* ws
                "}" ws {
                  return new Section(name, questions, location());
                } / ws comment* ws "section" ws "\"" name:identifier "\"" ws questions:question {
                  return new Section(name, questions, location());
                }

question        = ws comment* ws "question" ws name:identifier ws "widget" ws type:widgetType {
                    return new Question(name, type, location());
                  } /
                  ws comment* ws "question" ws name:identifier {
                    return new Question(name, WidgetType.NONE, location());
                  }

default         = ws comment* ws "default" ws type:type ws "widget" ws widgetType:widgetType {
                  return new Default(type, widgetType, location());
                }

text            = (ws word ws)+ {return text();}

type            = booleanType /
                  stringType /
                  integerType /
                  dateType /
                  decimalType /
                  moneyType

widgetType      = radioWidgetType /
                  moneyWidgetType /
                  checkboxWidgetType /
                  spinboxWidgetType

// low-level

ws "whitespace" = [ \t\n\r]* { return; }
identifier 		  = [a-zA-Z0-9]+ {return text();}
expression 		  = [a-zA-Z0-9 +\-\/*><=]+ {return text();}
word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text();}
comment         = "//" (!lineTerminator .)*
lineTerminator  = "\n" / "\r\n" / "\r" / "\u2028" / "\u2029"

booleanType     = "boolean" { return QuestionType.BOOLEAN; }
stringType      = "string" { return QuestionType.STRING; }
integerType     = "integer" { return QuestionType.INT; }
dateType        = "date" { return QuestionType.DATE; }
decimalType     = "decimal" { return QuestionType.DECIMAL; }
moneyType       = "money" { return QuestionType.MONEY; }

radioWidgetType     = "radio" { return WidgetType.RADIO; }
moneyWidgetType     = "money" { return WidgetType.MONEY; }
checkboxWidgetType  = "checkbox" { return WidgetType.CHECKBOX; }
spinboxWidgetType   = "spinbox" { return WidgetType.SPINBOX; }
