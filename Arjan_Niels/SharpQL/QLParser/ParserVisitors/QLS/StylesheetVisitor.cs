using Antlr4.Runtime.Misc;
using QLParser.AST.QLS;
using QLParser.AST.QLS.Enums;
using System.Collections.Generic;
using static QLSGrammar.QLSGrammarParser;

namespace QLParser.ParserVisitors.QLS
{
    public class StylesheetVisitor : QLSGrammar.QLSGrammarBaseVisitor<QLSNode>
    {
        public override QLSNode VisitStylesheet([NotNull] StylesheetContext context)
        {
            string id = context.ID().GetText();
            var qlsNode = new QLSStructuralNode(QLSNodeType.Stylesheet, id);

            foreach (PageContext pageContext in context.page())
                qlsNode.AddNode(VisitPage(pageContext));

            return qlsNode;
        }

        public override QLSNode VisitPage([NotNull] PageContext context)
        {
            string id = Util.RemoveQuotes(context.TEXT().GetText());
            var styles = VisitDefaults(context.defaults());
            var qlsNode = new QLSStructuralNode(QLSNodeType.Page, id, styles);

            foreach (SectionContext sectionContext in context.section())
                qlsNode.AddNode(VisitSection(sectionContext));

            return qlsNode;
        }

        public override QLSNode VisitSection([NotNull] SectionContext context)
        {
            string id = Util.RemoveQuotes(context.TEXT().GetText());
            var qlsNode = new QLSStructuralNode(QLSNodeType.Section, id);

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

                var qlsNode = new QLSQuestionNode(id, new List<QLSStyle>() { new QLSStyle(specification) });
                return qlsNode;
            }
            else
            {
                var qlsNode = new QLSQuestionNode(id);
                return qlsNode;
            }
        }

        private new QLSStyle VisitDefaults(DefaultsContext context)
        {
            if (context == null)
                return new QLSStyle();

            var visitor = new DefaultsVisitor();
            return visitor.VisitDefaults(context);
        }

        private IList<QLSStyle> VisitDefaults(DefaultsContext[] contexts)
        {
            IList<QLSStyle> styles = new List<QLSStyle>();
            foreach (var defaultContext in contexts)
                styles.Add(VisitDefaults(defaultContext));

            return styles;
        }
    }
}