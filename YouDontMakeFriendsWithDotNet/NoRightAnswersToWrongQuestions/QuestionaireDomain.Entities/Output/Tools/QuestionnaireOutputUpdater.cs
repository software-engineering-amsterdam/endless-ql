using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    internal class QuestionnaireOutputUpdater : IQuestionnaireOutputUpdater
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IDomainItemRegistry m_domainItemRegistry;

        public QuestionnaireOutputUpdater(
            IDomainItemLocator domainItemLocator,
            IDomainItemRegistry domainItemRegistry)
        {
            m_domainItemLocator = domainItemLocator;
            m_domainItemRegistry = domainItemRegistry;
        }

        public bool OutputExistsFor(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            return GetRootOutput(questionnaireRootNode) != null;
        }

        private IQuestionnaireOutputItem GetRootOutput(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var name = questionnaireRootNode
                .ToDomainItem(m_domainItemLocator)
                .QuestionnaireName;
            //ToDo: this assumes a single root node with the same name
            return m_domainItemLocator
                .GetAll<IQuestionnaireOutputItem>()
                .FirstOrDefault(x => x.DisplayName == name);
        }

        //public void UpdateOutputFor(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        //{
        //    UpdateOutput(GetRootOutput(questionnaireRootNode));
        //}

        //private void UpdateOutput(IQuestionnaireOutputItem questionnaireOutputItem)
        //{
        //    foreach (var outputItem in questionnaireOutputItem.Questions)
        //    {
        //        var domainItem = m_domainItemLocator
        //        m_domainItemRegistry.Delete(outputItem);
        //    }
        //}
    }
}