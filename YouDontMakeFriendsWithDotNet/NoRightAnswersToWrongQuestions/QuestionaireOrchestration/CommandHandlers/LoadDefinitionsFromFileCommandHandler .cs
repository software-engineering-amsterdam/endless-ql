using QuestionaireOrchestration.Commands;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.CommandHandlers
{
    internal class LoadDefinitionsFromFileCommandHandler : 
        ICommandHandler<LoadDefinitionsFromFileCommand>
    {
        private readonly IQuestionnaireDefinitionLoader m_definitionLoader;
        private readonly IQuestionnaireAstCreator m_questionnaireAstCreator;

        public LoadDefinitionsFromFileCommandHandler(
            IQuestionnaireDefinitionLoader definitionLoader,
            IQuestionnaireAstCreator questionnaireAstCreator)
        {
            m_definitionLoader = definitionLoader;
            m_questionnaireAstCreator = questionnaireAstCreator;
        }

        public void Execute(LoadDefinitionsFromFileCommand fromFileCommand)
        {
            var definitions = m_definitionLoader.Load(fromFileCommand.Path);
            foreach (var definition in definitions)
            {
                m_questionnaireAstCreator.Create(definition);
            }
        }
    }
}
