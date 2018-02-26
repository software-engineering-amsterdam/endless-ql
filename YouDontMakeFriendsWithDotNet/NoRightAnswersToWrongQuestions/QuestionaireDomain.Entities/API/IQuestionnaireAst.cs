using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireAst : IAstNode
    {
        string FormName { get; set; }
        IList<IQuestionAst> Questions { get; }
    }
}