using System.Runtime.CompilerServices;
using QlsTransformer.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Tools
{
    public interface IStyleSheetCreator
    {
        Reference<IStyleSheetRootNode> Create(string definition);
    }
}