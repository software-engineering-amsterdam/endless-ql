using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Nodes
{
    public interface IQlsQuestionNode : IAstNode
    {
        string Name { get; }
        DomainId<IStyleNode> Style { get; }
    }
}