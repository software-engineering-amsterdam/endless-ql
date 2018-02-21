% Definitions.

% INT        = [0-9]+
% ATOM       = :[a-z_]+
% WHITESPACE = [\s\t\n\r]

% Rules.

% {INT}         : {token, {int,  TokenLine, list_to_integer(TokenChars)}}.
% {ATOM}        : {token, {atom, TokenLine, to_atom(TokenChars)}}.
% \[            : {token, {'[',  TokenLine}}.
% \]            : {token, {']',  TokenLine}}.
% ,             : {token, {',',  TokenLine}}.
% {WHITESPACE}+ : skip_token.

% Erlang code.

% to_atom([$:|Chars]) -> list_to_atom(Chars).

Definitions.

% INT        = [0-9]+

FORM = form
TYPE = boolean
IDENTIFIER = [a-zA-Z0-9]+[:]?
STRING_VAL = ".+"
WHITESPACE = [\s\t\n\r]

Rules.

% {INT} : {token, {int,  TokenLine, list_to_integer(TokenChars)}}.
% {IDENTIFIER} : {token, {atom, TokenLine, to_string(TokenChars)}}.

{FORM}          : {token, {form, TokenLine}}.
{TYPE}          : {token, {type, TokenLine, TokenChars}}.
{IDENTIFIER}    : {token, {identifier, TokenLine, TokenChars}}.
{STRING_VAL}    : {token, {string_val, TokenLine,
                    escape_double_quote(TokenChars)
                  }}.
\{              : {token, {'{',  TokenLine}}.
\}              : {token, {'}',  TokenLine}}.
{WHITESPACE}+     : skip_token.

Erlang code.
escape_double_quote(Chars) -> string:strip(Chars, both, $").
