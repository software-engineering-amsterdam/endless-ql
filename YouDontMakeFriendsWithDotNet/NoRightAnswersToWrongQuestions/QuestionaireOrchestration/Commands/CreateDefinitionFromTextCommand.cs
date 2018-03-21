using QuestionnaireInfrastructure.API;

namespace QuestionnaireOrchestration.Commands
{
    public class CreateDefinitionFromTextCommand : ICommandMessage
    {
        public string DefinitionText { get; set; }
    }
}
