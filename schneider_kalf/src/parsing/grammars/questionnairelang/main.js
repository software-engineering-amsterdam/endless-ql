/*
 * Grammar for the Questionnaire Language
 *
 */

{
  // Global counter so assign unique id's
  var COUNTER = 1;
  
  // Increments and returns COUNTER value
  function getID() {
    return COUNTER++;
  }
  
  // A base type for building the main tree layout
  function base(type, rest){
    return Object.assign({
        id:getID(),
        type:type,
        //location:location(),
    },
    rest)
  }
  
  // Building an expression, DRY
  function buildExpression(head, tail, type) {
    return tail.reduce(function(memo, curr) {
      return {
        type,
        attributes:{
            operator:curr[1],
        },
        children:[
            {left:memo},
            {right:curr[3]}
        ]
      };
    }, head);
  }
  
  function fieldBody(type, name, label, expr){
    return base("FIELD", {
       attributes:{
         type,
         name,
         label,
       },
       children: expr? [expr] : null
    })
  }
}


start = call+

call = form:form _ lb* { return form}

allOps = compOps / boolOps / baseOps

unaryOps = "!"
boolOps = "&&" / "||"
compOps = "<=" / ">=" / "!=" / "==" / "<" / ">"
baseOps = "+" / "-" / "*" / "/"

type = "boolean" / "money" / "currency" / "date" 

// Field for pdf syntax
fieldPdfSyntax
 = _ name:name _ ":" _ label:string _ type:type _ expr:expression? lb*{
    console.log(name);
    return fieldBody(type, name, label, expr)
 }

// Field for github syntax
fieldGithubSyntax
 = _ label:string lb* name:name _ ":" _ type:type lb* expr:("=" lb* expression)? lb*{
    console.log(name, label, type, expr);
    return fieldBody(type, name, label, expr? expr[2] : null)
 }

block_if
 = "{" lb* e:(fieldGithubSyntax / fieldPdfSyntax / if)+ lb* "}" { 
     return base("BLOCK", {
       name:"THEN",
       children:e,
     })
 }

block_form
 = "{" lb* e:(fieldGithubSyntax / fieldPdfSyntax / if)+ lb* "}" { 
     return e
 }

form
 = _ type:"form" _ name:name _ childeren:block_form lb*{
     var attr = {attributes:{name,}, childeren}
     return base("FORM", attr)
}

if
 = s:"if" _ pre:(expression) _ block:block_if lb* {
     pre.name = "PREDICATE";
     var childeren = [pre,block];
     return base("IF", {childeren})
}

// Initiates an expression
expression
 = bools:bools {
     return {
       type:"EXPRESSION",
       name: null,
       children:[bools],
     }
 }

// BooleanExpression
bools
  = _ head:operation _ tail:(_(boolOps)_ operation)+ _{
    return buildExpression(head, tail, "BooleanExpression")
  }
  / operation

// ComparisonExpression
operation
  = _ head:additive _ tail:(_(compOps)_ additive)+ _{
    return buildExpression(head, tail, "ComparisonExpression")
  }
  / additive

// AdditiveExpression
additive
  = _ head:multiplicative _ tail:(_("+" / "-")_ multiplicative)+ _{
    return buildExpression(head, tail, "AdditiveExpression")
  }
  / multiplicative

// MultiplicativeExpression
multiplicative
  = _ head:primary _ tail:(_("*" / "/")_ primary)+ _{
    return buildExpression(head, tail, "MultiplicativeExpression")
  }
  / primary

primary
  = value
  / "("_ bools:bools _ ")" { return bools; }
  / unarryExpression

// UnaryExpression
unarryExpression
 = _ op:unaryOps value:primary _ {
     return {
        type:"UnaryExpression",
        attributes:{
            operator:op,
        },
        children: [value],
     }
  }
 
 
 
name 
 = v:([a-zA-Z]+) { 
     return v.join("")
   }

// Variable / Identifier?
variable
 = v:([a-zA-Z]+) {
     return {
       type: "VARIABLE",
       attributes:{
           name:v.join(""),
       }
     }
   }

// Integer
integer
  = digits:([-]?[0-9]+) {
      return {
        type: "INTEGER", 
        value: parseInt(digits.join(""), 10) 
      }
    }

// Currently only support doublequote String
string
 = '"' chars:doubleString* '"'{
     return chars.join('')
 }

doubleString
  = !('"' / "\\") char:. { return char; }
  / "\\" sequence:escapeSequence { return sequence; }

escapeSequence
  = "'"
  / '"'
  / "\\"
  / "b"  { return "\b";   }
  / "f"  { return "\f";   }
  / "n"  { return "\n";   }
  / "r"  { return "\r";   }
  / "t"  { return "\t";   }
  / "v"  { return "\x0B"; }



value = variable / integer / string
comment = "//" (!lb .)*
ws = [ \t]
_ = (ws / comment)*
lb = [ \t\r\n]