using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Tools
{
    internal class QuestionnaireAstCreator : IQuestionnaireAstCreator
    {
        private readonly IAstBuilder m_astBuilder;

        public QuestionnaireAstCreator(IAstBuilder astBuilder)
        {
            m_astBuilder = astBuilder;
        }

        public DomainId<IQuestionnaireRootNode> Create(string definition)
        {
            return m_astBuilder.BuildForm(definition);
        }
    }
}