// pegjs parser definition

form            = ws "form" ws name:identifier ws "{" ws
                  questions: (ws q ws)*
                "}" {
                  return {
                    name: name,
                    questions: []
                  }
                }

q "question"    = name:identifier ":" ws "\"" ws
                  label:text "\"" ws
                  type: type

text            = (ws word ws)+ {return text()}

// low-level

ws "whitespace" = [ \t\n\r]*

identifier 		= [a-zA-Z0-9]+ {return text()}

word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+ {return text()}

type            = "boolean" /
                  "string" /
                  "integer" /
                  "date" /
                  "decimal" /
                  "money"
