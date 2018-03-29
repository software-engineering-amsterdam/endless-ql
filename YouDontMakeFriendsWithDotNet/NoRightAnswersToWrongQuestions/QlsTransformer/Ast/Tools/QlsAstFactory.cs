using System.Collections;
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
            string pageName,
            IEnumerable<Reference<ISectionNode>> sections)
        {
            var pageNode = new PageNode(
                m_ids.Next,
                definition,
                pageName,
                sections);

            return DomainItemRegistration<IPageNode>(pageNode);
        }

        public Reference<ISectionNode> CreateSection(
            string definition,
            string sectionName,
            IEnumerable<Reference<IQlsQuestionNode>> questions)
        {
            var sectionNode = new SectionNode(
                m_ids.Next,
                definition,
                sectionName,
                questions);

            return DomainItemRegistration<ISectionNode>(sectionNode);
        }

        public Reference<IQlsQuestionNode> CreateQuestion(
            string definition, 
            string questionName)
        {
            var questionNode = new QlsQuestionNode(
                m_ids.Next,
                definition,
                questionName);

            return DomainItemRegistration<IQlsQuestionNode>(questionNode);
        }


        private Reference<T> DomainItemRegistration<T>(T node) where T : IDomainItem
        {
            m_registry.Add(node);
            return new Reference<T>(node.Id);
        }
    }
}