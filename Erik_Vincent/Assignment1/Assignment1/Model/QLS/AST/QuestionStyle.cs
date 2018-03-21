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

        public QuestionStyle(string id) : this(id, new List<IStyle>()) { }

        public QuestionStyle(string id, IEnumerable<IStyle> styles)
        {
            Id = id;
            _styles = styles.ToList();
        }

        public override void Accept(IQLSASTVisitor visitor) => visitor.Visit(this);
    }
}
