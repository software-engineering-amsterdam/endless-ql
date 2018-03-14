using System;
using System.Globalization;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational
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