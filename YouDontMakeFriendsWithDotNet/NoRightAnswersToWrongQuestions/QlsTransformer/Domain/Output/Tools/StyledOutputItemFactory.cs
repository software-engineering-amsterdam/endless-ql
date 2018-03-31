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
        private readonly IDomainItemLocator m_domainItemLocator;

        public StyledOutputItemFactory(
            IIdMaker ids,
            IDomainItemRegistry registry,
            IDomainItemLocator domainItemLocator)
        {
            m_ids = ids;
            m_registry = registry;
            m_domainItemLocator = domainItemLocator;
        }

        //ToDo: SS, page and section are all the same - remove repetition
        public DomainId<IStyledQuestionnaireOutputItem> CreateRoot(
            string displayName, 
            List<DomainId<IPagesOutputItem>> pages)
        {
            var questionnaireOutputItem = new StyledQuestionnaireOutputItem(
                m_ids.Next,
                displayName)
            {
                Pages = pages
            };

            return DomainItemRegistration<IStyledQuestionnaireOutputItem>(
                questionnaireOutputItem);
        }

        public DomainId<IPagesOutputItem> CreatePage(
            string displayName, 
            List<DomainId<ISectionOutputItem>> sections)
        {
            var pageOutputItem = new PagesOutputItem(
                m_ids.Next,
                displayName)
            {
                Sections = sections
            };

            return DomainItemRegistration<IPagesOutputItem>(
                pageOutputItem);
        }

        public DomainId<ISectionOutputItem> CreateSection(
            string displayName, 
            List<DomainId<IStyledQuestionOutputItem>> questions)
        {
            var sectionOutputItem = new SectionOutputItem(
                m_ids.Next,
                displayName)
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

        //ToDo: make a factory base class with this in
        private DomainId<T> DomainItemRegistration<T>(T node) where T : IDomainItem
        {
            m_registry.Add(node);
            return new DomainId<T>(node.Id);
        }
    }
}