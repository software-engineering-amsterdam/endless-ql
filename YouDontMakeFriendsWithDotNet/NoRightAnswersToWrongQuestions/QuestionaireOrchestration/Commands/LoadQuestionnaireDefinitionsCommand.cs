using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.Commands
{
    public class LoadQuestionnaireDefinitionsCommand : ICommandMessage
    {
        public string Path { get; set; }
    }
}
