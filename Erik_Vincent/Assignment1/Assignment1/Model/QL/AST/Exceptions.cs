using System;
using System.Runtime.Serialization;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST
{
    [Serializable]
    public abstract class ExpressionException : Exception
    {
        public IExpression Expression { get; }

        protected ExpressionException() { }

        protected ExpressionException(string message) : base(message) { }

        protected ExpressionException(string message, Exception inner) : base(message, inner) { }

        protected ExpressionException(IExpression expression)
        {
            Expression = expression;
        }

        public override void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            info.AddValue("Expression", Expression, typeof(IExpression));
            base.GetObjectData(info, context);
        }

        protected ExpressionException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
            Expression = (IExpression)info.GetValue("Expression", typeof(IExpression));
        }
    }

    [Serializable]
    public abstract class QuestionException : Exception
    {
        public Question Question { get; }

        protected QuestionException() { }

        protected QuestionException(string message) : base(message) { }

        protected QuestionException(string message, Exception inner) : base(message, inner) { }

        protected QuestionException(Question question)
        {
            Question = question;
        }

        public override void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            info.AddValue("Question", Question, typeof(Question));
            base.GetObjectData(info, context);
        }

        protected QuestionException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
            Question = (Question)info.GetValue("Question", typeof(Question));
        }
    }

    [Serializable]
    public class UndeclaredQuestionException : ExpressionException
    {
        public UndeclaredQuestionException() { }

        public UndeclaredQuestionException(string message) : base(message) { }

        public UndeclaredQuestionException(string message, Exception inner) : base(message, inner) { }

        public UndeclaredQuestionException(IExpression expression) : base(expression) { }

        protected UndeclaredQuestionException(SerializationInfo info, StreamingContext context) : base(info, context) { }
    }

    [Serializable]
    public class InvalidExpressionException : ExpressionException
    {
        public InvalidExpressionException() { }

        public InvalidExpressionException(string message) : base(message) { }

        public InvalidExpressionException(string message, Exception inner) : base(message, inner) { }

        public InvalidExpressionException(IExpression expression) : base(expression) { }

        protected InvalidExpressionException(SerializationInfo info, StreamingContext context) : base(info, context) { }
    }

    [Serializable]
    public class DuplicateQuestionException : QuestionException
    {
        public DuplicateQuestionException() { }

        public DuplicateQuestionException(string message) : base(message) { }

        public DuplicateQuestionException(string message, Exception inner) : base(message, inner) { }

        public DuplicateQuestionException(Question question) : base(question) { }

        protected DuplicateQuestionException(SerializationInfo info, StreamingContext context) : base(info, context) { }
    }
}
