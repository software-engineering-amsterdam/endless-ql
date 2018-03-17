using System.Linq;
using QuestionaireOrchestration.Commands;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.CommandHandlers
{
    internal class CreateDefinitionFromTextCommandHandler :
        ICommandHandler<CreateDefinitionFromTextCommand>
    {
        private readonly IQuestionnaireAstCreator m_astCreator;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IQuestionnaireOutputCreator m_outputCreator;

        public CreateDefinitionFromTextCommandHandler(
            IQuestionnaireAstCreator astCreator,
            IDomainItemLocator domainItemLocator,
            IQuestionnaireOutputCreator outputCreator)
        {
            m_astCreator = astCreator;
            m_domainItemLocator = domainItemLocator;
            m_outputCreator = outputCreator;
        }

        public void Execute(CreateDefinitionFromTextCommand command)
        {
            m_astCreator.Create(command.DefinitionText);

            var questionnaireRef = m_domainItemLocator
                .GetAllRefs<IQuestionnaireRootNode>()
                .FirstOrDefault();

            if (m_outputCreator.Validate(questionnaireRef))
            {
                m_outputCreator.CreateOrUpdate(questionnaireRef);
            }
        }
    }
}
