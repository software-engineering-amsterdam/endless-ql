using Antlr4.Runtime.Tree;
using QLGrammar;
using QLParser.AST;
using QLParser.AST.QL;
using System;
using System.Linq;
using static QLGrammar.QLGrammarParser;

namespace QLParser.ParserVisitors.QL
{
    public class FormVisitor : QLGrammarBaseVisitor<FormNode>
    {
        public override FormNode VisitForm(FormContext context)
        {
            if (context == null)
                throw new ArgumentNullException("Context can't be null");

            if (context.children.Any(x => x.GetType() == typeof(ErrorNodeImpl)))
                return null;

            // Construct FormNode object to store the results in.
            var name = context.formName().GetText();
            FormNode node = new FormNode(Location.FromContext(context), name);

            // Get the sections
            SectionContext[] sectionContexts = context.section();
            SectionVisitor visitor = new SectionVisitor();
            foreach (SectionContext sectionContext in sectionContexts)
                node.AddNode(visitor.VisitSection(sectionContext));

            return node;
        }
    }
}