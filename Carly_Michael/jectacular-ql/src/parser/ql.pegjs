// pegjs parser definition
form            = ws comment* ws "form" ws name:identifier ws "{" ws
                  statements: statement*
                  ws comment* ws
                "}" ws {
                  return new ast.Form(name, statements, location());
                }

statement       = exprQuestion / q / ifElseStatement / ifStatement

ifStatement     = ws comment* ws "if" ws "(" ws condition:orExpression ws ")" ws "{" ws
                  statements:statement* ws
                  ws comment* ws
                  "}" ws {
                    return new ast.If(condition, statements, [], location());
                  }

ifElseStatement = ws comment* ws "if" ws "(" ws condition:orExpression ws ")" ws "{" ws
                  statements:statement* ws
                  ws comment* ws
                  "}" ws "else" ws "{" ws
                  elseStatements:statement* ws
                  ws comment* ws
                  "}" {
                    return new ast.If(condition, statements, elseStatements, location());
                  }

q "question"    = ws comment* ws name:identifier ":" ws "\"" ws
                  label:text "\"" ws
                  type: type ws {
                    return new ast.Question(name, label, type, location());
                  }

exprQuestion    = ws comment* ws name:identifier ":" ws "\"" ws
                  label:text "\"" ws
                  type: type ws
                  "=" ws expr:orExpression ws {
                    return new ast.ExpressionQuestion(name, label, type, expr, location());
                  }

// precedence:
//     - !                  (unary operators)
//     * / %                (binary operators: multiplicative)
//     + -                  (additive)
//     < > <= >=            (comparison)
//     == !=                (equality)
//     &&                   (logical and)
//     ||                   (logical or)

orExpression
  = head:andExpression tail:(ws "||" ws orExpression) {
      return new ast.OrExpression(head, tail[3], location());
} / v:andExpression {
        return v;
      }

andExpression
  = head:equalExpression tail:(ws "&&" ws andExpression) {
      return new ast.AndExpression(head, tail[3], location());
} / v:equalExpression {
        return v;
      }

equalExpression
  = head:inEqualExpression tail:(ws ("==") ws equalExpression) {
      return new ast.EqualExpression(head, tail[3], location());
} / v:inEqualExpression {
        return v;
      }

inEqualExpression
  = head:greaterThanExpression tail:(ws ("!=") ws inEqualExpression) {
      return new ast.InEqualExpression(head, tail[3], location());
} / v:greaterThanExpression {
        return v;
      }

greaterThanExpression
  = head:greaterThanEqualExpression tail:(ws (">") ws greaterThanExpression) {
      return new ast.GreaterThanExpression(head, tail[3], location());
} / v:greaterThanEqualExpression {
        return v;
      }
greaterThanEqualExpression
  = head:lessThanExpression tail:(ws (">=") ws greaterThanEqualExpression) {
      return new ast.GreaterThanEqualExpression(head, tail[3], location());
} / v:lessThanExpression {
        return v;
      }

lessThanExpression
  = head:lessThanEqualExpression tail:(ws ("<") ws lessThanExpression) {
      return new ast.LessThanExpression(head, tail[3], location());
} / v:lessThanEqualExpression {
        return v;
      }

lessThanEqualExpression
  = head:addExpression tail:(ws ("<=") ws lessThanEqualExpression) {
      return new ast.LessThanEqualExpression(head, tail[3], location());
} / v:addExpression {
        return v;
      }
addExpression
  = head:subtractExpression tail:(ws ("+") ws addExpression) {
      return new ast.AddExpression(head, tail[3], location());
} / v:subtractExpression {
        return v;
      }

subtractExpression
  = head:multiplyExpression tail:(ws ("-") ws subtractExpression) {
      return new ast.SubtractExpression(head, tail[3], location());
} / v:multiplyExpression {
        return v;
      }

multiplyExpression
  = head:divideExpression tail:(ws ("*") ws multiplyExpression) {
      return new ast.MultiplyExpression(head, tail[3], location());
} / v:divideExpression {
        return v;
      }

divideExpression
  = head:unaryExpression tail:(ws ("/") ws divideExpression) {
      return new ast.DivideExpression(head, tail[3], location());
} / v:unaryExpression {
        return v;
      }

unaryExpression
  = head:(ws ("!" / "-") ws unaryExpression) {
      return new ast.UnaryExpression(head[3], head[1], location());
} / v:primitive {
        return v;
      }

primitive
  = integer / boolean / date / string / variable / "(" expr:addExpression ")" { return expr; }

text            = (ws word ws)+ {return text();}

type            = booleanType /
                  stringType /
                  integerType /
                  dateType

// low-level

ws "whitespace" = [ \t\n\r]* { return; }
identifier 		  = [a-zA-Z0-9]+ {return text();}
expression 		  = [a-zA-Z0-9 +\-\/*><=]+ {return text();}
integer         = ws [0-9]+ ws { return new ast.Literal(ast.ExpressionType.NUMBER, parseInt(text(), 10), location()); }
boolean         = ws val:("true" / "false") ws { return new ast.Literal(ast.ExpressionType.BOOLEAN, val, location()); }
date            = ws "d" day:([0-9][0-9]) "-" month:([0-9][0-9]) "-" year:([0-9][0-9][0-9][0-9]) {
  let javascriptMonth = parseInt(month[0] + month[1], 10)-1;
  return new ast.Literal(ast.ExpressionType.DATE, new Date(Date.UTC(year[0] + year[1] + year[2] + year[3],
    javascriptMonth, day[0] + day[1], 0, 0, 0, 0)), location());
}
string          = ws "\"" val:identifier "\"" ws { return new ast.Literal(ast.ExpressionType.STRING, val, location()); }
variable        = ws val:identifier ws { return new ast.Variable(val, location()); }
word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text();}
comment         = "//" (!lineTerminator .)*
lineTerminator  = "\n" / "\r\n" / "\r" / "\u2028" / "\u2029"

booleanType     = "boolean" { return ast.QuestionType.BOOLEAN; }
stringType      = "string" { return ast.QuestionType.STRING; }
integerType     = "integer" { return ast.QuestionType.INT; }
dateType        = "date" { return ast.QuestionType.DATE; }
