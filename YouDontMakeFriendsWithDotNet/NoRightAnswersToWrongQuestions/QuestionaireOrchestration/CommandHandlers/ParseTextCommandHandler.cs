using QuestionaireOrchestration.API;
using QuestionnaireDomain.Logic.API;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.CommandHandlers
{
    internal class ParseTextCommandHandler : ICommandHandler<CreateQuestionnaireCommandMessage>
    {
        private readonly IQuestionnaireCreator m_questionnaireCreator;

        public ParseTextCommandHandler(
            IQuestionnaireCreator questionnaireCreator)
        {
            m_questionnaireCreator = questionnaireCreator;
        }
        
        public void Execute(CreateQuestionnaireCommandMessage command)
        {
            m_questionnaireCreator.Create(command.Text);
        }
    }
}