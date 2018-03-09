using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;
using QuestionnaireInfrastructure.API;

namespace QuestionaireDomain.Entities.API
{
    internal class OutputItemFactory : IOutputItemFactory
    {
        private readonly IIdMaker m_ids;
        private readonly IDomainItemRegistry m_registry;

        public OutputItemFactory(
            IIdMaker ids, 
            IDomainItemRegistry registry)
        {
            m_ids = ids;
            m_registry = registry;
        }

        public Reference<IQuestionnaireOutputItem> CreateQuestionnaireOutputItem(
            string displayName, 
            IList<IQuestionOutputItem> questions)
        {
            var questionnaireOutputItem= new QuestionnaireOutputItem(
                m_ids.Next,
                displayName) {Questions = questions };

            return DomainItemRegistration<IQuestionnaireOutputItem>(questionnaireOutputItem);
        }

        public IQuestionOutputItem<T> CreateQuestionOutputItem<T>(string text, T value, bool isVisible, bool isReadonly)
        {
            throw new NotImplementedException();
        }

        private Reference<T> DomainItemRegistration<T>(T node) where T : IDomainItem
        {
            m_registry.Add(node);
            return new Reference<T>(node.Id);
        }
    }
}