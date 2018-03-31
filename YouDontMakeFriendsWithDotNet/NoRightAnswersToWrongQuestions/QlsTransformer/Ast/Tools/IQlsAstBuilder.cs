using QlsTransformer.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Tools
{
    public interface IQlsAstBuilder
    {
        DomainId<IStyleSheetRootNode> BuildStyleSheet(string definition);
    }
}