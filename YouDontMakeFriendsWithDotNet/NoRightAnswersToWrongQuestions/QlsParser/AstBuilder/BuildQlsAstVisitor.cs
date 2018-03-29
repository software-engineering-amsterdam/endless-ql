using System.Linq;
using QlsGrammar;
using QlsTransformer.Ast.Nodes;
using QlsTransformer.Ast.Tools;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QlsParser.AstBuilder
{
    public class BuildQlsAstVisitor : QlsBaseVisitor<Reference<IAstNode>>
    {
        private readonly IQlsAstFactory m_astFactory;
        private readonly IDomainItemLocator m_domainItemLocator;

        public BuildQlsAstVisitor(
            IQlsAstFactory astFactory,
            IDomainItemLocator domainItemLocator)
        {
            m_astFactory = astFactory;
            m_domainItemLocator = domainItemLocator;
        }

        public override Reference<IAstNode> VisitStyleSheet(QlsGrammar.QlsParser.StyleSheetContext context)
        {
            var definition = context.GetText();
            var styleSheetName = context.styleSheetName.Text;
            var pages = context.page()
                .Select(Visit)
                .To<IPageNode>(m_domainItemLocator);

            return m_astFactory.CreateStyleSheet(
                definition,
                styleSheetName,
                pages);
        }

        public override Reference<IAstNode> VisitPage(QlsGrammar.QlsParser.PageContext context)
        {
            var definition = context.GetText();
            var pageNameText = context.pageName.Text;
            var sections = context.section()
                .Select(Visit)
                .To<ISectionNode>(m_domainItemLocator);
            
            return m_astFactory.CreatePage(
                definition,
                pageNameText,
                sections);
        }

        public override Reference<IAstNode> VisitSection(QlsGrammar.QlsParser.SectionContext context)
        {
            var definition = context.GetText();
            var sectionNameText = context.sectionName.Text;
            var questions = context.question()
                .Select(Visit)
                .To<IQlsQuestionNode>(m_domainItemLocator);

            return m_astFactory.CreateSection(
                definition,
                sectionNameText,
                questions);
        }

        public override Reference<IAstNode> VisitQuestion(QlsGrammar.QlsParser.QuestionContext context)
        {
            var definition = context.GetText();
            var questionNameText = context.questionName.Text;

            return m_astFactory.CreateQuestion(
                definition,
                questionNameText);
        }
    }
}