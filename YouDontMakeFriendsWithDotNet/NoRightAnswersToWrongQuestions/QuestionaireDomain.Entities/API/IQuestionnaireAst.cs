<<<<<<< HEAD
﻿using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireAst : IDomainItem, IAstNode
    {
        string FormName { get; set; }
    }
=======
﻿using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireAst : IDomainItem, IAstNode
    {
        string FormName { get; set; }
        IList<IQuestionAst> Questions { get; }
    }
>>>>>>> b4a9b6ed7a567bef7322e087eb0d3de8f04a3913
}