﻿using System;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface IQuestionNode : IStatementNode, ITerminal
    {
        string QuestionName { get; }
        string QuestionText { get; }
        Type QuestionType { get; }
    }
}