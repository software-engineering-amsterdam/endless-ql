using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Validators;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Output.Tools
{
    internal class StyledQuestionnaireOutputCreator : IStyledQuestionnaireOutputCreator
    {
        private readonly IStyleSheetTypeChecker m_questionnaireTypeChecker;
        private readonly IQuestionStyleVisitor m_styleVisitor;

        public StyledQuestionnaireOutputCreator(
            IQuestionStyleVisitor styleVisitor,
            IStyleSheetTypeChecker questionnaireTypeChecker)
        {
            m_styleVisitor = styleVisitor;
            m_questionnaireTypeChecker = questionnaireTypeChecker;
        }

        public void CreateOrUpdate(DomainId<IStyleSheetRootNode> questionnaireRootNode)
        {
            var isValid = m_questionnaireTypeChecker
                .Validate(questionnaireRootNode);
            if (isValid) m_styleVisitor.Build(questionnaireRootNode);
        }
    }

    public interface IStyledQuestionnaireOutputCreator
    {
        void CreateOrUpdate(DomainId<IStyleSheetRootNode> questionnaireRootNode);
    }
}