using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.Commands
{
    public class CreateQuestionnaireCommandMessage : ICommandMessage
    {
        public string Text { get; set; }
    }
}