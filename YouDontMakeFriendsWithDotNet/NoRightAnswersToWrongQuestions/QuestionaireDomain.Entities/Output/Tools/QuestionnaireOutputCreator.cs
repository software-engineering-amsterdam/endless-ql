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
        private readonly ICalculationService m_calculationService;

        public QuestionnaireOutputCreator(
            IBuildOutputVisitor buildOutputVisitor,
            IQuestionnaireValidator questionnaireValidator,
            ICalculationService calculationService)
        {
            m_buildOutputVisitor = buildOutputVisitor;
            m_questionnaireValidator = questionnaireValidator;
            m_calculationService = calculationService;
        }
        
        public void CreateOrUpdate(DomainId<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var isValid = m_questionnaireValidator
                .Validate(questionnaireRootNode);
            if (isValid)
            {
                m_calculationService.UpdateCalculations();
                m_buildOutputVisitor.Build(questionnaireRootNode);
            }
        }
    }
}