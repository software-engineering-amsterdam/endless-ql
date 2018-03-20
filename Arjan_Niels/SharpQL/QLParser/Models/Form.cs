using System;
using System.Collections.Generic;

namespace QL_Parser.Models
{
    public class Form
    {
        public String Name { get; set; }
        public List<Section> Sections { get; set; }

        public Form()
        {
            this.Sections = new List<Section>();
        }
    }
}
