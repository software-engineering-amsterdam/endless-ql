grammar QLanguage;

/*
 * Parser Rules
*/
formDeclaration : 'form' formName '{' '}';
formName : ID ;
question : ID ;


 /*
  * Lexer Rules
 */

ID : [a-zA-Z0-9]+ ;
WS: [ \t\n\r]+ -> skip ;