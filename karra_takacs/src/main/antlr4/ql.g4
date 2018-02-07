grammar ql;

whitespace : ' '
           | '\n'
           | '\r'
           | '\t'
           ;

form : whitespace? 'form' whitespace* '{' .* '}';
