using System.Collections.Generic;
using System.Linq;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST
{
    public class IfStatement : Statement
    {
        public IExpression Condition { get; }
        public IReadOnlyCollection<Statement> ThenStatements => _thenStatements.AsReadOnly();
        public IReadOnlyCollection<Statement> ElseStatements => _elseStatements.AsReadOnly();

        private readonly List<Statement> _thenStatements;
        private readonly List<Statement> _elseStatements;

        public IfStatement(int lineNumber, IExpression condition, IEnumerable<Statement> thenStatements)
            : this(lineNumber, condition, thenStatements, new List<Statement>()) { }

        public IfStatement(int lineNumber, IExpression condition, IEnumerable<Statement> thenStatements, IEnumerable<Statement> elseStatements)
        {
            _lineNumber = lineNumber;
            Condition = condition;
            _thenStatements = thenStatements.ToList();
            _elseStatements = elseStatements.ToList();
        }

        public override void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
