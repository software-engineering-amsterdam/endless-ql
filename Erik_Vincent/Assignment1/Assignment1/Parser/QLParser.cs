using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Assignment1.Model.QL.AST;

namespace Assignment1.Parser
{
    public class QLParser : QLBaseListener
    {
        private QuestionForm _form;
        private readonly ParseErrorHandler _errorHandler = new ParseErrorHandler();

        public override void ExitForm(QL.FormContext context)
        {
            _form = context.result;
        }

        public static QuestionForm ParseString(string input)
        {
            var listener = new QLParser();
            var errorListener = new QLErrorListener(listener._errorHandler);

            var stream = CharStreams.fromstring(input);
            var lexer = new QLLexer(stream);
            lexer.RemoveErrorListeners();
            lexer.AddErrorListener(errorListener);
            ITokenStream tokens = new CommonTokenStream(lexer);
            var parser = new QL(tokens);
            parser.RemoveErrorListeners();
            parser.AddErrorListener(errorListener);
            var context = parser.form();

            var walker = new ParseTreeWalker();
            walker.Walk(listener, context);

            if (listener._errorHandler.HasErrors)
                listener._errorHandler.ThrowParseException();
            return listener._form;
        }
    }
}
