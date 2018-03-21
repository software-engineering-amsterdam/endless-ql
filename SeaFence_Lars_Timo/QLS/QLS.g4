grammar QLS;

/*
 * Parser rules
 */

stylesheet: 'stylesheet' stylesheet_id '{' page* '}' EOF;
page: 'page' page_id '{' section* default_style* '}';

section: 'section' section_id ('{' (question | section | default_style)* '}' | (question | section | default_style)*);

question: 'question' (var | var widget);
default_style: 'default' vartype ('{' default_options* widget '}' | widget);
default_options: (width | font | fontsize | color);

stylesheet_id: NAME;
page_id: NAME;
section_id: STR;
widget: 'widget' widget_type;
widget_type: 'checkbox'
           | 'radio("Yes", "No")'
           | 'spinbox'
           | 'slider'
           | 'text'
           | 'dropdown("Yes, "No")'
           ;

width: 'width:' NUMBER;
font: 'font:' STR;
fontsize: 'fontsize:' NUMBER;
color: 'color:' HEX;

var: NAME;
vartype: 'money'
       | 'boolean';

/*
 * Lexer rules
 */

WS: [ \t\n\r]+ -> skip;
COMMENT: '/*' .*? '*/' -> skip;

HEX: '#'([0-9A-F]) ([0-9A-F]) ([0-9A-F]) ([0-9A-F]) ([0-9A-F]) ([0-9A-F]);
NUMBER: [0-9]+;
STR: '"' .*? '"';
NAME: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;