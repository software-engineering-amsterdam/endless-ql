using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireInfrastructure.API;
using QuestionnaireOrchestration.Commands;

namespace QuestionnaireOrchestration.CommandHandlers
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

        public void Execute(LoadDefinitionsFromFileCommand command)
        {
            var definitions = m_definitionLoader.Load(command.Path);
            foreach (var definition in definitions)
            {
                m_questionnaireAstCreator.Create(definition);
            }
        }
    }
}