using System;
using QlsTransformer.Domain.Ast.Nodes;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QlsTransformer.Domain.Output.Nodes
{
    public interface IStyledQuestionOutputItem : IDomainItem
    {
        DomainId<IQuestionNode> Variable { get; }
        string QuestionName { get; }
        string QuestionText { get; }
        bool Visible { get; set; }
        bool ReadOnly { get; }
        string Value { get; set; }
        IQuestionType QuestionType { get; }
        IWidget Widget { get; }
        int Width { get; }
        string Font { get; }
        decimal FontSize { get; }
        string Color { get; }
    }
}