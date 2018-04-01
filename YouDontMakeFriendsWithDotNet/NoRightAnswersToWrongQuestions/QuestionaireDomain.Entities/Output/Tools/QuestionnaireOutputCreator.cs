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
        private readonly IQuestionnaireTypeChecker m_questionnaireTypeChecker;
        private readonly ICalculationService m_calculationService;

        public QuestionnaireOutputCreator(
            IBuildOutputVisitor buildOutputVisitor,
            IQuestionnaireTypeChecker questionnaireTypeChecker,
            ICalculationService calculationService)
        {
            m_buildOutputVisitor = buildOutputVisitor;
            m_questionnaireTypeChecker = questionnaireTypeChecker;
            m_calculationService = calculationService;
        }
        
        public void CreateOrUpdate(DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var isValid = m_questionnaireTypeChecker
                .Validate(questionnaireRootNode);
            if (isValid)
            {
                m_calculationService.UpdateCalculations();
                m_buildOutputVisitor.Build(questionnaireRootNode);
            }
        }
    }
}