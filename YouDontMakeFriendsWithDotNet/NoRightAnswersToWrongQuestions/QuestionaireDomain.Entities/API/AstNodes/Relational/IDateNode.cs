using System;

namespace QuestionaireDomain.Entities.API.AstNodes.Relational
{
    public interface IDateNode : IAstNode
    {
        DateTime Value { get; }
    }
}
