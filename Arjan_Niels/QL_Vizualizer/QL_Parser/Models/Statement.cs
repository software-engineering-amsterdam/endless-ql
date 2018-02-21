using System;

namespace QL_Parser.Models
{
    public class Statement : IComparable
    {
        public string lhs { get; set; }
        public string rhs { get; set; }
        public string opr { get; set; }

        public int CompareTo(object obj)
        {
            throw new NotImplementedException();
        }

        public bool GetValue()
        {
            return false;
        }


    }
}
