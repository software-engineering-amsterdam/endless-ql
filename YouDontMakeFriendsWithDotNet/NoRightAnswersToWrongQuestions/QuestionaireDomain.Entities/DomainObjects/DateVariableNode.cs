using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Relational;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class DateVariableNode : VariableNodeBase, IDateVariableNode
    {
        public DateVariableNode(
            Guid id,
            string variableName)
            : base(id, variableName)
        {
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }

    public class TextVariableNode : VariableNodeBase, ITextVariableNode
    {
        public TextVariableNode(
            Guid id,
            string variableName)
            : base(id, variableName)
        {
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }
}