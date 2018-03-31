using System;
using System.Linq;
using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Ast.Tools;
using QlsTransformer.Domain.Output.Tools;
using QlsTransformer.Domain.Validators;
using QlsTransformer.Orchestration.Commands;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QlsTransformer.Orchestration.CommandHandler
{
    internal class CreateStyleSheetFromTextCommandHandler :
            ICommandHandler<CreateStyleSheetFromTextCommand>
    {

        private readonly IStyleSheetCreator m_styleSheetAstCreator;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IStyleSheetTypeChecker m_styleSheetTypeChecker;
        private readonly IStyledQuestionnaireOutputCreator m_outputCreator;

        public CreateStyleSheetFromTextCommandHandler(
            IStyleSheetCreator styleSheetAstCreator,
            IDomainItemLocator domainItemLocator,
            IStyleSheetTypeChecker styleSheetTypeChecker,
            IStyledQuestionnaireOutputCreator outputCreator
        )
        {
            m_styleSheetAstCreator = styleSheetAstCreator;
            m_domainItemLocator = domainItemLocator;
            m_styleSheetTypeChecker = styleSheetTypeChecker;
            m_outputCreator = outputCreator;
        }

        public void Execute(CreateStyleSheetFromTextCommand command)
        {
            m_styleSheetAstCreator.Create(command.DefinitionText);

            var styleSheetId = m_domainItemLocator
                .GetAllRefs<IStyleSheetRootNode>()
                .FirstOrDefault();

            var isValid = m_styleSheetTypeChecker
                .Validate(styleSheetId);

            if (!isValid)
            {
                var errorMessage = string.Join(
                    Environment.NewLine,
                    m_styleSheetTypeChecker
                        .Results
                        .Select(x => x.Message));

                throw new QlValidationException(errorMessage, null);
            }

            m_outputCreator.CreateOrUpdate(styleSheetId);
        }
    }
}
