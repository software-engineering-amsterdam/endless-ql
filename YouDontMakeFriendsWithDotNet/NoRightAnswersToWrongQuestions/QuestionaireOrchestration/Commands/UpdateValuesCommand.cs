using QuestionnaireInfrastructure.API;
using QuestionnaireOrchestration.Models;

namespace QuestionnaireOrchestration.Commands
{
    public class UpdateValuesCommand : ICommandMessage
    {
        public QuestionnaireModel Questionnaire;
    }
}