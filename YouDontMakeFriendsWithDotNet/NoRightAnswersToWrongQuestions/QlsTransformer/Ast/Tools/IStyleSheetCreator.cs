using System.Runtime.CompilerServices;
using QlsTransformer.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Tools
{
    public interface IStyleSheetCreator
    {
        DomainId<IStyleSheetRootNode> Create(string definition);
    }
}