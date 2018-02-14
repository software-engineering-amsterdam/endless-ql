using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QL_Parser;
using QL_Parser.Models;

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
            Form form = QLParserHelper.Parse(builder.ToString());

            Console.WriteLine("Parsed a form with the name: {0}", form.Name);
            foreach (Question question in form.Sections.Where(x => x.GetType() == typeof(Question)))
                Console.WriteLine("ID: {0} of type {1}", question.ID, question.QType);

            Console.ReadLine();
        }
    }
}
