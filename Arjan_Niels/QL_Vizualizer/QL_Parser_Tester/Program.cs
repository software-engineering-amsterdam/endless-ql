using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QL_Parser;

namespace QL_Parser_Tester
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] lines = System.IO.File.ReadAllLines(@"..\..\..\QL_Parser_Tester\Questionnaire.txt");
            StringBuilder builder = new StringBuilder();
            foreach (string line in lines)
                builder.AppendLine(line);

            Console.WriteLine("Start parsing the QL");
            QLParserHelper.Parse(builder.ToString());
        }
    }
}
