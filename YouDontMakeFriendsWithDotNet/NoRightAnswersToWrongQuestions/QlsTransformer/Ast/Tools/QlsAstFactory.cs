using System.Collections.Generic;
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
            string styleSheetName,
            IEnumerable<Reference<IPageNode>> pages)
        {
            var styleSheetRootNode = new StyleSheetRootNode(
                m_ids.Next,
                definition,
                styleSheetName,
                pages);

            return DomainItemRegistration<IStyleSheetRootNode>(styleSheetRootNode);
        }

        public Reference<IPageNode> CreatePage(
            string definition,
            string pageName)
        {
            var pageNode = new PageNode(
                m_ids.Next,
                definition,
                pageName);

            return DomainItemRegistration<IPageNode>(pageNode);
        }

        private Reference<T> DomainItemRegistration<T>(T node) where T : IDomainItem
        {
            m_registry.Add(node);
            return new Reference<T>(node.Id);
        }
    }
}