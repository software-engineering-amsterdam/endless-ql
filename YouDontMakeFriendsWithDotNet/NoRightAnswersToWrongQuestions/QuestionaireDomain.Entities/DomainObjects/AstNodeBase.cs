using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public abstract class AstNodeBase : IQuestionnaireAstNode
    {
        protected AstNodeBase(Guid id)
        {
            Id = id;
        }

        public Guid Id { get; }
        public IList<IQuestionnaireAstNode> ChildNodes { get; } = new List<IQuestionnaireAstNode>();

        public abstract void Accept(IAstVisitor visitor);
    }
}