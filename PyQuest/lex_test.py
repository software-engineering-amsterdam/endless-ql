import lex

# Build the lexer
lex = lex.lexer

# Read language syntax into string
f = open('./tests/test1.ql')
test_input = f.read()

# Parse input
lex.input(test_input)

# Print tokens of parsed input
while True:
    tok = lex.token()
    if not tok: break
    print(tok)
