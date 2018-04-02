using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Nodes
{
    public interface IDefaultStyle
    {
        IQuestionType Type { get; }
        DomainId<IStyleNode> Style { get; }
    }
}