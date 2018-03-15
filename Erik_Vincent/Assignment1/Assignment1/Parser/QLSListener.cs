using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Assignment1.Model.QLS;

namespace Assignment1.Parser
{
    internal class QLSListener : QLSBaseListener
    {
        private Stylesheet _stylesheet;

        public override void ExitStylesheet(QLS.StylesheetContext context)
        {
            _stylesheet = context.result;
        }

        internal static Stylesheet ParseString(string input)
        {
            QLSListener listener = new QLSListener();
            ICharStream stream = CharStreams.fromstring(input);
            ITokenSource lexer = new QLSLexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            QLS parser = new QLS(tokens);
            QLS.StylesheetContext context = parser.stylesheet();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.Walk(listener, context);
            return listener._stylesheet;
        }
    }
}
