using Antlr4.Runtime;
using QLParser.AST.QLS;
using QLParser.Visitors.QLS;
using QLSGrammar;
using static QLSGrammar.QLSGrammarParser;

namespace QLParser
{
    public class QLSParserHelper
    {
        public static QLSNode Parse(string style)
        {
            AntlrInputStream inputStream = new AntlrInputStream(style);
            QLSGrammarLexer QLSLexer = new QLSGrammarLexer(inputStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(QLSLexer);
            QLSGrammarParser QLSParser = new QLSGrammarParser(commonTokenStream);

            StylesheetContext context = QLSParser.stylesheet();
            StylesheetVisitor visitor = new StylesheetVisitor();

            return visitor.VisitStylesheet(context);
        }
    }
}
