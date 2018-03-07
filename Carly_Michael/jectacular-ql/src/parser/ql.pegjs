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
      return new ast.LogicalExpression(head, tail[3], tail[1], location());
} / v:andExpression {
        return v;
      }

andExpression
  = head:equalityExpression tail:(ws "&&" ws andExpression) {
      return new ast.LogicalExpression(head, tail[3], tail[1], location());
} / v:equalityExpression {
        return v;
      }

equalityExpression
  = head:comparisonExpression tail:(ws ("==" / "!=") ws equalityExpression) {
      return new ast.LogicalExpression(head, tail[3], tail[1], location());
} / v:comparisonExpression {
        return v;
      }

comparisonExpression
  = head:addExpression tail:(ws (">" / "<" / ">=" / "<=") ws comparisonExpression) {
      return new ast.LogicalExpression(head, tail[3], tail[1], location());
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
                  dateType /
                  decimalType

// low-level

ws "whitespace" = [ \t\n\r]* { return; }
identifier 		  = [a-zA-Z0-9]+ {return text();}
expression 		  = [a-zA-Z0-9 +\-\/*><=]+ {return text();}
integer         = ws [0-9]+ ws { return new ast.Literal(ast.ExpressionType.NUMBER, parseInt(text(), 10), location()); }
boolean         = ws val:("true" / "false") ws { return new ast.Literal(ast.ExpressionType.BOOLEAN, val, location()); }
date            = ws "d" day:([0-9][0-9]) "-" month:([0-9][0-9]) "-" year:([0-9][0-9][0-9][0-9]) {
  let jsMonth = parseInt(month[0] + month[1], 10)-1;
  return new ast.Literal(ast.ExpressionType.DATE, new Date(Date.UTC(year[0] + year[1] + year[2] + year[3],
    jsMonth, day[0] + day[1], 0, 0, 0, 0)), location());
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
decimalType     = "decimal" { return ast.QuestionType.DECIMAL; }
