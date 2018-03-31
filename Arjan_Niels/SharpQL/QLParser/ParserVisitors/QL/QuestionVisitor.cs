using QLGrammar;
using QLParser.AST;
using QLParser.AST.QL;
using System;
using static QLGrammar.QLGrammarParser;

namespace QLParser.ParserVisitors.QL
{
    public class QuestionVisitor : QLGrammarBaseVisitor<QuestionNode>
    {
        public override QuestionNode VisitQuestion(QuestionContext context)
        {
            if (context == null)
                throw new ArgumentNullException("Context can't be null");

            var id = context.ID().GetText();
            var questionRaw = context.TEXT().GetText();
            var question = Util.RemoveQuotes(questionRaw);

            var qtype = (QValueType)Enum.Parse(typeof(QValueType), context.QTYPE().GetText().ToUpper());

            return new QuestionNode(Location.FromContext(context), id, question, qtype);
        }
    }
}