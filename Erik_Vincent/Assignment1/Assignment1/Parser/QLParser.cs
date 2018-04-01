using Antlr4.Runtime;
using Assignment1.Model.QL.AST;

namespace Assignment1.Parser
{
    public static class QLParser
    {
        public static QuestionForm ParseString(string input)
        {
            var errorHandler = new ParseErrorHandler();
            var errorListener = new QLErrorListener(errorHandler);

            var stream = CharStreams.fromstring(input);
            var lexer = new QLLexer(stream);
            lexer.RemoveErrorListeners();
            lexer.AddErrorListener(errorListener);
            ITokenStream tokens = new CommonTokenStream(lexer);
            var parser = new QL(tokens);
            parser.RemoveErrorListeners();
            parser.AddErrorListener(errorListener);
            var form = parser.form().result;

            if (errorHandler.HasErrors)
                errorHandler.ThrowParseException();
            return form;
        }
    }
}
