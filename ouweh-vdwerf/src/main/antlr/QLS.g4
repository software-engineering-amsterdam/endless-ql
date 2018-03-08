grammar QLS;

@header {
package antlr.generated;
}

stylesheet
    :   'stylesheet' id=ID OPEN_BRACKET statement* CLOSE_BRACKET
    ;



OPEN_BRACKET :  '{' ;
CLOSE_BRACKET : '}' ;
OPEN_PARENTH :  '(' ;
CLOSE_PARENTH : ')' ;
