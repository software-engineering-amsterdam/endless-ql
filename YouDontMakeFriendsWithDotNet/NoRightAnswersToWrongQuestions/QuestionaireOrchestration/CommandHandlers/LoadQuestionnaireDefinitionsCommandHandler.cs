using QuestionaireOrchestration.API;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.CommandHandlers
{
    public class LoadQuestionnaireDefinitionsCommandHandler : 
        ICommandHandler<LoadQuestionnaireDefinitionsCommand>
    {
        public void Execute(LoadQuestionnaireDefinitionsCommand command)
        {
            if (string.IsNullOrEmpty(command.Path))
            {
                
            }
        }
    }
}
