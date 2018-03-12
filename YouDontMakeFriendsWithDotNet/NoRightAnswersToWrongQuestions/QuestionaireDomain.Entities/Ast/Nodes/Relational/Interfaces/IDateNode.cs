using System;
using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionaireDomain.Entities.Ast.Nodes.Relational.Interfaces
{
    public interface IDateNode : IAstNode
    {
        DateTime Value { get; }
    }
}
