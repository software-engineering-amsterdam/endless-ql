using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;

namespace Assignment1
{
    internal class Controller
    {
        public void ParseForm(string input)
        {
            ICharStream stream = CharStreams.fromstring(input);
            ITokenSource lexer = new QLLexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);
            QLParser.FContext context = parser.f();
            IQLListener listener = new QLListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.Walk(listener, context);
        }
    }
}
