using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces
{
    public interface IDateNode : IAstNode
    {
        DateTime Value { get; }
    }
}
