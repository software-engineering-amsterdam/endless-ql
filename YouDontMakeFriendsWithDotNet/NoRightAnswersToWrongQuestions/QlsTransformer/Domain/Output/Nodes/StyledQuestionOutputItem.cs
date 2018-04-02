using System;
using QlsTransformer.Domain.Ast.Nodes;
using QlsTransformer.Domain.Output.Tools;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QlsTransformer.Domain.Output.Nodes
{
    internal class StyledQuestionOutputItem : IStyledQuestionOutputItem
    {
        public StyledQuestionOutputItem(
            Guid id,
            IQuestionOutputItem question,
            Style style)
        {
            Id = id;
            Variable = question.Variable;
            QuestionName = question.QuestionName;
            DisplayName = question.DisplayName;
            QuestionText = question.QuestionText;
            Value = question.Value;
            QuestionType = question.QuestionType;
            Visible = question.Visible;
            ReadOnly = question.ReadOnly;
            Widget = style.Widget;
            Width = style.Width;
            Font = style.Font;
            FontSize = style.FontSize;
            Color = style.Color;
        }

        public Guid Id { get; }
        public DomainId<IQuestionNode> Variable { get; }
        public string QuestionName { get; }
        public string DisplayName { get; }
        public string QuestionText { get; }
        public bool Visible { get; set; }
        public bool ReadOnly { get; }
        public string Value { get; set; }
        public IQuestionType QuestionType { get; }
        public IWidget Widget { get; }
        public int Width { get; }
        public string Font { get; }
        public decimal FontSize { get; }
        public string Color { get; }
    }
}
