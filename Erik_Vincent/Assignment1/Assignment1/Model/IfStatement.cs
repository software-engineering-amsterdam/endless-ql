using System.Collections.Generic;

namespace Assignment1.Model
{
    public class IfStatement : Content
    {
        public readonly Expression Expression;
        public readonly List<Content> ThenContent;

        public IfStatement(Expression expression, List<Content> thenContent)
        {
            Expression = expression;
            ThenContent = thenContent;
        }

        public override void Accept(IContentVisitor visitor)
        {
            visitor.Visit(this);
        }
    }

    public class IfElseStatement : IfStatement
    {
        public readonly List<Content> ElseContent;

        public IfElseStatement(Expression expression, List<Content> thenContent, List<Content> elseContent) : base(expression, thenContent)
        {
            ElseContent = elseContent;
        }

        public override void Accept(IContentVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
