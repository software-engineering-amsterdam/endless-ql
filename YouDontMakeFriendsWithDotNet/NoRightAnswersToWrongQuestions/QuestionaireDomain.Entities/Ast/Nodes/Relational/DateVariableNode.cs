using System;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.Ast.Nodes.Relational.Interfaces;

namespace QuestionaireDomain.Entities.Ast.Nodes.Relational
{
    internal class DateVariableNode : 
        VariableNodeBase, 
        IDateVariableNode
    {
        public DateVariableNode(
            Guid id,
            string variableName)
            : base(id, variableName)
        {
        }
    }
}