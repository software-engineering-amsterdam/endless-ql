using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public abstract class AstNodeBase : IAstNode
    {
        protected AstNodeBase(Guid id)
        {
            Id = id;
        }

        public Guid Id { get; }
        public abstract void Accept(IAstVisitor visitor);
    }
}