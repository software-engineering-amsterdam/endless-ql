using QuestionaireOrchestration.API;
using QuestionnaireDomain.Logic.API;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.CommandHandlers
{
    public class LoadQuestionnaireDefinitionsCommandHandler : 
        ICommandHandler<LoadQuestionnaireDefinitionsCommand>
    {
        private readonly IQuestionnaireDefinitionLoader m_definitionLoader;
        private readonly IQuestionnaireAstCreator m_questionnaireAstCreator;

        public LoadQuestionnaireDefinitionsCommandHandler(
            IQuestionnaireDefinitionLoader definitionLoader,
            IQuestionnaireAstCreator questionnaireAstCreator)
        {
            m_definitionLoader = definitionLoader;
            m_questionnaireAstCreator = questionnaireAstCreator;
        }

        public void Execute(LoadQuestionnaireDefinitionsCommand command)
        {
            var definitions = m_definitionLoader.Load(command.Path);
            foreach (var definition in definitions)
            {
                m_questionnaireAstCreator.Create(definition);
            }
        }
    }
}
