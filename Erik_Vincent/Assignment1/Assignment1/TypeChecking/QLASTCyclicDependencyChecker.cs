using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.TypeChecking
{
    public class QLASTCyclicDependencyChecker : QLASTBaseVisitor
    {
        private readonly LinkedList<Question> _currentCycle = new LinkedList<Question>();

        /// <summary>
        /// Checks for cyclic dependencies.
        /// Will throw UndeclaredQuestionException, InvalidExpressionException or DuplicateQuestionException if the form is invalid.
        /// </summary>
        /// <param name="questionForm">Form without duplicate or undeclared questions.</param>
        public static void CheckForCycles(QuestionForm questionForm)
        {
            var checker = new QLASTCyclicDependencyChecker();
            checker.Visit(questionForm);
        }

        private QLASTCyclicDependencyChecker() { }

        private static void ReportError(string error)
        {
            Console.WriteLine(error);
        }

        public override void Visit(ComputedQuestion question)
        {
            if (_currentCycle.Contains(question))
            {
                ReportError(question.Id + " depends on itself. Cycle:" + string.Join(" -> ", _currentCycle.Select(q => q.Id)));
            }
            else
            {
                _currentCycle.AddLast(question);
                base.Visit(question);
                Debug.Assert(_currentCycle.Last.Value.Equals(question));
                _currentCycle.RemoveLast();
            }
        }

        public override void Visit(Reference expression)
        {
            FollowReference(expression);
        }
    }
}
