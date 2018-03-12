using System;
using System.Globalization;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.Ast.Nodes.Relational.Interfaces;

namespace QuestionaireDomain.Entities.Ast.Nodes.Relational
{
    internal class DateNode : AstNodeBase, IDateNode
    {
        public DateTime Value { get; }

        public DateNode(
            Guid id,
            string dateText)
            : base(id, dateText)
        {
            DateTime parsedDateTime;
            var culture = new CultureInfo("en-GB");
            var styles = DateTimeStyles.None;
            if (!DateTime.TryParse(dateText, culture, styles, out parsedDateTime))
            {
                throw new QlParserException($"could not parse '{dateText}' into a date", null);
            }

            Value = parsedDateTime;
        }
    }
}