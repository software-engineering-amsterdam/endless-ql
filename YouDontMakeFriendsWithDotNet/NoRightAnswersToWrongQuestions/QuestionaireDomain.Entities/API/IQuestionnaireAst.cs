using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireAst : IDomainItem, IAstNode
    {
        string FormName { get; set; }
        IList<IQuestionAst> Questions { get; }
    }
}