using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public interface IAstNode
    {
        IList<IAstNode> Statements { get; }

        void Accept(IAstVisitor visitor);
    }
}