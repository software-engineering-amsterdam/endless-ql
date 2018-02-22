parser grammar QlParser;

options { tokenVocab=QlLexer; }

expr: NUMBER operation NUMBER;

operation: (ADD | SUB | MUL | DIV);
