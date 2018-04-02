using QlsTransformer.Orchestration.Models;
using QuestionnaireInfrastructure.API;

namespace QlsTransformer.Orchestration.Commands
{
    public class UpdateStyledValuesCommand : ICommandMessage
    {
        public StyledQuestionnaireModel Questionnaire { get; set; }
    }
}