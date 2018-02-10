// pegjs parser definition
form            = ws "form" ws name:identifier ws "{" ws
                  statements: statement*
                "}" ws {
                  return {
                    name: name,
                    statements: statements
                  }
                }

statement       = q / ifStatement

ifStatement     = ws "if" ws "(" ws condition:identifier ws ")" ws "{" ws
                  statements:statement* ws
                  "}" ws
                  {
                    return {
                      statementType: "if",
                      condition: condition,
                      statements: statements
                    };
                  }

q "question"    = ws name:identifier ":" ws "\"" ws
                  label:text "\"" ws
                  type: type ws {
                    return {
                      statementType: "question",
                      name: name,
                      label: label,
                      type: type
                    }
                  }

text            = (ws word ws)+ {return text()}

// low-level

ws "whitespace" = [ \t\n\r]* { return; }

identifier 		= [a-zA-Z0-9]+ {return text()}

word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text()}

type            = "boolean" /
                  "string" /
                  "integer" /
                  "date" /
                  "decimal" /
                  "money"
