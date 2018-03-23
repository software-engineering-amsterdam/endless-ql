using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Model.QL.AST
{
    /// <summary>
    /// This class should only be used to visit valid forms.
    /// </summary>
    public abstract class QLASTValidVisitor : QLASTBaseVisitor
    {
        private void CheckDuplicateQuestion(Question question)
        {
            if (QuestionsInScope[question.Id].Count() > 1)
            {
                throw new DuplicateQuestionException(question);
            }
        }

        public override void Visit(NormalQuestion question)
        {
            CheckDuplicateQuestion(question);
            question.Answer.Accept(this);
        }

        public override void Visit(ComputedQuestion question)
        {
            CheckDuplicateQuestion(question);
            question.Computation.Accept(this);
        }
        
        public override void Visit(Reference expression)
        {
            FollowReference(expression);
        }
    }
}