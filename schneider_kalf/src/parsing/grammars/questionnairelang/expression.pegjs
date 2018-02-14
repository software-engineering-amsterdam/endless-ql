/* Test implementation to handle expressions with numbers
 * brackets () and multiple operators:
 *
 * Boolean operators: &&, ||, !
 * Comparison operators: <=, >=, !=, ==, <, >
 * Arithmatic operators: +, -, *, /
 * 
 * NOTE: this version doesn't work with identifiers)
 */ 

{
 function buildExpression(head, tail, type) {
    return tail.reduce(function(memo, curr) {
      return {
        type,
        operator: curr[1], 
        left: memo,
        right: curr[3]
      };
    }, head);
 }
}

start = expression

expression
 = bools:bools {
     return {
       type:"EXPRESSION",
       children:[bools],
     }
 }

bools
  = _ head:operation _ tail:(_(boolOps)_ operation)+ _{
    return buildExpression(head, tail, "BoolOp")
  }
  / operation

operation
  = _ head:additive _ tail:(_(compOps)_ additive)+ _{
    return buildExpression(head, tail, "CompOp")
  }
  / additive

additive
  = _ head:multiplicative _ tail:(_("+" / "-")_ multiplicative)+ _{
    return buildExpression(head, tail, "BaseOp")
  }
  / multiplicative

multiplicative
  = _ head:primary _ tail:(_("*" / "/")_ primary)+ _{
    return buildExpression(head, tail, "BaseOp")
  }
  / primary

primary
  = number
  / "("_ bools:bools _ ")" { return bools; }

number
  = digits:[0-9]+ { return digits.join(""); }

comment = "//" (!lb .)*
ws = [ \t]
_ = (ws / comment)*
lb = [ \t\r\n]

boolOps = "&&" / "||" / "!"
compOps = "<=" / ">=" / "!=" / "==" / "<" / ">"
baseOps = "+" / "-" / "*" / "/"