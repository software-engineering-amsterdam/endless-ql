using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class BooleanVariableNode : AstNodeBase, IVariableNode
    {
        public string VariableName { get; }

        public BooleanVariableNode(Guid id, string variableName) : base(id, variableName)
        {
            VariableName = variableName;
        }

        public override void Accept(IAstVisitor visitor)
        {
            (visitor as IAstVisitor<IVariableNode>)?.Visit(this);
        }
    }
}