using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.Commands
{
    public class CreateDefinitionFromTextCommand : ICommandMessage
    {
        public string DefinitionText { get; set; }
    }
}
