using QuestionnaireInfrastructure.API;

namespace QuestionnaireOrchestration.Commands
{
    public class LoadDefinitionsFromFileCommand : ICommandMessage
    {
        public string Path { get; set; }
    }
}
