using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public interface IAstNode
    {
        IList<IAstNode> Questions { get; }

        void Accept(IAstVisitor visitor);
    }
}