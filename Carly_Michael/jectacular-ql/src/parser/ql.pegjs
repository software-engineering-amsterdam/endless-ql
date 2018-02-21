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
                  "=" ws "(" ws expr:Expression ws ")" ws {
                    return new ExpressionQuestion(name, label, type, expr, location());
                  }

Expression
  = head:Factor tail:(ws ("+" / "-" / "*" / "/") ws Factor)* {
      tail[0].unshift(head);
      return tail.reduce(function(result, element) {
        if (element[2] === "+") { result.push(new AddExpression(element[0], element[4])); return result; }
        if (element[2] === "-") { result.push(new SubtractExpression(element[0], element[4])); return result; }
        if (element[2] === "*") { result.push(new MultiplyExpression(element[0], element[4])); return result; }
        if (element[2] === "/") { result.push(new DivideExpression(element[0], element[4])); return result; }
      }, []);
}

Factor
  = "(" ws expr:Expression ws ")" { return expr; }
/ integer

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
integer         = ws [0-9]+ { return new IntegerExpression(parseInt(text(), 10)); }
word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text();}

booleanType     = "boolean" { return QuestionType.BOOLEAN; }
stringType      = "string" { return QuestionType.STRING; }
integerType     = "integer" { return QuestionType.INT; }
dateType        = "date" { return QuestionType.DATE; }
decimalType     = "decimal" { return QuestionType.DECIMAL; }
moneyType       = "money" { return QuestionType.MONEY; }
