using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.API
{
    public class LoadQuestionnaireDefinitionsCommand : ICommandMessage
    {
        public string Path { get; set; }
    }
}
