using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QL_Parser.Visitors;
using static QLanguage.QLanguageParser;

namespace QL_Parser
{
    public class FormVisitor : QLanguage.QLanguageBaseVisitor<FormNode>
    {
        public FormNode Form { get; set; }

        public override FormNode VisitFormDeclaration([NotNull] FormDeclarationContext context)
        {
            //Get the formname
            FormNameContext formName = context.formName();

            // Construct FormNode object to store the results in.
            var name = formName.GetText();
            FormNode node = new FormNode(name);

            // Get the sections
            SectionContext[] sectionContext = context.section();
            SectionVisitor visitor = new SectionVisitor();
            foreach (SectionContext ctx in sectionContext)
                node.AddNode(visitor.VisitSection(ctx));

            return node;
        }
    }
}