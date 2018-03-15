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

            return m_domainItemLocator
                .GetAll<IQuestionnaireOutputItem>()
                .FirstOrDefault(x => x.DisplayName == name);
        }

        public void DeleteOutputFor(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            DeleteOutput(GetRootOutput(questionnaireRootNode));
        }

        private void DeleteOutput(IQuestionnaireOutputItem questionnaireOutputItem)
        {
            foreach (var outputItem in questionnaireOutputItem.Questions)
            {
                m_domainItemRegistry.Delete(outputItem);
            }
        }
    }
}