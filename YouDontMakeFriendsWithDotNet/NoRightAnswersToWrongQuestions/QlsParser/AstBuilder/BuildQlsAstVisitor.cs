using QlsGrammar;
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
            return m_astFactory.CreateStyleSheet(
                definition,
                styleSheetName);
        }
    }
}