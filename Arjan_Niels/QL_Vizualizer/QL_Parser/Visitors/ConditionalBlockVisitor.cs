using Antlr4.Runtime.Misc;
using QL_Parser.Models;
using QLanguage;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Parser.Visitors
{
    public class ConditionalBlockVisitor : QLanguage.QLanguageBaseVisitor<ConditionalBlock>
    {
        public override ConditionalBlock VisitConditionalBlock([NotNull] QLanguageParser.ConditionalBlockContext context)
        {
            return base.VisitConditionalBlock(context);
        }
    }
}
