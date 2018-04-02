using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Nodes
{
    public class DefaultStyle : IDefaultStyle
    {
        public DefaultStyle(IQuestionType type, DomainId<IStyleNode> style)
        {
            Type = type;
            Style = style;
        }

        public IQuestionType Type { get; }
        public DomainId<IStyleNode> Style { get; }
    }
}