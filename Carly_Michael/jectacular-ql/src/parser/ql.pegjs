// pegjs parser definition

form            = ws "form" ws name:identifier ws "{" ws
                  questions: (ws q ws)*
                "}"

q "question"    = name:identifier ":" ws "\"" ws
                  label:text "\"" ws
                  type: type

text            = (ws word ws)+

// low-level

ws "whitespace" = [ \t\n\r]*

identifier 		= [a-zA-Z0-9]+

word            = [a-zA-Z0-9\:\?\\\/\.\,\;\!]+

type            = "boolean" /
                  "string" /
                  "integer" /
                  "date" /
                  "decimal" /
                  "money"
