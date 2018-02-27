using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class QuestionnaireAst : AstNodeBase, IQuestionnaireAst
    {
        public QuestionnaireAst(Guid id) : base(id)
        {
        }

        public string FormName { get; set; }
        
        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: something here
        }
    }
}