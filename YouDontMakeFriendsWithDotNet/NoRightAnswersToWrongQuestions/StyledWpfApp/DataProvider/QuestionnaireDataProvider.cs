using System;
using System.Linq;
using QlsTransformer.Orchestration.Commands;
using QlsTransformer.Orchestration.Models;
using QlsTransformer.Orchestration.QueryServices;
using QlsTransformer.UI.Models;
using QuestionnaireInfrastructure.API;
using QuestionnaireOrchestration.Commands;

namespace StyledWpfApp.DataProvider
{
    internal class QuestionnaireDataProvider: IQuestionnaireDataProvider
    {
        private readonly ICommandBus m_commandBus;
        private readonly IStyledQuestionnaireModelQueryService m_queryService;

        public QuestionnaireDataProvider(
            ICommandBus commandBus,
            IStyledQuestionnaireModelQueryService queryService)
        {
            m_commandBus = commandBus;
            m_queryService = queryService;
        }

        public StyledQuestionnaireWrapper GetSingleQuestionnaire()
        {
            var firstItem = m_queryService.GetAll().FirstOrDefault();
            if (firstItem == null)
            {
                throw new ArgumentException("No Questionnaire Created");
            }

            var model = m_queryService.GetModel(firstItem.Id);

            return new StyledQuestionnaireWrapper(model);
        }

        public void LoadDefaultQuestionnaire()
        {
            var command = new CreateDefinitionFromTextCommand
            {
                DefinitionText = Properties.Resources.ExampleForm
            };
            m_commandBus.Send(command);
        }

        public void Reload(StyledQuestionnaireModel questionnaireModel)
        {
            var command = new UpdateStyledValuesCommand
            {
                Questionnaire = questionnaireModel
            };
            m_commandBus.Send(command);
        }

        public void LoadStyleSheet()
        {
            var command = new CreateStyleSheetFromTextCommand
            {
                DefinitionText = Properties.Resources.ExampleStyleSheet
            };
            m_commandBus.Send(command);
        }
    }
}
