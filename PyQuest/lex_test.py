import lex

# Read language syntax into string
f = open('./tests/test1.ql')
test_input = f.read()

# Build the lexer and try it out
m = lex.LexTokenizer()
m.test(test_input)

