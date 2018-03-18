using QuestionaireOrchestration.Models;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.Commands
{
    public class UpdateValuesCommand : ICommandMessage
    {
        public QuestionnaireModel Questionnaire;
    }
}