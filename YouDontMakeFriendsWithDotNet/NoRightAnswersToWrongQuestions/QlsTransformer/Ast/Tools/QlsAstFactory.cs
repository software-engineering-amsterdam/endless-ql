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

        public DomainId<IStyleSheetRootNode> CreateStyleSheet(
            string definition, 
            string styleSheetName,
            IEnumerable<IDefaultStyle> defaultStyles,
            IEnumerable<DomainId<IPageNode>> pages)
        {
            var styleSheetRootNode = new StyleSheetRootNode(
                m_ids.Next,
                definition,
                styleSheetName,
                defaultStyles,
                pages);

            return DomainItemRegistration<IStyleSheetRootNode>(styleSheetRootNode);
        }

        public DomainId<IPageNode> CreatePage(
            string definition,
            string pageName,
            IEnumerable<IDefaultStyle> defaultStyles,
            IEnumerable<DomainId<ISectionNode>> sections)
        {
            var pageNode = new PageNode(
                m_ids.Next,
                definition,
                pageName,
                defaultStyles,
                sections);

            return DomainItemRegistration<IPageNode>(pageNode);
        }

        public DomainId<ISectionNode> CreateSection(
            string definition,
            string sectionName,
            IEnumerable<IDefaultStyle> defaultStyles,
            IEnumerable<DomainId<IQlsQuestionNode>> questions)
        {
            var sectionNode = new SectionNode(
                m_ids.Next,
                definition,
                sectionName,
                defaultStyles,
                questions);

            return DomainItemRegistration<ISectionNode>(sectionNode);
        }

        public DomainId<IQlsQuestionNode> CreateQuestion(
            string definition, 
            string questionName,
            DomainId<IStyleNode> questionStyle)
        {
            var questionNode = new QlsQuestionNode(
                m_ids.Next,
                definition,
                questionName,
                questionStyle);

            return DomainItemRegistration<IQlsQuestionNode>(questionNode);
        }

        public DomainId<IAstNode> CreateStyle(
            string definition, 
            IWidget widget, 
            int? width, 
            decimal? fontSize, 
            string font, 
            string color)
        {
            var styleNode = new StyleNode(
                m_ids.Next,
                definition,
                widget,
                width,
                font,
                fontSize,
                color);

            return DomainItemRegistration<IStyleNode>(styleNode);
        }
        
        private DomainId<T> DomainItemRegistration<T>(T node) where T : IDomainItem
        {
            m_registry.Add(node);
            return new DomainId<T>(node.Id);
        }
    }
}