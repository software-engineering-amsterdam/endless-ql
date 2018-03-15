using System.Collections.Generic;

namespace Assignment1.Model.QLS
{
    public class Page
    {
        public string Id;
        public readonly List<Section> Sections = new List<Section>();
    }
}
