using System.Collections.Generic;

namespace Assignment1.Model.QLS
{
    public class Section : IContent
    {
        public readonly List<IContent> Contents = new List<IContent>();

        public void Accept(IContentVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
