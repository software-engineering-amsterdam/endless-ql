using System;
using System.Linq;
using QuestionaireOrchestration.Commands;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireDomain.Entities.Validators;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.CommandHandlers
{
    internal class CreateDefinitionFromTextCommandHandler :
        ICommandHandler<CreateDefinitionFromTextCommand>
    {
        private readonly IQuestionnaireAstCreator m_astCreator;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IQuestionnaireOutputCreator m_outputCreator;
        private readonly IQuestionnaireValidator m_questionnaireValidator;

        public CreateDefinitionFromTextCommandHandler(
            IQuestionnaireAstCreator astCreator,
            IDomainItemLocator domainItemLocator,
            IQuestionnaireOutputCreator outputCreator,
            IQuestionnaireValidator questionnaireValidator
            )
        {
            m_astCreator = astCreator;
            m_domainItemLocator = domainItemLocator;
            m_outputCreator = outputCreator;
            m_questionnaireValidator = questionnaireValidator;
        }

        public void Execute(CreateDefinitionFromTextCommand command)
        {
            m_astCreator.Create(command.DefinitionText);

            var questionnaireRef = m_domainItemLocator
                .GetAllRefs<IQuestionnaireRootNode>()
                .FirstOrDefault();
            
            m_questionnaireValidator.Validate(questionnaireRef);
            if (m_questionnaireValidator.Results.Any())
            {
                var errorMessage = string.Join(
                    Environment.NewLine,
                    m_questionnaireValidator
                        .Results
                        .Select(x => x.Message));
                throw new QlValidationException(errorMessage,null);
            }

            m_outputCreator.CreateOrUpdate(questionnaireRef);
            
        }
    }
}
