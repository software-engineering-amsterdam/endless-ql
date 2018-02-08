/*
form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean

  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
  }

}
*/

grammar QL;

@parser::header
{
    package org.uva.sea.ql.parser.antlr;
    import org.uva.sea.ql.parser.parseObject.Form;
    import org.uva.sea.ql.parser.parseObject.Statement;
}

@lexer::header
{
    package org.uva.sea.ql.parser.antlr;
    import org.uva.sea.ql.parser.parseObject.Form;
    import org.uva.sea.ql.parser.parseObject.Statement;
}

form returns [Form result]
    :   'form' Ident '{' stms=statements '}' { $result = new Form($Ident.text, $stms.result);  }
    ;

statements returns [Statement result]
    : 'statement' Ident {$result = new Statement($Ident.text); }
    ;

WS  :	(' ' | '\t' | '\n' | '\r') -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .*? '"';
