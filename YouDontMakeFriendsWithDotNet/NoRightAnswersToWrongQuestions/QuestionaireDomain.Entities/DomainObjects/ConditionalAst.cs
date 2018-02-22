using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class ConditionalAst : AstNodeBase, IConditionalAst
    {
        public ConditionalAst(string questionName)
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
