using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QlsTransformer.Ast.Nodes
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

    public interface IQlsQuestionNode : IAstNode
    {
        string Name { get; }
    }
}
