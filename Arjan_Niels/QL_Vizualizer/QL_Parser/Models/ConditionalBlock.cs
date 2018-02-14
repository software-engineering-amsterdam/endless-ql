using System.Collections.Generic;

namespace QL_Parser.Models
{
    public class ConditionalBlock : Section
    {
        public ConditionalBlock()
        {
            this.Sections = new List<Section>();
        }

        public List<Section> Sections { get; set; }
    }
}
