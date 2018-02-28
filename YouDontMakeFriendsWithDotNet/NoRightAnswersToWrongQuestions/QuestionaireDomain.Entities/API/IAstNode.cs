using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstNode : IDomainItem
    {
        void Accept(IAstVisitor visitor);
    }

    public interface IQuestionnaireAstNode : IAstNode
    {
        IList<IQuestionnaireAstNode> ChildNodes { get; }
    }
}