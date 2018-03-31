using QlsTransformer.Domain.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Tools
{
    public interface IQlsAstBuilder
    {
        DomainId<IStyleSheetRootNode> BuildStyleSheet(string definition);
    }
}