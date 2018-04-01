using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Output.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Output.Tools
{
    public interface IQuestionStyleVisitor
    {
        DomainId<IStyledQuestionnaireOutputItem> Build(
            DomainId<IStyleSheetRootNode> node);
    }
}