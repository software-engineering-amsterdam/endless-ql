grammar QL;

/*
 * Parser Rules
 */

unit        : form*?;

form        : 'form' id=Identifier '{' block* '}';

block       : question | condition;

question    : label=String fieldDefinition;

fieldDefinition : variable=Identifier ':' fieldType=type assignment?;

assignment  : '=' expression;

type        : TYPE_BOOLEAN | TYPE_STRING | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE | typeMoneyWithCurrency | TYPE_MONEY ;

typeMoneyWithCurrency : TYPE_MONEY '(' currencyCode ')';

condition   : 'if' '(' cond=expression ')' '{' block* '}';

expression  : value
            | '(' expression ')'
            | unaryExpression
            | NOT unaryExpression
            | NOT '(' expression ')'
            | expression bop=(MULT|DIV) expression
            | expression bop=(PLUS|MINUS) expression
            | expression bop=(GT|GE|LT|LE|EQ|NEQ) expression
            | expression bop=(AND|OR) expression
;

unaryExpression
            : Identifier
            ;

value       : String
            | Integer
            | Decimal
            | moneyValue
            | boolValue
            | dateValue
            ;

boolValue
            : TRUE
            | FALSE
            ;

moneyValue
            : currencyCode '(' Decimal ')'
            ;

currencyCode : 'AED' | 'AFN' | 'ALL' | 'AMD' | 'ANG' | 'AOA' | 'ARS' | 'AUD' | 'AWG' | 'AZN' | 'BAM' | 'BBD' | 'BDT' | 'BGN' | 'BHD' | 'BIF' | 'BMD' | 'BND' | 'BOB' | 'BRL' | 'BSD' | 'BTN' | 'BWP' | 'BYN' | 'BZD' | 'CAD' | 'CDF' | 'CHF' | 'CLP' | 'CNY' | 'COP' | 'CRC' | 'CUC' | 'CUP' | 'CVE' | 'CZK' | 'DJF' | 'DKK' | 'DOP' | 'DZD' | 'EGP' | 'ERN' | 'ETB' | 'EUR' | 'FJD' | 'FKP' | 'GBP' | 'GEL' | 'GGP' | 'GHS' | 'GIP' | 'GMD' | 'GNF' | 'GTQ' | 'GYD' | 'HKD' | 'HNL' | 'HRK' | 'HTG' | 'HUF' | 'IDR' | 'ILS' | 'IMP' | 'INR' | 'IQD' | 'IRR' | 'ISK' | 'JEP' | 'JMD' | 'JOD' | 'JPY' | 'KES' | 'KGS' | 'KHR' | 'KMF' | 'KPW' | 'KRW' | 'KWD' | 'KYD' | 'KZT' | 'LAK' | 'LBP' | 'LKR' | 'LRD' | 'LSL' | 'LYD' | 'MAD' | 'MDL' | 'MGA' | 'MKD' | 'MMK' | 'MNT' | 'MOP' | 'MRU' | 'MUR' | 'MVR' | 'MWK' | 'MXN' | 'MYR' | 'MZN' | 'NAD' | 'NGN' | 'NIO' | 'NOK' | 'NPR' | 'NZD' | 'OMR' | 'PAB' | 'PEN' | 'PGK' | 'PHP' | 'PKR' | 'PLN' | 'PYG' | 'QAR' | 'RON' | 'RSD' | 'RUB' | 'RWF' | 'SAR' | 'SBD' | 'SCR' | 'SDG' | 'SEK' | 'SGD' | 'SHP' | 'SLL' | 'SOS' | 'SPL' | 'SRD' | 'STN' | 'SVC' | 'SYP' | 'SZL' | 'THB' | 'TJS' | 'TMT' | 'TND' | 'TOP' | 'TRY' | 'TTD' | 'TVD' | 'TWD' | 'TZS' | 'UAH' | 'UGX' | 'USD' | 'UYU' | 'UZS' | 'VEF' | 'VND' | 'VUV' | 'WST' | 'XAF' | 'XCD' | 'XDR' | 'XOF' | 'XPF' | 'YER' | 'ZAR' | 'ZMW' | 'ZWD'
            ;

dateValue : '@' Integer '-' Integer '-' Integer;



/*
 * Lexer Rules
 */

AND : '&&' ;
OR  : '||' ;

NOT : '!';  
ASSIG : '=';

MULT  : '*' ;
DIV   : '/' ;
PLUS  : '+' ;
MINUS : '-' ;

GT : '>' ;
GE : '>=' ;
LT : '<' ;
LE : '<=' ;
EQ : '==' ;
NEQ : '!=';

TYPE_BOOLEAN    : 'boolean';
TYPE_STRING     : 'string';
TYPE_INTEGER    : 'integer';
TYPE_DECIMAL    : 'decimal';
TYPE_MONEY      : 'money';
TYPE_DATE       : 'date';

TRUE    : 'true' | 'TRUE';
FALSE   : 'false' | 'FALSE';

WS  :	(' ' | '\t' | '\n' | '\r')  -> skip;

COMMENT : '/*' .*? '*/'  -> skip;

Identifier:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Integer: ('0'..'9')+;
String: '"' .*? '"';
Decimal: [0-9]+'.'[0-9]+;


