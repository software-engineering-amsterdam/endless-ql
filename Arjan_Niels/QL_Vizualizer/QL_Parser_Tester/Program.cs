using QL_Parser;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Validators;
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
            FormNode form = QLParserHelper.Parse(builder.ToString());
            PrintForm(form);

            ASTValidator.IsValid(form);
            var errors = ASTValidator.GetErrors();
            Console.WriteLine("\n\n---- Errors: {0} ----", errors.Count);
            foreach (string error in errors)
                Console.WriteLine(error);

            Console.ReadLine();
        }

        public static void PrintForm(FormNode form)
        {
            Console.WriteLine(form);
            foreach (Node section in form.Children)
                if (section.GetType() == typeof(QuestionNode))
                    PrintSection(section as QuestionNode);
                else
                    PrintSection(section as ConditionalNode);
        }

        public static void PrintSection(QuestionNode question)
        {
            Console.WriteLine(question);
        }

        public static void PrintSection(ConditionalNode conditional)
        {
            Console.WriteLine("\n" + conditional);
            foreach (Node section in conditional.Children)
                if (section.GetType() == typeof(QuestionNode))
                    PrintSection(section as QuestionNode);
                else
                    PrintSection(section as ConditionalNode);
        }
    }
}
