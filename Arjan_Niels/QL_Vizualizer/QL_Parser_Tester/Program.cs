using QL_Parser;
using QL_Parser.Models;
using System;
using System.Text;

namespace QL_Parser_Tester
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] lines = System.IO.File.ReadAllLines(@"..\..\..\QL_Parser_Tester\Questionnaire.gl");
            StringBuilder builder = new StringBuilder();
            foreach (string line in lines)
                builder.AppendLine(line);

            Console.WriteLine("Start parsing the QL");
            Form form = QLParserHelper.Parse(builder.ToString());
            PrintForm(form);

            Console.ReadLine();
        }

        public static void PrintForm(Form form)
        {
            Console.WriteLine("Parsed a form with the name: {0}", form.Name);
            foreach (Section section in form.Sections)
                if (section.GetType() == typeof(Question))
                    PrintSection(section as Question);
                else
                    PrintSection(section as ConditionalBlock);
        }

        public static void PrintSection(Question question)
        {
            Console.WriteLine(question);
        }

        public static void PrintSection(ConditionalBlock conditional)
        {
            Console.WriteLine("\nHere starts a new section");
            foreach (Section section in conditional.Sections)
                if (section.GetType() == typeof(Question))
                    PrintSection(section as Question);
                else
                    PrintSection(section as ConditionalBlock);
        }
    }
}
