using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Model.QL.AST
{
    /// <summary>
    /// The visitor will by default not follow references.
    /// To visit references override the Visit(Reference expression) method and call
    /// FollowReference(expression) but be aware that doing this with an invalid form can cause exceptions.
    /// </summary>
    public abstract class QLASTBaseVisitor : IQLASTVisitor, IExpressionVisitor
    {
        protected ILookup<string, Question> QuestionsInScope => _scopes.SelectMany(scope => scope).ToLookup(question => question.Id, question => question);
        private readonly Stack<IEnumerable<Question>> _scopes = new Stack<IEnumerable<Question>>();

        public virtual void Visit(QuestionForm questionForm)
        {
            VisitStatements(questionForm.Statements);
        }

        protected void VisitStatements(IEnumerable<Statement> statements)
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
            ifStatement.Condition.Accept(this);
            VisitStatements(ifStatement.ThenStatements);
            VisitStatements(ifStatement.ElseStatements);
        }

        public virtual void Visit(QLBoolean value) { }

        public virtual void Visit(QLInteger value) { }

        public virtual void Visit(QLString value) { }

        public virtual void Visit(QLDate value) { }

        public virtual void Visit(QLDecimal value) { }

        public virtual void Visit(QLMoney value) { }

        public virtual void Visit(Not expression)
        {
            expression.Expression.Accept(this);
        }

        /// <summary>
        /// Will visit the referenced question.
        /// Will throw an UndeclaredQuestionException, InvalidExpressionException or DuplicateQuestionException
        /// if the reference can not be folllowed.
        /// </summary>
        /// <param name="expression"></param>
        protected void FollowReference(Reference expression)
        {
            try
            {
                QuestionsInScope[expression?.QuestionId].Single().Accept(this);
            }
            catch (KeyNotFoundException)
            {
                throw new UndeclaredQuestionException(expression);
            }
            catch (ArgumentNullException)
            {
                throw new InvalidExpressionException(expression);
            }
            catch (InvalidOperationException)
            {
                throw new DuplicateQuestionException(expression?.QuestionId);
            }
        }

        public virtual void Visit(Reference expression) { }

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