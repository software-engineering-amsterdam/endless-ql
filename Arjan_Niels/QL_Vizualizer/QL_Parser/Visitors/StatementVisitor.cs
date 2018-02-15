using Antlr4.Runtime.Misc;
using QL_Parser.Models;
using QLanguage;

namespace QL_Parser.Visitors
{
    public class StatementVisitor : QLanguageBaseVisitor<Statement>
    {
        public override Statement VisitStatement([NotNull] QLanguageParser.StatementContext context)
        {




            return base.VisitStatement(context);
        }
    }
}
