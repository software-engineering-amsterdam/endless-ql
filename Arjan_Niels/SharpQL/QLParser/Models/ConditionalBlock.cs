using System.Collections.Generic;

namespace QL_Parser.Models
{
    public class ConditionalBlock : Section
    {
        public List<Section> Sections { get; set; }
        public Statement Statement { get; set; }

        public ConditionalBlock()
        {
            this.Sections = new List<Section>();
        }
    }
}
