using QlsTransformer.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Tools
{
    public interface IQlsAstFactory
    {
        Reference<IStyleSheetRootNode> CreateStyleSheet(string definition, string styleSheetName);
    }
}