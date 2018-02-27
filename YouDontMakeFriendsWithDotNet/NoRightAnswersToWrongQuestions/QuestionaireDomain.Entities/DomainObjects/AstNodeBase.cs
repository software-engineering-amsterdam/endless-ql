using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public abstract class AstNodeBase : IAstNode
    {
        protected AstNodeBase(Guid id)
        {
            Id = id;
        }

        public Guid Id { get; }
        public IList<IAstNode> ChildNodes { get; } = new List<IAstNode>();

        public abstract void Accept(IAstVisitor visitor);
    }
}