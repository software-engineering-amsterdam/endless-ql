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
            Reference<IQuestionNode> variable,
            string questionText,
            Type questionType,
            string value,
            bool isVisible,
            bool isReadonly)
        {
            Id = id;
            Variable = variable;
            DisplayName = questionText;
            QuestionText = questionText;
            Value = value;
            QuestionType = questionType;
            Visible = isVisible;
            ReadOnly = isReadonly;
        }
        
        public Guid Id { get; }
        public Reference<IQuestionNode> Variable { get; }
        public string DisplayName { get; }
        public string QuestionText { get; }
        public bool Visible { get; set; }
        public bool ReadOnly { get; }
        // ToDo: should this be dynamic
        public string Value { get; set; }
        public Type QuestionType { get; }
    }
}
