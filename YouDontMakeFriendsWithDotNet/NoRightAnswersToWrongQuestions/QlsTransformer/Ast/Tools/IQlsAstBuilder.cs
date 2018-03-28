using QlsTransformer.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Tools
{
    public interface IQlsAstBuilder
    {
        Reference<IStyleSheetRootNode> BuildStyleSheet(string definition);
    }
}