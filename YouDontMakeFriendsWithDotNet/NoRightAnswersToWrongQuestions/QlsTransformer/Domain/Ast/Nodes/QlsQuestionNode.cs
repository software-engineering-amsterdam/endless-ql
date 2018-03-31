using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Domain.Ast.Nodes
{
    internal class QlsQuestionNode : AstNodeBase, IQlsQuestionNode
    {
        public QlsQuestionNode(
            Guid id,
            string definition,
            string name,
            DomainId<IStyleNode> questionStyle) : base(id, definition)
        {
            Name = name;
            Style = questionStyle;
        }
        
        public string Name { get; }
        public DomainId<IStyleNode> Style { get; }
    }
}
