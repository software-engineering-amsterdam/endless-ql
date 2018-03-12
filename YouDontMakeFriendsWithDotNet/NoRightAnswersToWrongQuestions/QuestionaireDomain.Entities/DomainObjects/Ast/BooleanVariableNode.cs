using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class BooleanVariableNode : AstNodeBase, IBooleanVariableNode
    {
        public string VariableName { get; }

        public BooleanVariableNode(Guid id, string variableName) : base(id, variableName)
        {
            VariableName = variableName;
        }
    }
}