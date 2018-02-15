using Antlr4.Runtime.Misc;
using QL_Parser.Models;
using QL_Parser.Visitors;
using static QLanguage.QLanguageParser;

namespace QL_Parser
{
    public class FormVisitor : QLanguage.QLanguageBaseVisitor<object>
    {
        public Form Form { get; set; }

        public override object VisitFormDeclaration([NotNull] FormDeclarationContext context)
        {
            // Construct form object to store the results in.
            Form form = new Form();

            //Get the formname
            FormNameContext formName = context.formName();
            form.Name = formName.GetText();

            // Get the sections
            SectionContext[] sectionContext = context.section();
            SectionVisitor visitor = new SectionVisitor();
            foreach (SectionContext ctx in sectionContext)
                form.Sections.Add(visitor.VisitSection(ctx));

            return form;
        }
    }
}