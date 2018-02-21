using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public abstract class AstNodeBase : IAstNode
    {
        protected AstNodeBase()
        {
            Statements = new List<IAstNode>();
        }
        
        public IList<IAstNode> Statements { get; }

        public abstract void Accept(IAstVisitor visitor);
    }
}