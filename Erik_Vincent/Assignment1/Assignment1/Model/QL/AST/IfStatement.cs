using System.Collections.Generic;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST
{
    public class IfStatement : Statement
    {
        public IExpression Condition { get; }
        public List<Statement> ThenStatements { get; }
        public List<Statement> ElseStatements { get; }

        public IfStatement(IExpression condition, List<Statement> thenStatements)
            : this(condition, thenStatements, new List<Statement>()) { }

        public IfStatement(IExpression condition, List<Statement> thenStatements, List<Statement> elseStatements)
        {
            Condition = condition;
            ThenStatements = thenStatements;
            ElseStatements = elseStatements;
        }

        public override void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
