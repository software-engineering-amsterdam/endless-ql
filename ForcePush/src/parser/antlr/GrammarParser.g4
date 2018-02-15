parser grammar GrammarParser;

options { tokenVocab=Grammar; }

//RULES
a : b INT;
b   : ID ID;