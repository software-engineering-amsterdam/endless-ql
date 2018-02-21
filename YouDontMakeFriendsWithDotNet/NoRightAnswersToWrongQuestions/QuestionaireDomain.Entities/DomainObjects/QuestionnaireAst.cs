using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class QuestionnaireAst : AstNodeBase, IQuestionnaireAst
    {
        public QuestionnaireAst()
        {
            Id = Guid.NewGuid();
            Questions = new List<IQuestionAst>();
        }

        public string FormName { get; set; }
        public Guid Id { get; }
        public IList<IQuestionAst> Questions { get; }
        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: something here
        }
    }
}