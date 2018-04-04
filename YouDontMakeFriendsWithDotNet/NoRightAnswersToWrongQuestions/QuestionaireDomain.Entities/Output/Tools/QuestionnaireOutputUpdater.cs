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

        public QuestionnaireOutputUpdater(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public bool OutputExistsFor(DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            return GetRootOutput(questionnaireRootNode) != null;
        }

        private IQuestionnaireOutputItem GetRootOutput(DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var name = questionnaireRootNode
                .ToDomainItem(m_domainItemLocator)
                .QuestionnaireName;

            return m_domainItemLocator
                .GetAll<IQuestionnaireOutputItem>()
                .FirstOrDefault(x => x.DisplayName == name);
        }
    }
}