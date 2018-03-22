using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
using QuestionnaireDomain.Entities.Validators.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    internal class QuestionnaireOutputCreator : IQuestionnaireOutputCreator
    {
        private readonly IBuildOutputVisitor m_buildOutputVisitor;
        private readonly IQuestionnaireValidator m_questionnaireValidator;
        private readonly IVariableService m_variableService;

        public QuestionnaireOutputCreator(
            IBuildOutputVisitor buildOutputVisitor,
            IQuestionnaireValidator questionnaireValidator,
            IVariableService variableService)
        {
            m_buildOutputVisitor = buildOutputVisitor;
            m_questionnaireValidator = questionnaireValidator;
            m_variableService = variableService;
        }
        
        public void CreateOrUpdate(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var isValid = m_questionnaireValidator
                .Validate(questionnaireRootNode);
            if (isValid)
            {
                m_variableService.UpdateCalculations();
                m_buildOutputVisitor.Build(questionnaireRootNode);
            }
        }
    }
}