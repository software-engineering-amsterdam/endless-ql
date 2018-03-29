using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QlsTransformer.Ast.Nodes
{
    internal class PageNode : AstNodeBase, IPageNode
    {
        public PageNode(
            Guid id, 
            string definition,
            string name) : base(id, definition)
        {
            Name = name;
        }

        public string Name { get; }
    }

    public interface IPageNode : IAstNode
    {
        string Name { get; }
    }
}
