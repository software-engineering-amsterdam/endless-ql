using QuestionnaireInfrastructure.API;

namespace QlsTransformer.Orchestration.Commands
{
    public class CreateStyleSheetFromTextCommand : ICommandMessage
    {
        public string DefinitionText { get; set; }
    }
}