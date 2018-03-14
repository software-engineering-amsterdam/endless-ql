grammar QLS;

@parser::header {
	package org.uva.jomi.qls.parser.antlr;
}

@lexer::header {
	package org.uva.jomi.qls.parser.antlr;
}

parse: stylesheetStmt* EOF ;

stylesheetStmt: 'stylesheet' IDENTIFIER '{' pageStmt* '}' ;

pageStmt: 'page' IDENTIFIER blockStmt ;

blockStmt: '{' command* '}' ;

command: sectionStmt
	   | defaultWidgetStmt
	   | questionStmt
	   ;
	   
sectionStmt: 'section' LABEL blockStmt ;

questionStmt: 'question' IDENTIFIER (widgetStmt)? ;

defaultWidgetStmt: 'default' TYPE widgetStmt ;

widgetStmt: 'widget' widgetType ; 
widgetType: widgetRadioStmt
		  | widgetSpinboxStmt
		  | widgetTextStmt
		  | widgetYesNoRadiosStmt
		  | widgetCheckboxStmt
		  | widgetDropdownStmt
		  | widgetYesNoDropdownStmt
		  ;
		  
widgetRadioStmt: 'radio' ( '(' LABEL ',' LABEL ')' )?;
widgetSpinboxStmt: 'spinbox' ;
widgetTextStmt: 'text' ;
widgetYesNoRadiosStmt: 'yesno-radios' ;
widgetCheckboxStmt: 'checkbox' ;
widgetDropdownStmt: 'dropdown' '(' LABEL (',' LABEL)* ')' ;
widgetYesNoDropdownStmt: 'yesno-dropdown' ;


TYPE: 'boolean' | 'string' | 'integer' | 'decimal' | 'date' | 'money';
IDENTIFIER: LETTER+ (LETTER|DIGIT)*  ;
LABEL: '"' .*? '"' ;

LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
WS: [ \t\r\n] -> skip ;

// Fragments (Prefix the rule with 'fragment': the rule will be used ONLY by other rules.)
fragment
DIGIT:	[0-9];
fragment
LETTER: [a-zA-Z];