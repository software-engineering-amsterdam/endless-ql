grammar Qls;

compileUnit
	:	EOF
	;

NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip ;