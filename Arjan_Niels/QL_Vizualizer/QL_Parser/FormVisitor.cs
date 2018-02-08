using Antlr4.Runtime.Misc;
using QL_Parser.Models;
using static QLanguage.QLanguageParser;

namespace QL_Parser
{
    public class FormVisitor : QLanguage.QLanguageBaseVisitor<object>
    {
        public Form Form { get; set; }

        public override object VisitFormDeclaration([NotNull] FormDeclarationContext context)
        {
            FormNameContext formName = context.formName();

            Form form = new Form() { Name = formName.GetText() };
            return form;
        }
    }
}
