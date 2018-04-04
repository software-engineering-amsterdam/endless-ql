using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Nodes
{
    internal class QuestionOutputItem : IQuestionOutputItem
    {
        public QuestionOutputItem(
            Guid id,
            DomainId<IQuestionNode> variable,
            string questionName,
            string questionText,
            IQuestionType questionType,
            string value,
            bool isVisible,
            bool isReadonly)
        {
            Id = id;
            Variable = variable;
            QuestionName = questionName;
            DisplayName = questionText;
            QuestionText = questionText;
            Value = value;
            QuestionType = questionType;
            Visible = isVisible;
            ReadOnly = isReadonly;
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
    }
}