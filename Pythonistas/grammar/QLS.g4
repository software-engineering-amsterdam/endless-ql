grammar QLS;

// Parser
stylesheet: ID (BRACKETL page* BRACKETR) EOF;
page: ID (BRACKETL section* BRACKETR);
section: STRING (BRACKETL question* BRACKETR);
question: ID widget;
widget: checkbox;
checkbox: CHECKBOX;

// Lexer
CHECKBOX: 'checkbox';

ID: [A-Za-z][A-Za-z0-9_]*;
STRING: '"' (~('"' | '\\' | '\r' | '\n'))* '"';

BRACKETL : '{';
BRACKETR : '}';

SPACE: [ \t]+ -> skip;
NEWLINE: '\r'? '\n' -> skip;