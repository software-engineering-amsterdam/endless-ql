using QlsTransformer.Ast.Nodes;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QlsTransformer.Ast.Tools
{
    internal class QlsAstFactory : IQlsAstFactory
    {
        private readonly IIdMaker m_ids;
        private readonly IDomainItemRegistry m_registry;

        public QlsAstFactory(
            IIdMaker ids,
            IDomainItemRegistry registry)
        {
            m_ids = ids;
            m_registry = registry;
        }

        public Reference<IStyleSheetRootNode> CreateStyleSheet(
            string definition, 
            string styleSheetName)
        {
            var styleSheetRootNode = new StyleSheetRootNode(
                m_ids.Next,
                definition,
                styleSheetName);

            return DomainItemRegistration<IStyleSheetRootNode>(styleSheetRootNode);
        }

        private Reference<T> DomainItemRegistration<T>(T node) where T : IDomainItem
        {
            m_registry.Add(node);
            return new Reference<T>(node.Id);
        }
    }
}