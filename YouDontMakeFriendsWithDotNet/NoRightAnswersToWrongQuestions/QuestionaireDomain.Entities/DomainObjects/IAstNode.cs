<<<<<<< HEAD
﻿using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public interface IAstNode
    {
        IList<IAstNode> Statements { get; }

        void Accept(IAstVisitor visitor);
    }
=======
﻿using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public interface IAstNode
    {
        IList<IAstNode> Questions { get; }

        void Accept(IAstVisitor visitor);
    }
>>>>>>> b4a9b6ed7a567bef7322e087eb0d3de8f04a3913
}