using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireInfrastructure.API;
using QuestionnaireOrchestration.Commands;

namespace QuestionnaireOrchestration.CommandHandlers
{
    internal class UpdateValuesCommandHandler : 
        ICommandHandler<UpdateValuesCommand>
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IVariableUpdater m_variableUpdater;

        public UpdateValuesCommandHandler(
            IDomainItemLocator domainItemLocator,
            IVariableUpdater variableUpdater)
        {
            m_domainItemLocator = domainItemLocator;
            m_variableUpdater = variableUpdater;
        }

        public void Execute(UpdateValuesCommand updateValuesCommand)
        {
            foreach (var question in updateValuesCommand.Questionnaire.Questions)
            {
                if (question.ReadOnly) continue;
                if (question.Value == null) continue;

                var questionRef = m_domainItemLocator
                    .GetAllRefs<IQuestionNode>()
                    .FirstOrDefault(x => x.Id == question.QuestionVariableId);

                m_variableUpdater.Update(questionRef, question.Value);
            }
        }
    }
}
