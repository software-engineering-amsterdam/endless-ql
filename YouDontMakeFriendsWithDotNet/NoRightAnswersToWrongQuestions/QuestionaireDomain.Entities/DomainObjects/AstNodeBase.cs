<<<<<<< HEAD
﻿using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public abstract class AstNodeBase : IAstNode
    {
        protected AstNodeBase()
        {
            Statements = new List<IAstNode>();
        }
        
        public IList<IAstNode> Statements { get; }

        public abstract void Accept(IAstVisitor visitor);
    }
=======
﻿using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public abstract class AstNodeBase : IAstNode
    {
        protected AstNodeBase()
        {
            Questions = new List<IAstNode>();
        }
        
        public IList<IAstNode> Questions { get; }

        public abstract void Accept(IAstVisitor visitor);
    }
>>>>>>> b4a9b6ed7a567bef7322e087eb0d3de8f04a3913
}