using Antlr4.Runtime;
using Assignment1.Model.QLS.AST;

namespace Assignment1.Parser
{
    public static class QLSParser
    {
        public static StyleSheet ParseString(string input)
        {
            ICharStream stream = CharStreams.fromstring(input);
            ITokenSource lexer = new QLSLexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            var parser = new QLS(tokens);
            return parser.stylesheet().result;
        }
    }
}
