using QuestionaireOrchestration.Commands;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.CommandHandlers
{
    internal class ParseTextCommandHandler : ICommandHandler<CreateQuestionnaireCommandMessage>
    {
        private readonly IQuestionnaireAstCreator m_questionnaireAstCreator;

        public ParseTextCommandHandler(
            IQuestionnaireAstCreator questionnaireAstCreator)
        {
            m_questionnaireAstCreator = questionnaireAstCreator;
        }
        
        public void Execute(CreateQuestionnaireCommandMessage command)
        {
            m_questionnaireAstCreator.Create(command.Text);
        }
    }
}