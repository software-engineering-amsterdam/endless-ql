using Antlr4.Runtime.Misc;
using QL_Parser.Analysis;
using QL_Parser.AST.Nodes;
using QLanguage;
using System;

namespace QL_Parser.Visitors
{
    public class QuestionVisitor : QLanguageBaseVisitor<QuestionNode>
    {
        public override QuestionNode VisitQuestion([NotNull] QLanguageParser.QuestionContext context)
        {
            var id = context.ID().GetText();
            var question = context.TEXT().GetText();
            var qtype = (QuestionType)Enum.Parse(typeof(QuestionType), context.QTYPE().GetText().ToUpper());


            // Register the new ID in the SymbolTable.
            SymbolTable.Add(id, qtype);

            return new QuestionNode(id, question, qtype);
        }
    }
}
