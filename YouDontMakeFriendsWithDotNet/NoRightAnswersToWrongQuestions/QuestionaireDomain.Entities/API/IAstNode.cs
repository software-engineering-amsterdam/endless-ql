using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstNode : IDomainItem
    {
        Guid Id { get; } 
        IList<IAstNode> ChildNodes { get; }
        void Accept(IAstVisitor visitor);
    }
}