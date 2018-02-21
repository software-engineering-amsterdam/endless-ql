using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public abstract class AstNodeBase : IAstNode
    {
        protected AstNodeBase()
        {
            Questions = new List<IAstNode>();
        }
        
        public IList<IAstNode> Questions { get; }

        public abstract void Accept(IAstVisitor visitor);
    }
}