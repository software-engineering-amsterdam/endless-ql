using Antlr4.Runtime.Misc;
using QLParser.AST;
using static QLSGrammar.QLSGrammarParser;

namespace QLParser.Visitors.QLS
{
    public class StylesheetVisitor : QLSGrammar.QLSGrammarBaseVisitor<QLSNode>
    {
        public override QLSNode VisitStylesheet([NotNull] StylesheetContext context)
        {
            string ID = context.ID().GetText();
            var qlsNode = new QLSNode(QLSNodeType.Stylesheet, ID);

            return qlsNode;
        }
    }
}
