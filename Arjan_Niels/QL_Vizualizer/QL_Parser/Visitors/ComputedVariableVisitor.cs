using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QLanguage;
using System;

namespace QL_Parser.Visitors
{
    public class ComputedVariableVisitor : QLanguage.QLanguageBaseVisitor<ComputedNode>
    {
        public override ComputedNode VisitComputedVariable([NotNull] QLanguageParser.ComputedVariableContext context)
        {
            var id = context.ID().GetText();
            var textRaw = context.TEXT().GetText();
            var text = textRaw.Substring(1, textRaw.Length - 2);
            var qtype = (QValueType)Enum.Parse(typeof(QValueType), context.QTYPE().GetText().ToUpper());


            var statementVisitor = new StatementVisitor();
            var expression = statementVisitor.VisitStatement(context.statement());

            var computedNode = new ComputedNode(id, text, qtype, expression);
            return computedNode;
        }
    }
}
