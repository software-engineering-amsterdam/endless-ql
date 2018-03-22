using System.Collections.Generic;
using System.Linq;
using Assignment1.Model.QLS.AST.Style;

namespace Assignment1.Model.QLS.AST
{
    public class QuestionStyle : Statement
    {
        public string Id { get; }
        public IReadOnlyCollection<IStyle> Styles => _styles.AsReadOnly();

        private readonly List<IStyle> _styles;

        public QuestionStyle(int lineNumber, string id) : this(lineNumber, id, new List<IStyle>()) { }

        public QuestionStyle(int lineNumber, string id, IEnumerable<IStyle> styles)
        {
            _lineNumber = lineNumber;
            Id = id;
            _styles = styles.ToList();
        }

        public override void Accept(IQLSASTVisitor visitor) => visitor.Visit(this);
    }
}
