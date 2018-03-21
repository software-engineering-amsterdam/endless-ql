using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using Assignment1.Model.QL.AST;
using Assignment1.Parser;

namespace Assignment1.Converters
{
    internal class TextToQLAST : QLBaseListener
    {
        private QuestionForm _form;
        private QLParseErrorHandler _errorHandler = new QLParseErrorHandler();

        public override void ExitForm(QL.FormContext context)
        {
            _form = context.result;
        }

        internal static QuestionForm ParseString(string input)
        {
            TextToQLAST listener = new TextToQLAST();
            QLErrorListener errorListener = new QLErrorListener(listener._errorHandler);

            ICharStream stream = CharStreams.fromstring(input);
            var lexer = new QLLexer(stream);
            lexer.RemoveErrorListeners();
            lexer.AddErrorListener(errorListener);
            ITokenStream tokens = new CommonTokenStream(lexer);
            QL parser = new QL(tokens);
            parser.RemoveErrorListeners();
            parser.AddErrorListener(errorListener);
            QL.FormContext context = parser.form();

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.Walk(listener, context);

            if (listener._errorHandler.FormHasErrors)
                listener._errorHandler.ThrowQLParseException();
            return listener._form;
        }
    }
}
