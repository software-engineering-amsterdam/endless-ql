using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireAst : IQuestionnaireAstNode
    {
        string FormName { get; set; }
    }
}