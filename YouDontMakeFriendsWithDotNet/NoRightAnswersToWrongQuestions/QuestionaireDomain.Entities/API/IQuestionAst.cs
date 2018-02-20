using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionAst
    {
        string Name { get; }
        string Text { get; }
    }
}
