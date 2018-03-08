using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using QLParser.AST.Nodes;
using QLParser.Visitors;
using System.Linq;
using static QLanguage.QLanguageParser;

namespace QLParser
{
    public class FormVisitor : QLanguage.QLanguageBaseVisitor<FormNode>
    {
        public override FormNode VisitFormDeclaration([NotNull] FormDeclarationContext context)
        {
            if (context.children.Any(x => x.GetType() == typeof(ErrorNodeImpl)))
                return null;

            // Construct FormNode object to store the results in.
            var name = context.formName().GetText();
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