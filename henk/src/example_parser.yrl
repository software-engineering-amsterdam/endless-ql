Nonterminals list elems elem main questions question header.
Terminals '[' ']' ',' int atom '{' '}' identifier string_val type form.
% Rootsymbol list.
Rootsymbol main.

% list -> '[' ']'       : [].
% list -> '[' elems ']' : '$2'.

% elems -> elem           : ['$1'].
% elems -> elem ',' elems : ['$1'|'$3'].

% elem -> int  : extract_token('$1').
% % elem -> atom : extract_token('$1').
% elem -> list : '$1'.

main -> header '{' questions '}'              : ['$1', '$3'].
header -> form identifier                     : extract_token('$2').
questions -> question                       : '$1'.
questions -> question questions             : ['$1', '$2'].

question -> identifier string_val type      : [{identifier, extract_token('$1')}, '$2', '$3'].
Erlang code.

extract_token({_Token, _Line, Value}) -> Value.
extract_form({_Token, Value}) -> Value.
