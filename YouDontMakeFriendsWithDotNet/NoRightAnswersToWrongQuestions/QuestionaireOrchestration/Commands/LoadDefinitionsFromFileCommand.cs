using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.Commands
{
    public class LoadDefinitionsFromFileCommand : ICommandMessage
    {
        public string Path { get; set; }
    }
}
