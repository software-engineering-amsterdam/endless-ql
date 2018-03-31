using QlsTransformer.Domain.Ast.Nodes;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Tools
{
    public interface IStyleSheetCreator
    {
        DomainId<IStyleSheetRootNode> Create(string definition);
    }
}