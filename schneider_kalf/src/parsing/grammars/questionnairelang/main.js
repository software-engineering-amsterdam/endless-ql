/*
 * Grammer for the Questionnaire Language
 *
 */

{
  // A base type for building the main tree layout
  function base(type, rest){
    return Object.assign({
        type:type,
        //location:location(),
    },
    rest)
  }

  // Building an expression, DRY
  function buildExpression(head, tail, type) {
    return tail.reduce(function(left, curr) {
      return {
        type,
        attributes:{
            operator:curr[1],
        },
        children:[
            Object.assign({name:"LEFT"}, left),
            Object.assign({name:"RIGHT"}, curr[3])
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

// Unary operation
unaryOps = "!"

// Boolean operations
boolOps = "&&" / "||"

// Compartive operations
compOps = "<=" / ">=" / "!=" / "==" / "<" / ">"

//Addative operations
addOps = "+" / "-"

//Multiplicant operations
multOps = "*" / "/"

// All includede aritmic operations
aritmicOps = multOps / addOps

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
 = expr:boolExpr {
     return base("EXPRESSION", {
       name: null,
       children:[expr],
     })
 }

// BooleanExpression
boolExpr
  = _ head:compExpr _ tail:(_(boolOps)_ compExpr)+ _{
    return buildExpression(head, tail, "BooleanExpression")
  }
  / compExpr

// ComparisonExpression
compExpr
  = _ head:additiveExpr _ tail:(_(compOps)_ additiveExpr)+ _{
    return buildExpression(head, tail, "ComparisonExpression")
  }
  / additiveExpr

// AdditiveExpression
additiveExpr
  = _ head:multiplicativeExpr _ tail:(_(addOps)_ multiplicativeExpr)+ _{
    return buildExpression(head, tail, "AdditiveExpression")
  }
  / multiplicativeExpr

// MultiplicativeExpression
multiplicativeExpr
  = _ head:primary _ tail:(_(multOps)_ primary)+ _{
    return buildExpression(head, tail, "MultiplicativeExpression")
  }
  / primary

primary
  = value
  / "("_ expr:boolExpr _ ")" { return expr; }
  / unarryExpr

// UnaryExpression
unarryExpr
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