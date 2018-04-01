using System.Collections.Generic;
using QlsTransformer.Domain.Output.Nodes;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QlsTransformer.Domain.Output.Tools
{
    internal class StyledOutputItemFactory : IStyledOutputItemFactory
    {
        private readonly IIdMaker m_ids;
        private readonly IDomainItemRegistry m_registry;

        public StyledOutputItemFactory(
            IIdMaker ids,
            IDomainItemRegistry registry)
        {
            m_ids = ids;
            m_registry = registry;
        }

        public DomainId<IStyledQuestionnaireOutputItem> CreateRoot(
            string name, 
            List<DomainId<IPagesOutputItem>> pages)
        {
            var questionnaireOutputItem = new StyledQuestionnaireOutputItem(
                m_ids.Next,
                name)
            {
                Pages = pages
            };

            return DomainItemRegistration<IStyledQuestionnaireOutputItem>(
                questionnaireOutputItem);
        }

        public DomainId<IPagesOutputItem> CreatePage(
            string name, 
            List<DomainId<ISectionOutputItem>> sections)
        {
            var pageOutputItem = new PagesOutputItem(
                m_ids.Next,
                name)
            {
                Sections = sections
            };

            return DomainItemRegistration<IPagesOutputItem>(
                pageOutputItem);
        }

        public DomainId<ISectionOutputItem> CreateSection(
            string name, 
            List<DomainId<IStyledQuestionOutputItem>> questions)
        {
            var sectionOutputItem = new SectionOutputItem(
                m_ids.Next,
                name)
            {
                Questions = questions
            };

            return DomainItemRegistration<ISectionOutputItem>(
                sectionOutputItem);
        }

        public DomainId<IStyledQuestionOutputItem> CreateQuestion(
            IQuestionOutputItem question, 
            Style style)
        {
            var styledQuestionOutputItem = new StyledQuestionOutputItem(
                m_ids.Next,
                question,
                style);

            return DomainItemRegistration<IStyledQuestionOutputItem>(
                styledQuestionOutputItem);
        }
        
        private DomainId<T> DomainItemRegistration<T>(T node) where T : IDomainItem
        {
            m_registry.Add(node);
            return new DomainId<T>(node.Id);
        }
    }
}