using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Model.QL.AST
{
    public abstract class QLASTBaseVisitor : IQLASTVisitor, IExpressionVisitor
    {
        private ILookup<string, Question> QuestionsInScope => _scopes.SelectMany(scope => scope).ToLookup(question => question.Id, question => question);
        private readonly Stack<IEnumerable<Question>> _scopes = new Stack<IEnumerable<Question>>();

        public virtual void Visit(QuestionForm questionForm)
        {
            VisitStatements(questionForm.Statements);
        }

        private void VisitStatements(IEnumerable<Statement> statements)
        {
            statements = statements.ToArray();
            var questions = statements.OfType<Question>().ToArray();
            _scopes.Push(questions);
            foreach (var statement in statements)
            {
                statement.Accept(this);
            }
            Debug.Assert(_scopes.Peek().Equals(questions));
            _scopes.Pop();
        }

        private void CheckDuplicateQuestion(Question question)
        {
            if (QuestionsInScope[question.Id].Count() > 1)
            {
                throw new DuplicateQuestionException(question);
            }
        }

        public virtual void Visit(NormalQuestion question)
        {
            CheckDuplicateQuestion(question);
            question.Answer.Accept(this);
        }

        public virtual void Visit(ComputedQuestion question)
        {
            CheckDuplicateQuestion(question);
            question.Computation.Accept(this);
        }

        public virtual void Visit(IfStatement ifStatement)
        {
            ifStatement.Condition.Accept(this);
            VisitStatements(ifStatement.ThenStatements);
            VisitStatements(ifStatement.ElseStatements);
        }

        public virtual void Visit(QLBoolean value) { }

        public virtual void Visit(QLInteger value) { }

        public virtual void Visit(Undefined undefined) { }

        public virtual void Visit(QLString value) { }

        public virtual void Visit(QLDate value) { }

        public virtual void Visit(QLDecimal value) { }

        public virtual void Visit(QLMoney value) { }

        public virtual void Visit(Not expression)
        {
            expression.Expression.Accept(this);
        }

        public virtual void Visit(Reference expression)
        {
            try
            {
                QuestionsInScope[expression.QuestionId].First().Accept(this);
            }
            catch (KeyNotFoundException)
            {
                throw new UndeclaredQuestionException(expression);
            }
            catch (ArgumentNullException)
            {
                throw new InvalidExpressionException(expression);
            }
        }

        private void VisitBinaryChildren(Binary expression)
        {
            expression.Left.Accept(this);
            expression.Right.Accept(this);
        }

        public virtual void Visit(And expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(Or expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(LessThan expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(GreaterThan expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(GreaterThanOrEqual expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(LessThanOrEqual expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(NotEqual expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(Equal expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(Add expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(Subtract expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(Multiply expression)
        {
            VisitBinaryChildren(expression);
        }

        public virtual void Visit(Divide expression)
        {
            VisitBinaryChildren(expression);
        }
    }
}