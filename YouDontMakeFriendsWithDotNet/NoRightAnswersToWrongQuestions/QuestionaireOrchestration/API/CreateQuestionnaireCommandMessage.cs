using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.API
{
    public class CreateQuestionnaireCommandMessage : ICommandMessage
    {
        public string Text { get; set; }
    }
}