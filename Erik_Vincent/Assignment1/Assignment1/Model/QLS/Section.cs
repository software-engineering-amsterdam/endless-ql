using System.Collections.Generic;

namespace Assignment1.Model.QLS
{
    public class Section : IContent
    {
        public List<IContent> Contents;

        public void Accept(IContentVisitor visitor)
        {
            
        }
    }
}
