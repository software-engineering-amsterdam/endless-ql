// pegjs parser definition
stylesheet      = whitespace comment* whitespace "stylesheet" whitespace "\"" name:text "\"" whitespace "{" whitespace
                  pages: page*
                  whitespace comment* whitespace
                  "}" whitespace {
                    return new astQls.Stylesheet(name, pages, location());
                  }

page            = whitespace comment* whitespace "page" whitespace "\"" name:text "\"" whitespace "{" whitespace
                  sections: section*
                  d: default
                  whitespace comment* whitespace
                  "}" whitespace {
                    return new astQls.Page(name, sections, location(), d);
                  } / whitespace comment* whitespace "page" whitespace "\"" name:identifier "\"" whitespace "{" whitespace
                  sections: section*
                  whitespace comment* whitespace
                  "}" whitespace {
                    return new astQls.Page(name, sections, location());
                  }

section         = whitespace comment* whitespace "section" whitespace "\"" name:text "\"" whitespace "{" whitespace
                  questions: question*
                  sections: section*
                  d: default
                  whitespace comment* whitespace
                  "}" whitespace {
                    return new astQls.Section(name, sections, questions, location(), d);
                  } / whitespace comment* whitespace "section" whitespace "\"" name:text "\"" whitespace "{" whitespace
                  questions: question*
                  sections: section*
                  whitespace comment* whitespace
                  "}" whitespace {
                    return new astQls.Section(name, sections, questions, location());
                  } / whitespace comment* whitespace "section" whitespace "\"" name:identifier "\"" whitespace question:question {
                    return new astQls.Section(name, [], [question], location());
                  }

question        = whitespace comment* whitespace "question" whitespace name:identifier whitespace type:defaultWidget {
                    return new astQls.Question(name, type, location());
                  } /
                  whitespace comment* whitespace "question" whitespace name:identifier {
                    return new astQls.Question(name, new astQls.Widget(astQls.WidgetType.NONE, []), location());
                  }

default         = whitespace comment* whitespace "default" whitespace type:type whitespace "{" whitespace
                  styles:style*
                  widget: defaultWidget
                  whitespace comment* whitespace
                  "}" {
                    return new astQls.Default(type, widget, styles, location());
                  } / whitespace comment* whitespace "default" whitespace type:type whitespace widget:defaultWidget {
                    return new astQls.Default(type, widget, [], location());
                  }

defaultWidget   = whitespace comment* whitespace "widget" whitespace type:widget {
                    return type;
                  }

style           = whitespace comment* whitespace name:identifier ":" whitespace value:number {
                    return new astQls.Style(name, value, location());
                  } / whitespace comment* whitespace name:identifier ":" whitespace value:hex {
                    return new astQls.Style(name, value, location());
                  } / whitespace comment* whitespace name:identifier ":" whitespace "\"" value:ascii "\"" {
                    return new astQls.Style(name, value, location());
                  }

text            = (whitespace word whitespace)+ {return text();}

type            = booleanType /
                  stringType /
                  integerType /
                  dateType

widget          = radioWidget /
                  textWidget /
                  checkboxWidget /
                  spinboxWidget /
                  dropdownWidget /
                  sliderWidget

// low-level

whitespace = [ \t\n\r]* {return;}
identifier 		  = [a-zA-Z0-9\-]+ {return text();}
expression 		  = [a-zA-Z0-9 +\-\/*><=]+ {return text();}
comment         = "//" (!lineTerminator .)*
lineTerminator  = "\n" / "\r\n" / "\r" / "\u2028" / "\u2029"

number          = val:([0-9]+) {return new astQls.NumberValue(parseInt(text(), 10));}
hex             = "#" r:([0-9][0-9]) g:([0-9][0-9]) b:([0-9][0-9]) {
                    const parsedRed = parseInt(r[0] + r[1], 10);
                    const parsedGreen = parseInt(g[0] + g[1], 10);
                    const parsedBlue = parseInt(b[0] + b[1], 10);
                    return new astQls.RgbValue(parsedRed, parsedGreen, parsedBlue);
                  }
word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text();}
ascii           = [a-zA-Z]+ {return new astQls.StringValue(text());}

//types
booleanType     = "boolean" { return astQl.QuestionType.BOOLEAN; }
stringType      = "string" { return astQl.QuestionType.STRING; }
integerType     = "integer" { return astQl.QuestionType.INT; }
dateType        = "date" { return astQl.QuestionType.DATE; }

//widgets
radioWidget     = "radio" whitespace "(\"" yesValue:identifier "\"," whitespace "\"" noValue:identifier "\")" {
  return new astQls.Widget(astQls.WidgetType.RADIO, [yesValue, noValue]);
}
textWidget      = "text" { return new astQls.Widget(astQls.WidgetType.TEXT); }
checkboxWidget  = "checkbox" { return new astQls.Widget(astQls.WidgetType.CHECKBOX); }
spinboxWidget   = "spinbox" { return new astQls.Widget(astQls.WidgetType.SPINBOX); }
dropdownWidget  = "dropdown" whitespace "(\"" yesValue:identifier "\"," whitespace "\"" noValue:identifier "\")" {
  return new astQls.Widget(astQls.WidgetType.DROPDOWN, [yesValue, noValue]);
}
sliderWidget    = "slider" { return new astQls.Widget(astQls.WidgetType.SLIDER); }





