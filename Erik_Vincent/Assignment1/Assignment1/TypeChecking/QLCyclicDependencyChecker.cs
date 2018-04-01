using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.TypeChecking
{
    public class QLCyclicDependencyChecker : QLASTBaseVisitor
    {
        private readonly LinkedList<Question> _currentCycle = new LinkedList<Question>();
        private readonly MessageContainer _messages = new MessageContainer();

        /// <summary>
        /// Checks for cyclic dependencies.
        /// Will throw UndeclaredQuestionException, InvalidExpressionException or DuplicateQuestionException if the form is invalid.
        /// </summary>
        /// <param name="questionForm">Form without duplicate or undeclared questions.</param>
        public static (IEnumerable<string> errors, IEnumerable<string> warnings) CheckForCycles(QuestionForm questionForm)
        {
            var checker = new QLCyclicDependencyChecker();
            checker.Visit(questionForm);
            return checker._messages.ToTuple();
        }

        private QLCyclicDependencyChecker() { }

        public override void Visit(ComputedQuestion question)
        {
            if (_currentCycle.Contains(question))
            {
                _messages.AddError(question.Id + " depends on itself. Cycle:" + string.Join(" -> ", _currentCycle.Select(q => q.Id)));
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
