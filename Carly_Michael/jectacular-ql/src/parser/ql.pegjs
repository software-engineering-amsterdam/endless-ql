// pegjs parser definition
form            = ws "form" ws name:identifier ws "{" ws
                  statements: statement*
                "}" ws {
                  return new Form(name, statements, location());
                }

statement       = exprQuestion / q / ifStatement

ifStatement     = ws "if" ws "(" ws condition:identifier ws ")" ws "{" ws
                  statements:statement* ws
                  "}" ws
                  {
                    return new If(condition, statements, location());
                  }

q "question"    = ws name:identifier ":" ws "\"" ws
                  label:text "\"" ws
                  type: type ws {
                    return new Question(name, label, type, location());
                  }

exprQuestion    = ws name:identifier ":" ws "\"" ws
                  label:text "\"" ws
                  type: type ws
                  "=" ws expr:orExpression ws {
                    return new ExpressionQuestion(name, label, type, expr, location());
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
      return new LogicalExpression(head, tail[3], tail[1], location());
} / v:andExpression {
        return v;
      }

andExpression
  = head:equalityExpression tail:(ws "&&" ws andExpression) {
      return new LogicalExpression(head, tail[3], tail[1], location());
} / v:equalityExpression {
        return v;
      }

equalityExpression
  = head:comparisonExpression tail:(ws ("==" / "!=") ws equalityExpression) {
      return new LogicalExpression(head, tail[3], tail[1], location());
} / v:comparisonExpression {
        return v;
      }

comparisonExpression
  = head:addExpression tail:(ws (">" / "<" / ">=" / "<=") ws comparisonExpression) {
      return new LogicalExpression(head, tail[3], tail[1], location());
} / v:addExpression {
        return v;
      }

addExpression
  = head:mulExpression tail:(ws ("+" / "-") ws addExpression) {
      return new ArithmeticExpression(head, tail[3], tail[1], location());
} / v:mulExpression {
        return v;
      }

mulExpression
  = head:unaryExpression tail:(ws ("*" / "/") ws mulExpression) {
      return new ArithmeticExpression(head, tail[3], tail[1], location());
} / v:unaryExpression {
        return v;
      }

unaryExpression
  = head:(ws ("!" / "-") ws unaryExpression) {
      return new UnaryExpression(head[3], head[1], location());
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
                  decimalType /
                  moneyType

// low-level

ws "whitespace" = [ \t\n\r]* { return; }
identifier 		  = [a-zA-Z0-9]+ {return text();}
expression 		  = [a-zA-Z0-9 +\-\/*><=]+ {return text();}
integer         = ws [0-9]+ ws { return new Literal(ExpressionType.NUMBER, parseInt(text(), 10), location()); }
boolean         = ws val:("true" / "false") ws { return new Literal(ExpressionType.BOOLEAN, val, location()); }
date            = ws "d" day:([0-9][0-9]) "-" month:([0-9][0-9]) "-" year:([0-9][0-9][0-9][0-9]) {
  let jsMonth = parseInt(month[0] + month[1], 10)-1;
  return new Literal(ExpressionType.DATE, new Date(Date.UTC(year[0] + year[1] + year[2] + year[3],
    jsMonth, day[0] + day[1], 0, 0, 0, 0)), location());
}
string          = ws "\"" val:identifier "\"" ws { return new Literal(ExpressionType.STRING, val, location()); }
variable        = ws val:identifier ws { return new Literal(ExpressionType.VARIABLE, val, location()); }
word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text();}

booleanType     = "boolean" { return QuestionType.BOOLEAN; }
stringType      = "string" { return QuestionType.STRING; }
integerType     = "integer" { return QuestionType.INT; }
dateType        = "date" { return QuestionType.DATE; }
decimalType     = "decimal" { return QuestionType.DECIMAL; }
moneyType       = "money" { return QuestionType.MONEY; }
