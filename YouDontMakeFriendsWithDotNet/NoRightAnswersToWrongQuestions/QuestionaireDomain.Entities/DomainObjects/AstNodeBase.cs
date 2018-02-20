using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public interface IAstNode
    {
        IList<IAstNode> Questions { get; }

        void Accept(IAstVisitor visitor);
    }

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