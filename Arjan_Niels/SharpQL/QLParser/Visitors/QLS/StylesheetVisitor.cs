using Antlr4.Runtime.Misc;
using QLParser.AST.QLS;
using QLParser.AST.QLS.Enums;
using static QLSGrammar.QLSGrammarParser;

namespace QLParser.Visitors.QLS
{
    public class StylesheetVisitor : QLSGrammar.QLSGrammarBaseVisitor<QLSNode>
    {
        public override QLSNode VisitStylesheet([NotNull] StylesheetContext context)
        {
            string id = context.ID().GetText();
            var qlsNode = new QLSNode(QLSNodeType.Stylesheet, id);

            foreach (PageContext pageContext in context.page())
                qlsNode.AddNode(VisitPage(pageContext));

            return qlsNode;
        }

        public override QLSNode VisitPage([NotNull] PageContext context)
        {
            string id = Util.RemoveQuotes(context.TEXT().GetText());
            var qlsNode = new QLSNode(QLSNodeType.Page, id);

            foreach (SectionContext sectionContext in context.section())
                qlsNode.AddNode(VisitSection(sectionContext));

            return qlsNode;
        }

        public override QLSNode VisitSection([NotNull] SectionContext context)
        {
            string id = Util.RemoveQuotes(context.TEXT().GetText());
            var qlsNode = new QLSNode(QLSNodeType.Section, id);

            foreach (SectionContext sectionContext in context.section())
                qlsNode.AddNode(VisitSection(sectionContext));

            foreach (QuestionContext questionContext in context.question())
                qlsNode.AddNode(VisitQuestion(questionContext));

            return qlsNode;
        }

        public override QLSNode VisitQuestion([NotNull] QuestionContext context)
        {
            string id = context.ID().GetText();

            if (context.widgetspecification() != null)
            {
                var widgetSpecificaitonVisitor = new WidgetSpecificationVisitor();
                var specification = widgetSpecificaitonVisitor.VisitWidgetspecification(context.widgetspecification());

                var qlsNode = new QLSNode(QLSNodeType.Question, id, new QLSStyle(specification));
                return qlsNode;
            }
            else
            {
                var qlsNode = new QLSNode(QLSNodeType.Question, id);
                return qlsNode;
            }
        }
    }
}