using Antlr4.Runtime.Misc;
using QLGrammar;
using QLParser.AST;
using QLParser.AST.Nodes;
using System;
using static QLGrammar.QLGrammarParser;

namespace QLParser.Visitors
{
    public class QuestionVisitor : QLGrammarBaseVisitor<QuestionNode>
    {
        public override QuestionNode VisitQuestion([NotNull] QuestionContext context)
        {
            var id = context.ID().GetText();
            var questionRaw = context.TEXT().GetText();
            var question = Util.RemoveQuotes(questionRaw);

            var qtype = (QValueType)Enum.Parse(typeof(QValueType), context.QTYPE().GetText().ToUpper());

            return new QuestionNode(Location.FromContext(context), id, question, qtype);
        }
    }
}