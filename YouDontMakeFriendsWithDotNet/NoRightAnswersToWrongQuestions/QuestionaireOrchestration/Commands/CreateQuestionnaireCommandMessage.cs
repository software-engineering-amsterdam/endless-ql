using QuestionnaireInfrastructure.API;

namespace QuestionnaireOrchestration.Commands
{
    public class CreateQuestionnaireCommandMessage : ICommandMessage
    {
        public string Text { get; set; }
    }
}