using System;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class ConditionalAst : AstNodeBase, IConditionalAst
    {
        public ConditionalAst(Guid id, string questionName) : base(id)
        {
            QuestionName = questionName;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }

        public string QuestionName { get; }
    }
}
