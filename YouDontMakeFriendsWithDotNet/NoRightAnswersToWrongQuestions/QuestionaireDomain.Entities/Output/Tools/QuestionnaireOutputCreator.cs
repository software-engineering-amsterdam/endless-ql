using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    internal class QuestionnaireOutputCreator : IQuestionnaireOutputCreator
    {
        private readonly IBuildOutputVisitor m_buildOutputVisitor;

        public QuestionnaireOutputCreator(
            IBuildOutputVisitor buildOutputVisitor)
        {
            m_buildOutputVisitor = buildOutputVisitor;
        }
        
        public void CreateOrUpdate(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            m_buildOutputVisitor.Build(questionnaireRootNode);
        }
    }
}