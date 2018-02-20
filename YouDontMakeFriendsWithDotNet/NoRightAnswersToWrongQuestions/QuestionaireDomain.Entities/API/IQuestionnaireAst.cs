using System.Collections.Generic;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireAst : IDomainItem
    {
        string FormName { get; set; }
        IList<IQuestionAst> Questions { get; }
    }
}