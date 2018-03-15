using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Model.QL
{
    public interface IQuestionVisitor
    {
        void Visit(QuestionBool question);
        void Visit(QuestionInt question);
        void Visit(QuestionDate question);
        void Visit(QuestionDecimal question);
        void Visit(QuestionMoney question);
        void Visit(QuestionString question);
    }
}
