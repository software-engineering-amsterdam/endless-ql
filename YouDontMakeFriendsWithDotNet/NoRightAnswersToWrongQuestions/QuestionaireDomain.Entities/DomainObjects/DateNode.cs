using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Relational;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class DateNode : AstNodeBase, IDateNode
    {
        public DateTime Value { get; }

        public DateNode(
            Guid id,
            string dateText)
            : base(id, dateText)
        {
            DateTime parsedDateTime;
            if (!DateTime.TryParse(dateText, out parsedDateTime))
            {
                throw new QlParserException($"could not parse '{dateText}' into a date", null);
            }

            Value = parsedDateTime;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }
    }
}