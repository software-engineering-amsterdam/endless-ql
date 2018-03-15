// pegjs parser definition
form            = whitespace comment* whitespace "form" whitespace name:identifier whitespace "{" whitespace
                  statements: statement*
                  whitespace comment* whitespace
                "}" whitespace {
                  return new ast.Form(name, statements, location());
                }

statement       = expressionQuestion / question / ifElseStatement / ifStatement

ifStatement     = whitespace comment* whitespace "if" whitespace "(" whitespace condition:orExpression whitespace ")" whitespace "{" whitespace
                  statements:statement* whitespace
                  whitespace comment* whitespace
                  "}" whitespace {
                    return new ast.If(condition, statements, [], location());
                  }

ifElseStatement = whitespace comment* whitespace "if" whitespace "(" whitespace condition:orExpression whitespace ")" whitespace "{" whitespace
                  statements:statement* whitespace
                  whitespace comment* whitespace
                  "}" whitespace "else" whitespace "{" whitespace
                  elseStatements:statement* whitespace
                  whitespace comment* whitespace
                  "}" {
                    return new ast.If(condition, statements, elseStatements, location());
                  }

question   = whitespace comment* whitespace name:identifier ":" whitespace "\"" whitespace
                  label:text "\"" whitespace
                  type: type whitespace {
                    return new ast.Question(name, label, type, location());
                  }

expressionQuestion    = whitespace comment* whitespace name:identifier ":" whitespace "\"" whitespace
                  label:text "\"" whitespace
                  type: type whitespace
                  "=" whitespace expr:orExpression whitespace {
                    return new ast.ExpressionQuestion(name, label, type, expr, location());
                  }

orExpression
  = head:andExpression tail:(whitespace "||" whitespace orExpression) {
      return new ast.OrExpression(head, tail[3], location());
} / v:andExpression {
        return v;
      }

andExpression
  = head:equalExpression tail:(whitespace "&&" whitespace andExpression) {
      return new ast.AndExpression(head, tail[3], location());
} / v:equalExpression {
        return v;
      }

equalExpression
  = head:inEqualExpression tail:(whitespace "==" whitespace equalExpression) {
      return new ast.EqualExpression(head, tail[3], location());
} / v:inEqualExpression {
        return v;
      }

inEqualExpression
  = head:greaterThanExpression tail:(whitespace "!=" whitespace inEqualExpression) {
      return new ast.InEqualExpression(head, tail[3], location());
} / v:greaterThanExpression {
        return v;
      }

greaterThanExpression
  = head:greaterThanEqualExpression tail:(whitespace ">" whitespace greaterThanExpression) {
      return new ast.GreaterThanExpression(head, tail[3], location());
} / v:greaterThanEqualExpression {
        return v;
      }
greaterThanEqualExpression
  = head:lessThanExpression tail:(whitespace ">=" whitespace greaterThanEqualExpression) {
      return new ast.GreaterThanEqualExpression(head, tail[3], location());
} / v:lessThanExpression {
        return v;
      }

lessThanExpression
  = head:lessThanEqualExpression tail:(whitespace "<" whitespace lessThanExpression) {
      return new ast.LessThanExpression(head, tail[3], location());
} / v:lessThanEqualExpression {
        return v;
      }

lessThanEqualExpression
  = head:addExpression tail:(whitespace "<=" whitespace lessThanEqualExpression) {
      return new ast.LessThanEqualExpression(head, tail[3], location());
} / v:addExpression {
        return v;
      }
addExpression
  = head:subtractExpression tail:(whitespace "+" whitespace addExpression) {
      return new ast.AddExpression(head, tail[3], location());
} / v:subtractExpression {
        return v;
      }

subtractExpression
  = head:multiplyExpression tail:(whitespace "-" whitespace subtractExpression) {
      return new ast.SubtractExpression(head, tail[3], location());
} / v:multiplyExpression {
        return v;
      }

multiplyExpression
  = head:divideExpression tail:(whitespace "*" whitespace multiplyExpression) {
      return new ast.MultiplyExpression(head, tail[3], location());
} / v:divideExpression {
        return v;
      }

divideExpression
  = head:negativeExpression tail:(whitespace "/" whitespace divideExpression) {
      return new ast.DivideExpression(head, tail[3], location());
} / v:negativeExpression {
        return v;
      }

negativeExpression
  = head:(whitespace "-" whitespace negativeExpression) {
      return new ast.NegativeExpression(head[3], location());
} / v:negateExpression {
        return v;
      }

negateExpression
  = head:(whitespace "!" whitespace negateExpression) {
      return new ast.NegateExpression(head[3], location());
} / v:primitive {
        return v;
      }

primitive
  = date / integer / boolean / string / variable / "(" expr:addExpression ")" { return expr; }

text            = (whitespace word whitespace)+ {return text();}

type            = booleanType /
                  stringType /
                  integerType /
                  dateType

// low-level

whitespace = [ \t\n\r]* { return; }
identifier 		  = [a-zA-Z0-9]+ {return text();}
expression 		  = [a-zA-Z0-9 +\-\/*><=]+ {return text();}

//data
integer         = whitespace [0-9]+ whitespace { return new ast.Literal(ast.ExpressionType.NUMBER, parseInt(text(), 10), location()); }
boolean         = whitespace val:("true" / "false") whitespace { return new ast.Literal(ast.ExpressionType.BOOLEAN, val, location()); }
date            = whitespace day:([0-9][0-9]) "-" month:([0-9][0-9]) "-" year:([0-9][0-9][0-9][0-9]) {
  const javascriptMonth = parseInt(month[0] + month[1], 10)-1;
  return new ast.Literal(ast.ExpressionType.DATE, new Date(Date.UTC(year[0] + year[1] + year[2] + year[3],
    javascriptMonth, day[0] + day[1], 0, 0, 0, 0)), location());
}
string          = whitespace "\"" val:identifier "\"" whitespace { return new ast.Literal(ast.ExpressionType.STRING, val, location()); }

variable        = whitespace val:identifier whitespace { return new ast.Variable(val, location()); }
word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text();}
comment         = "//" (!lineTerminator .)*
lineTerminator  = "\n" / "\r\n" / "\r" / "\u2028" / "\u2029"

//types
booleanType     = "boolean" { return ast.QuestionType.BOOLEAN; }
stringType      = "string" { return ast.QuestionType.STRING; }
integerType     = "integer" { return ast.QuestionType.INT; }
dateType        = "date" { return ast.QuestionType.DATE; }
