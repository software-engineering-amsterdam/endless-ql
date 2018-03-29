using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QlsTransformer.Ast.Nodes
{
    internal class QlsQuestionNode : AstNodeBase, IQlsQuestionNode
    {
        public QlsQuestionNode(
            Guid id, 
            string definition,
            string name) : base(id, definition)
        {
            Name = name;
        }

        public string Name { get; }
    }

    public interface IQlsQuestionNode : IAstNode
    {
        string Name { get; }
    }
}
