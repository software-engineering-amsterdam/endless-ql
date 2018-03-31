using System;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;
using QuestionnaireInfrastructure.API;
using QuestionnaireOrchestration.Commands;

namespace QuestionnaireOrchestration.CommandHandlers
{
    internal class CreateDefinitionFromTextCommandHandler :
        ICommandHandler<CreateDefinitionFromTextCommand>
    {
        private readonly IQuestionnaireAstCreator m_astCreator;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IQuestionnaireOutputCreator m_outputCreator;
        private readonly IQuestionnaireTypeChecker m_questionnaireTypeChecker;

        public CreateDefinitionFromTextCommandHandler(
            IQuestionnaireAstCreator astCreator,
            IDomainItemLocator domainItemLocator,
            IQuestionnaireOutputCreator outputCreator,
            IQuestionnaireTypeChecker questionnaireTypeChecker
            )
        {
            m_astCreator = astCreator;
            m_domainItemLocator = domainItemLocator;
            m_outputCreator = outputCreator;
            m_questionnaireTypeChecker = questionnaireTypeChecker;
        }

        public void Execute(CreateDefinitionFromTextCommand command)
        {
            m_astCreator.Create(command.DefinitionText);

            var questionnaireRef = m_domainItemLocator
                .GetAllRefs<IQuestionnaireRootNode>()
                .FirstOrDefault();
            
            var isValid = m_questionnaireTypeChecker
                .Validate(questionnaireRef);

            if (!isValid)
            {
                var errorMessage = string.Join(
                    Environment.NewLine,
                    m_questionnaireTypeChecker
                        .Results
                        .Select(x => x.Message));
                throw new QlValidationException(errorMessage,null);
            }

            m_outputCreator.CreateOrUpdate(questionnaireRef);
        }
    }
}
