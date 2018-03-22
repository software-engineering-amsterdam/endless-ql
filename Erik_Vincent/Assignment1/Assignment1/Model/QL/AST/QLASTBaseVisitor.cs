using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;
using Assignment1.TypeChecking;

namespace Assignment1.Model.QL.AST
{
    public abstract class QLASTBaseVisitor : IQLASTVisitor, IExpressionVisitor
    {
        private IReadOnlyDictionary<string, Question> QuestionsInScope => _scopes.SelectMany(scope => scope).ToDictionary(question => question.Id, question => question);
        private readonly Stack<IEnumerable<Question>> _scopes = new Stack<IEnumerable<Question>>();

        public virtual void Visit(QuestionForm questionForm)
        {
            VisitStatements(questionForm.Statements);
        }

        private void VisitStatements(IEnumerable<Statement> statements)
        {
            statements = statements.ToArray();
            var questions = statements.OfType<Question>().ToArray();
            foreach (var question in questions)
            {
                if (QuestionsInScope.ContainsKey(question.Id))
                {
                    throw new DuplicateQuestionException(question);
                }
            }
            _scopes.Push(questions);
            foreach (var statement in statements)
            {
                statement.Accept(this);
            }
            Debug.Assert(_scopes.Peek().Equals(questions));
            _scopes.Pop();
        }

        public virtual void Visit(NormalQuestion question)
        {
            question.Answer.Accept(this);
        }

        public virtual void Visit(ComputedQuestion question)
        {
            question.Computation.Accept(this);
        }

        public virtual void Visit(IfStatement ifStatement)
        {
            VisitStatements(ifStatement.ThenStatements);
            VisitStatements(ifStatement.ElseStatements);
        }

        public virtual void Visit(QLBoolean value)
        {
            value.Accept(this);
        }

        public virtual void Visit(QLInteger value)
        {
            value.Accept(this);
        }

        public virtual void Visit(Undefined undefined)
        {
            undefined.Accept(this);
        }

        public virtual void Visit(QLString value)
        {
            value.Accept(this);
        }

        public virtual void Visit(QLDate value)
        {
            value.Accept(this);
        }

        public virtual void Visit(QLDecimal value)
        {
            value.Accept(this);
        }

        public virtual void Visit(QLMoney value)
        {
            value.Accept(this);
        }

        public virtual void Visit(Not expression)
        {
            expression.Expression.Accept(this);
        }

        public virtual void Visit(Reference expression)
        {
            try
            {
                QuestionsInScope[expression.QuestionId].Accept(this);
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