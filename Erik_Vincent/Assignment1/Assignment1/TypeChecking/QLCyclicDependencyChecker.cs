using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.TypeChecking
{
    public class QLCyclicDependencyChecker : IQLASTVisitor, IExpressionVisitor
    {
        private IDictionary<string, Question> QuestionsInScope => _scopes.SelectMany(scope => scope).ToDictionary(question => question.Id, question => question);
        private readonly Stack<IEnumerable<Question>> _scopes = new Stack<IEnumerable<Question>>();
        private readonly LinkedList<Question> _currentCycle = new LinkedList<Question>();

        public static void CheckForCycles(QuestionForm questionForm)
        {
            var checker = new QLCyclicDependencyChecker();
            checker.Visit(questionForm);
        }

        private QLCyclicDependencyChecker() { }

        public void Visit(QuestionForm questionForm)
        {
            VisitStatements(questionForm.Statements);
        }

        private void VisitStatements(IEnumerable<Statement> statements)
        {
            statements = statements.ToArray();
            var questions = statements.OfType<Question>();
            _scopes.Push(questions);
            foreach (var statement in statements)
            {
                statement.Accept(this);
            }
            Debug.Assert(_scopes.Peek().Equals(questions));
            _scopes.Pop();
        }

        public void Visit(NormalQuestion question) { }

        private static void ReportError(string error)
        {
            Console.WriteLine(error);
        }

        public void Visit(ComputedQuestion question)
        {
            if (_currentCycle.Contains(question))
            {
                ReportError(question.Id + " depends on itself. Cycle:" + string.Join(" -> ", _currentCycle.Select(q => q.Id)));
            }
            else
            {
                _currentCycle.AddLast(question);
                question.Computation.Accept(this);
                Debug.Assert(_currentCycle.Last.Value.Equals(question));
                _currentCycle.RemoveLast();
            }
        }

        public void Visit(IfStatement ifStatement)
        {
            VisitStatements(ifStatement.ThenStatements);
            VisitStatements(ifStatement.ElseStatements);
        }

        public void Visit(QLBoolean value) { }

        public void Visit(QLInteger value) { }

        public void Visit(Undefined undefined) { }

        public void Visit(QLString value) { }

        public void Visit(QLDate value) { }

        public void Visit(QLDecimal value) { }

        public void Visit(QLMoney value) { }

        public void Visit(Not expression)
        {
            expression.Expression.Accept(this);
        }

        public void Visit(Reference expression)
        {
            try
            {
                QuestionsInScope[expression.QuestionId].Accept(this);
            }
            catch (KeyNotFoundException)
            {
                ReportError(expression.QuestionId + " not declared in this scope.");
            }
            catch (ArgumentNullException)
            {
                ReportError(expression.QuestionId + " contains an invalid reference.");
            }
        }

        private void VisitBinaryChildren(Binary expression)
        {
            expression.Left.Accept(this);
            expression.Right.Accept(this);
        }

        public void Visit(And expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(Or expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(LessThan expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(GreaterThan expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(GreaterThanOrEqual expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(LessThanOrEqual expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(NotEqual expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(Equal expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(Add expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(Subtract expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(Multiply expression)
        {
            VisitBinaryChildren(expression);
        }

        public void Visit(Divide expression)
        {
            VisitBinaryChildren(expression);
        }
    }
}
